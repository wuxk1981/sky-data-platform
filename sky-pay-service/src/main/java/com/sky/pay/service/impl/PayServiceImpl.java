/******************************************************************
 *    Powered By tianxia-online.
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.pay.service.impl
 *
 *    Filename:    PayServiceImpl.java
 *
 *    Description: 支付
 *
 *    Copyright:   Copyright (c) 2018-2020
 *
 *    Company:     天下网络科技
 *
 *    @author:     Hardy
 *
 *    @version:    1.0.0
 *
 *    Create at:   2018年08月26日 11:33
 *
 *    Revision:
 *
 *    2018/8/26 11:33
 *        - first revision
 *
 *****************************************************************/
package com.sky.pay.service.impl;

import com.sky.pay.constant.PayConstant;
import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.entity.*;
import com.sky.pay.enums.EnterChannelEnum;
import com.sky.pay.enums.PayChannelEnum;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.mapper.*;
import com.sky.pay.po.PayResponse;
import com.sky.pay.po.ResultResponse;
import com.sky.pay.reflect.pay.ReflectPayService;
import com.sky.pay.service.PayService;
import com.sky.pay.util.PayUtil;
import com.sky.pay.util.PropertiesUtil;
import com.sky.pay.util.ReflectClazzUtil;
import com.sky.pay.vo.PayConfigVO;
import com.sky.pay.vo.PayRequest;
import com.xiaoleilu.hutool.bean.BeanUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  * @ClassName PayServiceImpl
 *  * @Description 支付接口
 *  * @Author Hardy
 *  * @Date 2018年08月26日 11:33
 *  * @Version 1.0.0
 *  
 **/
@Service
@Transactional
public class PayServiceImpl implements PayService {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private RechargeMapper rechargeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CagentMapper cagentMapper;

    @Autowired
    private UserChannelMapper userChannelMapper;

    @Autowired
    private CagentYsepayMapper cagentYsepayMapper;

    @Autowired
    private CagentStoredvalueMapper cagentStoredvalueMapper;

    @Override
    public ResultResponse wyPay(PayRequest payRequest) throws RPCPayException {
        //###########################组装支付需要的配置文件############################################//
        try {
            PayConfigVO config = getPayConfigVO(payRequest);
            if (config == null) {
                logger.info("组装支付参数异常!");
                throw new RPCPayException("error", "组装支付参数异常!");
            }
            //##########################################验证金额############################################//
            boolean isVerify = verifyAmount(config);
            if (!isVerify) {
                //验证金额失败
                logger.info("验证金额失败!");
                throw new RPCPayException("error", "验证金额失败!");
            }
            //################################################获取平台商配置文件############################//
            ReflectPayService reflectPayService = getReflectPayService(config);
            if (reflectPayService == null) {
                logger.info("获取支付反射接口失败!");
                throw new RPCPayException("error", "获取支付反射接口失败!");
            }
            //#################################################发起支付######################################//
            PayResponse response = reflectPayService.wyPay(config);
            if (response.getStatus() == 1 && response.getCode().equals("SUCCESS")) {
                //保存支付订单,需要进行try catch处理，且不能跑出异常，为了适订单成功支付，
                //如果订单没有保存成功，可以线下在处理,但支付一定要完成!
                try {
                    saveRecharge(config);
                } catch (RuntimeErrorException e) {
                    e.printStackTrace();
                }
                return ResultResponse.success(ResponseConstant.SUCCESS_STATUS, ResponseConstant.SUCCESS_CODE, "支付请求成功", response);
            }
            return ResultResponse.faild(ResponseConstant.FAILD_STATUS, ResponseConstant.FAILD_CODE, "支付请求失败", response);
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("支付异常:" + e.getErrorMsg());
            throw new RPCPayException("error", "支付异常:"+e.getErrorMsg(), e.getMessage());
        }
    }

