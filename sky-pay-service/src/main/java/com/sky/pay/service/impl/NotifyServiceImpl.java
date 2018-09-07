/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.service.impl 
 *
 *    Filename:    NotifyServiceImpl.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Hardy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年09月02日 22:14 
 *
 *    Revision: 
 *
 *    2018/9/2 22:14 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.service.impl;

import com.sky.common.redis.RedisService;
import com.sky.pay.constant.PayConstant;
import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.entity.*;
import com.sky.pay.enums.PayChannelEnum;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.mapper.*;
import com.sky.pay.reflect.pay.ReflectPayService;
import com.sky.pay.service.NotifyService;
import com.sky.pay.util.FilesUtil;
import com.sky.pay.util.ReflectClazzUtil;
import com.sky.pay.vo.NotifyVO;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  * @ClassName NotifyServiceImpl
 *  * @Description 回调接口实现类
 *  * @Author Hardy
 *  * @Date 2018年09月02日 22:14
 *  * @Version 1.0.0
 *  
 **/
@Service
public class NotifyServiceImpl implements NotifyService {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(NotifyServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RechargeMapper rechargeMapper;

    @Autowired
    private UserWalletMapper userWalletMapper;

    @Autowired
    private UserTreasureMapper userTreasureMapper;

    @Autowired
    private UserQuantityMapper userQuantityMapper;

    @Autowired
    private CagentMapper cagentMapper;

    @Autowired
    private CagentYsepayMapper cagentYsepayMapper;

    @Autowired
    private OnlinepayCallbackLogMapper onlinepayCallbackLogMapper;

    @Autowired
    private CagentStoredvalueLogMapper cagentStoredvalueLogMapper;

    @Autowired
    private CagentStoredvalueMapper cagentStoredvalueMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 功能描述:
     * 支付回调
     * @Author: Hardy
     * @Date: 2018年09月02日 22:16:05
     * @param
     * @return: void
     **/
    @Override
    public String payNotify(NotifyVO notifyVO) throws RPCPayException {
        logger.info("异步通知开始====================START================");
        try {
            //保存回调日志
            Map<String, String> fileMap = new HashMap<String, String>();
            fileMap.put("requestIp", notifyVO.getIp());
            fileMap.put("requestParams", JSONUtil.toJsonStr(notifyVO.getMap()));
            FilesUtil.saveLog(notifyVO.getClazzName(),fileMap);
            //通过订单号查询订单信息
            Recharge recharge = rechargeMapper.selectByOrderNo(notifyVO.getOrderNo());
            if(recharge == null){
                logger.error("{NOTIFY}订单号不存在,查询支付单据失败!");
                return ResponseConstant.FAILD_CODE;
            }
            //通过支付商ID查询支付商配置信息
            Integer payId = recharge.getPayId();
            CagentYsepay cagentYsepay = cagentYsepayMapper.selectByPrimaryKey(payId);
            if(cagentYsepay == null){
                logger.error("{NOTIFY}支付商不存在,查询支付商配置信息失败!");
                return ResponseConstant.SUCCESS_CODE;
            }
            //获取支付商配置详情
            String paymentConfig = cagentYsepay.getPaymentConfig();
            if(StrUtil.isBlank(paymentConfig)){
                logger.error("支付商没有配置支付信息!");
                return ResponseConstant.SUCCESS_CODE;
            }
            //验签
            Integer type = recharge.getPayType().intValue();//支付类型
            String payChannel = null;//支付渠道编码
            for (PayChannelEnum payChannelEnum : PayChannelEnum.values()){
                if (payChannelEnum.getType() == type){
                    payChannel = payChannelEnum.getCode().toLowerCase();
                }
            }
            //通过反射
            ReflectPayService reflectPayService = getReflectPayService(cagentYsepay,payChannel);
            //验签
            boolean isVerify = reflectPayService.notify(notifyVO.getMap());
            if(isVerify){//验签成功
                logger.info("支付回调验签成功!");
                //操作订单业务流程,组装所学要的参数
                notifyVO.setAmount(recharge.getOrderAmount().doubleValue());//订单金额
                notifyVO.setUid(recharge.getUid());//用户ID
                notifyVO.setCodingRate(cagentYsepay.getCodingRate().doubleValue());//打码量倍率
                notifyVO.setDividendRate(cagentYsepay.getDividendRate().doubleValue());//彩金倍率
                //写入用户彩金和打码量记录
                logger.info("写入用户资金流水开始===================START========================");
                saveProcess(notifyVO);
                logger.info("写入用户资金流水成功===================END========================");
                return ResponseConstant.SUCCESS_CODE;
            }
            //验签失败,直接返回，不做任何业务操作
            logger.error("========{"+notifyVO.getClazzName()+"}验签失败!=====================");
            return ResponseConstant.SUCCESS_CODE;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("异步通知处理异常:"+e.getMessage());
            throw  new RPCPayException("error","异步通知处理异常!",e.getMessage());
        }finally {
            //去掉缓存
            if(redisService.hasKey(notifyVO.getOrderNo())){
                logger.info("删除支付回调缓存中的key:"+notifyVO.getOrderNo());
                redisService.del(notifyVO.getOrderNo());
            }
        }
    }

    /**
     * 功能描述:
     * 保存支付成功后的业务
     * @Author: Hardy
     * @Date: 2018年09月04日 18:14:13
     * @param notifyVO
     * @return: void
     **/
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public void saveProcess(NotifyVO notifyVO) throws Exception{
        try {
            //组装所需数据
            String orderNo = notifyVO.getOrderNo();//订单号
            String tradeNo = notifyVO.getTradeNo();//第三方流水账号
            String tradeStatus = notifyVO.getTradeStatus();//第三方返回的订单状态
            String successStatus = notifyVO.getSuccessStatus();//第三方规定的成功订单返回的状态
            Integer uid = notifyVO.getUid();//用户ID
            Double dividendRate = notifyVO.getDividendRate();//彩金倍率
            Double codingRate = notifyVO.getCodingRate();//打码倍率
            Double amount = notifyVO.getAmount();//订单金额
            //查询用户钱包余额
            User user = userMapper.selectByPrimaryKey(uid);
            Double wallet = user.getWallet() == null ? 0.00 : user.getWallet().doubleValue();//用户钱包余额
            //根据平台编码获取平台详情
            Cagent cagent = cagentMapper.selectByCagent(user.getCagent());
            Integer cid = cagent.getId();//平台ID
            String cagentName = cagent.getCagent();//平台编码

            //修改订单状态
            Recharge recharge = new Recharge();
            recharge.setOrderNo(orderNo);
            recharge.setTradeNo(tradeNo);
            if(tradeStatus.equals(successStatus)){
                recharge.setTradeStatus(ResponseConstant.SUCCESS_CODE.toLowerCase());//订单回调成功
                //保存回调日志
                OnlinepayCallbackLog onlinepayCallbackLog = new OnlinepayCallbackLog();
                onlinepayCallbackLog.setIp(notifyVO.getIp());//访问来源IP
                onlinepayCallbackLog.setParams(JSONUtil.toJsonStr(notifyVO.getMap()));//第三方回调请求参数
                onlinepayCallbackLog.setStatus(ResponseConstant.SUCCESS_CODE.toLowerCase());//验签成功,支付订单也成功
                onlinepayCallbackLogMapper.insertSelective(onlinepayCallbackLog);
                //计算用户彩金,和打码量
                //用户彩金 = 充值金额 * 彩金倍率
                Double dividendAmount = NumberUtil.mul(amount,dividendRate);//彩金
                //用户打码量 = （充值金额 + (充值金额 * 彩金倍率)）* 打码量倍率
                Double codingAmount = NumberUtil.mul(NumberUtil.add(amount,dividendAmount),codingRate.doubleValue());
                //如果用户彩金(dividendAmount > 0)的时候,会员资金流水金额 = 彩金;变更金额 = 用户钱包余额+充值金额,变更后金额=用户钱包余额+充值金额+彩金
                //若果用户彩金(dividendAmount <= 0)的时候，会员资金流水金额 = 充值金额;变更金额=用户钱包余额,变更后金额=钱包余额+充值金额

                //判断平台商是否设置了彩金(彩金即使平台送出去的金额)
                if(dividendAmount > 0){
                    //插入一条会员资金流水彩金记录
                    UserTreasure userTreasure = new UserTreasure();
                    userTreasure.setUid(uid);
                    userTreasure.setAmount(Float.parseFloat(new DecimalFormat("####0.00").format(dividendAmount)));
                    userTreasure.setType("IN");
                    userTreasure.setAddtime(new Date());
                    //变更金额 = 用户钱包余额+充值金额
                    userTreasure.setOldMoney((float)NumberUtil.add(wallet,amount));
                    //变更后金额=用户钱包余额+充值金额+彩金
                    userTreasure.setNewMoney((float)NumberUtil.add(NumberUtil.add(wallet,amount),dividendAmount.doubleValue()));
                    userTreasure.settType("彩金");
                    userTreasure.setRmk("彩金");
                    userTreasure.setCagent(cagentName);
                    userTreasure.setNumber(notifyVO.getOrderNo());
                    userTreasureMapper.insertSelective(userTreasure);

                    //插入一条代理平台可用储值额度日志
                    CagentStoredvalueLog cagentStoredvalueLog = new CagentStoredvalueLog();
                    cagentStoredvalueLog.setCid(cid);
                    cagentStoredvalueLog.setType("OUT");
                    cagentStoredvalueLog.settType("彩金");
                    cagentStoredvalueLog.setValue(dividendAmount);

                    cagentStoredvalueLogMapper.insertSelective(cagentStoredvalueLog);
                }

                //保存会员资金流水记录
                UserTreasure userTreasure = new UserTreasure();
                userTreasure.setUid(uid);
                userTreasure.setType("IN");
                userTreasure.settType("存款");
                userTreasure.setAddtime(new Date());
                userTreasure.setNumber(notifyVO.getOrderNo());
                userTreasure.setRmk("存款");
                userTreasure.setCagent(cagentName);
                //若果用户彩金(dividendAmount <= 0)的时候，会员资金流水金额 = 充值金额;变更金额=用户钱包余额,变更后金额=钱包余额+充值金额
                userTreasure.setAmount(Float.parseFloat(new DecimalFormat("####0.00").format(amount)));
                userTreasure.setOldMoney(wallet.floatValue());
                userTreasure.setNewMoney((float) NumberUtil.add(wallet,amount));
                userTreasureMapper.insertSelective(userTreasure);

                //保存代理平台可用储值额度日志
                CagentStoredvalueLog cagentStoredvalueLog = new CagentStoredvalueLog();
                cagentStoredvalueLog.setCid(cid);
                cagentStoredvalueLog.setType("OUT");
                cagentStoredvalueLog.settType("存款");
                cagentStoredvalueLog.setValue(notifyVO.getAmount());
                cagentStoredvalueLogMapper.insertSelective(cagentStoredvalueLog);

                //保存会员资金流水记录
                userTreasureMapper.insertSelective(userTreasure);
                //代理平台可用储值额度日志
                cagentStoredvalueLog.settType("存款");
                cagentStoredvalueLog.setValue(amount);
                cagentStoredvalueLogMapper.insertSelective(cagentStoredvalueLog);

                //写入会员打码量记录
                UserQuantity userQuantity = new UserQuantity();
                userQuantity.setCid(cid);
                userQuantity.setUid(uid);
                userQuantity.setMarkingQuantity(codingAmount);
                userQuantity.setUserQuantity(0.00);
                userQuantity.setWinamount(0.00);
                userQuantity.setWinamount(0.00);
                userQuantityMapper.insertSelectiveByUpdateKey(userQuantity);

                //更新代理平台可用储值额度记录
                CagentStoredvalue cagentStoredvalue = cagentStoredvalueMapper.selectByCid(cid);
                Double userDvaue = NumberUtil.add(cagentStoredvalue.getUsedvaue().doubleValue(),NumberUtil.add(amount,dividendAmount));
                Double remainvalue = NumberUtil.sub(cagentStoredvalue.getRemainvalue().doubleValue(),NumberUtil.sub(amount,dividendAmount));
                cagentStoredvalue.setUsedvaue(userDvaue);
                cagentStoredvalue.setRemainvalue(remainvalue);
                cagentStoredvalueMapper.updateByPrimaryKeySelective(cagentStoredvalue);

                //更新用户钱包余额;钱包余额=充值金额 + 彩金 + 钱包余额
                user.setWallet((float)NumberUtil.add(NumberUtil.add(dividendAmount,amount),wallet.doubleValue()));
                userMapper.updateByPrimaryKeySelective(user);
            }else {
                recharge.setTradeStatus(tradeStatus);
            }
            //修改订单信息
            rechargeMapper.updateByOrderNo(recharge);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("写入用户彩金和打码量异常:"+e.getMessage());
            throw new Exception("写入用户彩金和打码量异常");
        }
    }

    /**
     * 功能描述:
     * 功能描述:
     * 重构支付反射接口
     * 平台商名称，如：凯发娱乐 KFY 澳门银河 YHH 等等
     * 支付渠道，如 网银 bank 微信 wx 支付宝 ali 财付通(QQ钱包) cft等等
     * 平台商的配置文件，包含 商户号、回调地址、支付地址、秘钥等等
     * @Author: Hardy
     * @Date: 2018年08月30日 12:40:32
     * @param config
     * @return: com.sky.pay.reflect.pay.ReflectPayService
     **/
    private ReflectPayService getReflectPayService(CagentYsepay config,String payChannel) throws RPCPayException {
        try {
            String constructorType = "";
            String configType = null;
            //特殊自定义获取配置文件类型的
            if (PayConstant.CONSTANT_SF.equals(config.getPaymentName()) || PayConstant.CONSTANT_MJF.equals(config.getPaymentName())) {
                constructorType = "1";//多加一个参数
                configType = payChannel;//网银 bank
            }
            //获取平台商配置文件
            Map<String, String> data = new HashMap<>();
            JSONObject jsonObject = JSONUtil.parseObj(config.getPaymentConfig());
            Iterator<String> iterator = jsonObject.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = jsonObject.getStr(key);
                data.put(key, value);
            }
            //通过反射获取对应支付商的支付函数
            ReflectPayService reflectPayService = null;
            if (constructorType.equals("1")) {
                reflectPayService = ReflectClazzUtil.getPayReflectClazzName(config.getPaymentName().toUpperCase(), data, configType);
            } else {
                reflectPayService = ReflectClazzUtil.getPayReflectClazzName(config.getPaymentName().toUpperCase(), data);
            }
            return reflectPayService;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("加载支付反射类异常:" + e.getMessage());
            throw new RPCPayException("error", "加载反射类异常!", e.getMessage());
        }
    }
}