    @Override
    public ResultResponse scanPay(PayRequest payRequest) throws RPCPayException {
        logger.info("扫码支付开始==================================start========================");
        //###########################组装支付需要的配置文件############################################//
        try {
            PayConfigVO config = getPayConfigVO(payRequest);
            if (config == null) {
                logger.info("组装支付参数异常!");
                throw new RPCPayException("error", "组装支付参数异常!");
            }
            //##########################################验证金额############################################//
            boolean isVerify = verifyAmount(config);
            if (!isVerify) {
                //验证金额失败
                logger.info("验证金额失败!");
                throw new RPCPayException("error", "验证金额失败!");
            }
            //################################################获取平台商配置文件############################//
            ReflectPayService reflectPayService = getReflectPayService(config);
            if (reflectPayService == null) {
                logger.info("获取支付反射接口失败!");
                throw new RPCPayException("error", "获取支付反射接口失败!");
            }
            PayResponse response = reflectPayService.scanPay(config);
            if (response.getStatus() == 1 && response.getResult().equals("SUCCESS")) {
                //保存支付订单,需要进行try catch处理，且不能跑出异常，为了适订单成功支付，
                //如果订单没有保存成功，可以线下在处理,但支付一定要完成!
                try {
                    saveRecharge(config);
                } catch (RuntimeErrorException e) {
                    e.printStackTrace();
                }
                return ResultResponse.success(ResponseConstant.SUCCESS_STATUS, ResponseConstant.SUCCESS_CODE, "支付请求成功", response);
            }
            return ResultResponse.faild(ResponseConstant.FAILD_STATUS, ResponseConstant.FAILD_CODE, "支付请求失败", response);
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("支付异常:" + e.getErrorMsg());
            throw new RPCPayException("error","支付异常:"+e.getErrorMsg(), e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 获取用户支付配置信息
     *
     * @param payRequest
     * @Author: Hardy
     * @Date: 2018年08月27日 20:40:09
     * @return: com.sky.pay.vo.PayConfigVO
     **/
    private PayConfigVO getPayConfigVO(PayRequest payRequest) throws RPCPayException {
        logger.info("组装支付配置参数开始====================================start");
        try {
            //1.获取用户类型(从缓存中可以获取,此处假装没有缓存),通过用户ID获取用户详情
            User user = userMapper.selectByPrimaryKey(payRequest.getUid());
            if (user == null) {
                logger.info("{PayServiceImpl.getPayConfigVO}查询用户信息异常!");
                throw new Exception("查询用户信息异常!");
            }
            //获取用户分层ID
            Integer typeId = user.getTypeId();
            //通过支付ID获取支付配置信息
            CagentYsepay cagentYsepay = cagentYsepayMapper.selectByPrimaryKey(payRequest.getPayId());

            if (cagentYsepay == null) {
                logger.info("{PayServiceImpl.getPayConfigVO}查询支付商配置信息异常!");
                throw new Exception("查询支付商配置信息异常!");
            }

            //通过支付商ID和用户分层ID获取用户渠道信息
            UserChannel userChannel = userChannelMapper.selectByTypeIdAndPaymentId(typeId, payRequest.getPayId());

            if (userChannel == null) {
                logger.info("{PayServiceImpl.getPayConfigVO}查询用户渠道信息异常!");
                throw new Exception("查询用户渠道信息异常!");
            }

            PayConfigVO config = new PayConfigVO();
            BeanUtil.copyProperties(cagentYsepay, config);
            config.setUsername(user.getUsername());
            config.setMobile(user.getMobile());
            config.setCagent(user.getCagent());//用户所在平台商编码
            config.setChannel(userChannel.getType());
            config.setTypeid(userChannel.getTypeid());
            config.setDividendRate(userChannel.getDividendRate());
            config.setCodingRate(userChannel.getCodingRate());
            config.setUid(payRequest.getUid());
            config.setAmount(payRequest.getAmount());
            config.setIp(payRequest.getIp());
            config.setRetrunUrl(payRequest.getUrl());
            config.setEnterChannel(payRequest.getEnterChannel());
            config.setPayChannel(payRequest.getPayChannel());
            //支付渠道
            String payChannel = payRequest.getPayChannel();//
            if (!PayChannelEnum.BANK.getCode().equals(payChannel)) {
                //如果不为网银支付渠道的时候，则为 微信、支付宝、京东、银联、财付通等等支付方式,然后这些
                //支付方式是最终传递到第三方去的,因此需要通过读取配置文件获取
                Integer enterChannel = payRequest.getEnterChannel();//进入渠道 1 PC 2 移动端 3 APP
                //获取配置文件
                Map<String, String> propertiesMap = new HashMap<>();
                if (enterChannel == EnterChannelEnum.PC.getType()) {
                    //PC端
                    propertiesMap = PropertiesUtil.readPcProperties();
                } else {
                    //移动端
                    propertiesMap = PropertiesUtil.readMobileProperties();
                }
                //初始化一个支付类型的数组，properties配置文件种的均以","规则隔开
                String[] sptNumber = null;
                String paymentName = config.getPaymentName().toUpperCase();//支付商名称
                if (propertiesMap != null && !propertiesMap.isEmpty()) {
                    if (propertiesMap.containsKey(paymentName)) {
                        sptNumber = propertiesMap.get(paymentName).split(",");
                    }
                }
                //有些支付渠道没有payCode值,比如快捷支付等等,因此要排除配置文件中没有的值,但在调用
                //第三方支付的时候，如果第三方需要payCode值得时候需要做非空验证，在这里不控制payCode值是否为空!
                if (sptNumber != null && sptNumber.length > 0) {
                    for (PayChannelEnum payChannelEnum : PayChannelEnum.values()) {
                        if (payChannelEnum.getCode().equals(payChannel)) {
                            //从新设置payCode值
                            config.setPayCode(sptNumber[payChannelEnum.getIndex()]);
                        }
                    }
                }
            }
            //生成订单号
            String orderNo = PayUtil.generatorOrderNo(config.getCagent().toUpperCase(), config.getPaymentName().toUpperCase());
            if (StrUtil.isBlank(orderNo)) {
                throw new Exception("创建订单号失败!");
            }
            config.setOrderNo(orderNo);
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("组装支付配置参数异常:" + e.getMessage());
            throw new RPCPayException("error", e.getMessage(), e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 保存订单信息
     *
     * @param config
     * @Author: Hardy
     * @Date: 2018年08月29日 16:05:50
     * @return: void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void saveRecharge(PayConfigVO config) throws RuntimeErrorException {
        Recharge recharge = new Recharge();
        recharge.setUid(config.getUid());
        recharge.setBankCode(config.getPayCode());

        for (PayChannelEnum payChannelEnum : PayChannelEnum.values()) {
            if (payChannelEnum.getCode().equals(config.getPayChannel())) {
                recharge.setPayType(payChannelEnum.getType().byteValue());
            }
        }
        recharge.setOrderNo(config.getOrderNo());
        recharge.setOrderAmount((float) config.getAmount());
        recharge.setOrderTime(new Date());
        recharge.setTradeStatus("paying");
        recharge.setTradeNo("");
        recharge.setIp(config.getIp());
        recharge.setMerchant(config.getPaymentName());
        recharge.setUpuid(0);
        recharge.setCagent(config.getCagent());
        recharge.setPayId(config.getId());//支付商ID
        rechargeMapper.insertSelective(recharge);
    }

    /**
     * 功能描述:
     * 验证金额
     *
     * @param config
     * @Author: Hardy
     * @Date: 2018年08月28日 20:20:35
     * @return: boolean
     **/
    private boolean verifyAmount(PayConfigVO config) throws RPCPayException {
        try {
            boolean isVerify = true;//默认为真
            double amount = config.getAmount();//订单金额,单位为元
            String payChannel = config.getPayChannel();//支付渠道 网银 bank 微信 wx 支付宝 ali 财付通(QQ钱包)等等
            Integer enterChannel = config.getEnterChannel();//进入渠道 1 PC端 2 移动端 3 APP
            //验证金额,网银的和其他的不同
            if (PayChannelEnum.BANK.getCode().equals(payChannel)) {
                //网银
                if (amount <= 0 || amount < config.getMinquota() || amount > config.getMaxquota()) {
                    logger.info("验证支付金额失败:{请输入大于" + config.getMinquota() + "且小于" + config.getMaxquota() + "之间的金额}");
                    throw new Exception( "验证支付金额失败:{请输入大于" + config.getMinquota() + "且小于" + config.getMaxquota() + "之间的金额}");
                }
            } else if (PayChannelEnum.WX.getCode().equals(payChannel)) {
                //微信
                if (EnterChannelEnum.PC.getType() == enterChannel) {
                    config.setIsh5Wx(1);
                }
                if (amount <= 0 || amount < config.getWxMinquota() || amount > config.getWxMaxquota()) {
                    logger.info("微信支付金额验证失败!");
                    isVerify = false;
                }
            } else if (PayChannelEnum.ALI.getCode().equals(payChannel)) {
                //支付宝
                if (EnterChannelEnum.PC.getType() == enterChannel) {
                    config.setIsh5Ali(1);
                }
                if (amount <= 0 || amount < config.getAliMinquota() || amount > config.getAliMaxquota()) {
                    logger.info("支付宝支付金额验证失败！");
                    isVerify = false;
                }
            } else if (PayChannelEnum.CFT.getCode().equals(payChannel)) {
                //财付通,QQ钱包
                if (EnterChannelEnum.PC.getType() == enterChannel) {
                    config.setIsh5Cft(1);
                }

                if (amount <= 0 || amount < config.getQrminquota() || amount > config.getQrmaxquota()) {
                    logger.info("财付通支付金额验证失败！");
                    isVerify = false;
                }
            } else if (PayChannelEnum.YL.getCode().equals(payChannel)) {
                //银联
                if (EnterChannelEnum.PC.getType() == enterChannel) {
                    config.setIsh5Yl(1);
                }

                if (amount <= 0 || amount < config.getYlMinquota() || amount > config.getYlMaxquota()) {
                    logger.info("银联支付金额验证失败！");
                    isVerify = false;
                }
            } else if (PayChannelEnum.JD.getCode().equals(payChannel)) {
                //京东
                if (EnterChannelEnum.PC.getType() == enterChannel) {
                    config.setIsh5Jd(1);
                }

                if (amount <= 0 || amount < config.getJdMinquota() || amount > config.getJdMaxquota()) {
                    logger.info("京东支付金额验证失败！");
                    isVerify = false;
                }
            } else if (PayChannelEnum.KY.getCode().equals(payChannel)) {
                //快捷
                if (amount <= 0 || amount < config.getKjMinquota() || amount > config.getKjMaxquota()) {
                    logger.info("快捷支付金额验证失败！");
                    isVerify = false;
                }
            } else if (PayChannelEnum.WXTM.getCode().equals(payChannel)) {
                if (amount <= 0 || amount < config.getWxtmMinquota() || amount > config.getWxtmMaxquota()) {
                    logger.info("微信条码金额验证失败！");
                    isVerify = false;
                }
            } else if (PayChannelEnum.ALITM.getCode().equals(payChannel)) {
                if (amount <= 0 || amount < config.getAlitmMinquota() || amount > config.getAlitmMaxquota()) {
                    logger.info("支付宝条码金额验证失败！");
                    isVerify = false;
                }
            }
            //获取平台充值额度
            double remainvalue = 0.00;
            CagentStoredvalue cagentStoredvalue = cagentStoredvalueMapper.selectByCid(config.getCid());
            if (cagentStoredvalue != null) {
                remainvalue = cagentStoredvalue.getRemainvalue();//剩余额度
            }
            if (NumberUtil.sub(remainvalue, amount) <= 0) {
                logger.info("平台额度不足！");
                isVerify = false;
            }
            return isVerify;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("验证金额异常:" + e.getMessage());
            throw new RPCPayException("error", "验证金额失败!", e.getMessage());
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
    private ReflectPayService getReflectPayService(PayConfigVO config) throws RPCPayException {
        try {
            String constructorType = "";
            String configType = null;
            //特殊自定义获取配置文件类型的
            if (PayConstant.CONSTANT_SF.equals(config.getPaymentName())) {
                constructorType = "1";//多加一个参数
                configType = config.getPayChannel();//网银 bank
            }else if(PayConstant.CONSTANT_MJF.equals(config.getPaymentName())){
                //明捷付
                constructorType = "1";//多加一个参数
                configType = config.getPayCode().toLowerCase();
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