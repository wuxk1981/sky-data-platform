/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    MJFReflectPayServiceImpl.java 
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
 *    Create at:   2018年08月30日 10:36 
 *
 *    Revision: 
 *
 *    2018/8/30 10:36 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.reflect.pay.impl;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.enums.EnterChannelEnum;
import com.sky.pay.enums.ViewEnum;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.PayResponse;
import com.sky.pay.reflect.pay.ReflectPayService;
import com.sky.pay.util.HttpUtil;
import com.sky.pay.util.MD5Util;
import com.sky.pay.util.mjf.MJFUtil;
import com.sky.pay.vo.PayConfigVO;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 *  * @ClassName MJFReflectPayServiceImpl
 *  * @Description 明捷支付
 *  * @Author Hardy
 *  * @Date 2018年08月30日 10:36
 *  * @Version 1.0.0
 *  
 **/
public class MJFReflectPayServiceImpl implements ReflectPayService {

    //日志
    private final static Logger logger = LoggerFactory.getLogger(MJFReflectPayServiceImpl.class);

    /**商户号*/
    private String merchNo;
    /**商户密钥*/
    private String key;
    /**支付地址*/
    private String reqUrl;
    /**后台回调*/
    private String callBackUrl;
    /**支付公钥*/
    private String pubKey;

    public MJFReflectPayServiceImpl(Map<String, String> pmap,String type) {
        if(pmap != null) {
            //商户号
            if(pmap.containsKey("merchNo")) {
                this.merchNo = pmap.get("merchNo");
            }
            //商户密钥
            if(pmap.containsKey("key")) {
                this.key = pmap.get("key");
            }
            //后台回调地址
            if(pmap.containsKey("callBackUrl")) {
                this.callBackUrl = pmap.get("callBackUrl");
            }
            //支付公钥
            if(pmap.containsKey("pubKey")) {
                this.pubKey = pmap.get("pubKey");
            }
            //支付地址
            if(pmap.containsKey("reqUrl")) {
                this.reqUrl = pmap.get("reqUrl");
                JSONObject json = JSONUtil.parseObj(reqUrl);
                if(json.containsKey(type.toLowerCase())){
                    this.reqUrl = json.getStr(type.toLowerCase());
                }
            }
        }
    }

    /**
     * 功能描述:
     * 网银支付
     * @Author: Hardy
     * @Date: 2018年08月30日 10:37:09
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        return null;
    }

    /**
     * 功能描述:
     * 扫码支付,支付地址：http://39.105.8.4:9803/api/pay 代付地址：http://39.105.8.4:9803/api/remit
     * @Author: Hardy
     * @Date: 2018年08月30日 10:37:24
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        logger.info("{MJFPay}扫码支付开始============================start==========================");
        try {
            //组装支付请求参数
            Map<String, String> data = sealRequest(config);
            if(data == null || data.isEmpty()){
                throw new RPCPayException("error","组装支付请求参数异常!");
            }
            //生成签名
            String sign = generatorSign(data);
            logger.info("{MJFPay}RSA签名字符串:"+sign);
            if(StrUtil.isBlank(sign)){
                throw new RPCPayException("error","生成支付签名串异常!");
            }
            //发起支付,获取支付返回结果
            return sealResponse(sign,data,config.getEnterChannel());
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("{MJFPay}支付异常:"+e.getMessage());
            throw new RPCPayException("error",e.getErrorMsg(),e.getMessage());
        }

    }

    @Override
    public boolean notify(Map<String, String> data){
        return false;
    }

    /**
     * 功能描述:
     * 封装支付请求参数
     * @Author: Hardy
     * @Date: 2018年08月30日 10:38:11
     * @param config
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    private Map<String,String> sealRequest(PayConfigVO config) throws RPCPayException{
        logger.info("组装支付请求参数开始=============================start============================");
        try {
            //格式化订单金额，单位为分
            String amount = new DecimalFormat("#").format(NumberUtil.mul(config.getAmount(),100));
            //封装map参数
            Map<String, String> data  = new HashMap<>();
            data.put("version", "V3.0.0.0");
            data.put("merchNo", merchNo);
            data.put("goodsName", "充值");
            data.put("callBackUrl",callBackUrl);// 后台回调地址
            data.put("charset","UTF-8");
            data.put("randomNum", RandomUtil.randomString(4));// 4位随机数
            data.put("netwayCode", config.getPayCode());// WX:微信支付,ZFB:支付宝支付,QQ
            data.put("orderNum", config.getOrderNo());//长度20
            data.put("amount", amount);// 单位:分
            data.put("callBackViewUrl", config.getRetrunUrl());// 回显地址
            //map转json字符串
            //排序
            String sign = mapToJson(data);
            logger.info("{MJFPay}待签名JSON字符串:"+sign);//参数签名
            //进行MD5加密
            String md5Str = MJFUtil.encrypt(sign);
            logger.info("{MJFPay}MD5加密后的字符串:" + md5Str.toUpperCase());
            //进行RSA加密
            data.put("sign",md5Str.toUpperCase());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("组装支付参数异常:"+e.getMessage());
            throw new RPCPayException("error","组装支付参数异常",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 生成支付签名
     * @Author: Hardy
     * @Date: 2018年08月30日 11:10:06
     * @param data
     * @return: java.lang.String
     **/
    private String generatorSign(Map<String,String> data) throws  RPCPayException{
        logger.info("{MJFPay}生成支付签名开始=======================start========================");
        try {
            //排序
            String rsaStr = mapToJson(data);
            logger.info("待加密字符串:"+rsaStr);
            //组装待RSA加密串,包含md5加密的字符串
            byte[] dataStr = MJFUtil.encryptByPublicKey(rsaStr.getBytes("UTF-8"),pubKey);
            String sign = new BASE64Encoder().encode(dataStr);
            logger.info("{MJFPay}RSA签名串:"+ sign);
            return sign;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{MJFPay}生成支付签名异常:"+e.getMessage());
            throw new RPCPayException("error","生成支付签名异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 封装支付返回结果
     * @Author: Hardy
     * @Date: 2018年08月30日 12:11:55
     * @param sign
     * @param data
     * @return: com.sky.pay.po.PayResponse
     **/
    private PayResponse sealResponse(String sign,Map<String,String> data,Integer enterChannel)throws RPCPayException{
        try {
            //组装发起第三方接口参数
            String reqParam = "data=" + URLEncoder.encode(sign,"UTF-8") + "&merchNo=" + data.get("merchNo") + "&version=" + data.get("version");
            String response = HttpUtil.toPostForm(reqParam,reqUrl);
            if(StrUtil.isBlank(response)){
                logger.info("调用第三方支付接口失败，返回结果为:"+response);
                return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"调用第三方支付接口失败，返回结果为空!",response);
            }

            JSONObject result  = JSONUtil.parseObj(response);
            //支付正确(1.支付状态="00"，2.sign校验成功)
            if(result.get("stateCode").equals("00") && verifySign(response)){
                //支付成功
                if (EnterChannelEnum.PC.getType() == enterChannel){
                    //进入渠道为PC端
                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRCODE.getType(),
                            ViewEnum.QRCODE.getCode(),"支付成功",result);
                }else {
                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.LINK.getType(),
                            ViewEnum.LINK.getCode(),"支付成功",result);
                }
            }
            return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败!",result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{MJFPay}发起支付异常:"+e.getMessage());
            throw new RPCPayException("error","调用第三接口异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 校验签名
     * @Author: Hardy
     * @Date: 2018年08月30日 12:21:16
     * @param result
     * @return: boolean
     **/
    private boolean verifySign(String result)throws RPCPayException{
        try {
            JSONObject json = JSONUtil.parseObj(result);
            //获取返回的签名
            String signStr = json.getStr("sign");
            json.remove("sign");//从json中删掉sing
            String resultStr = MD5Util.encryptToLower_32bit(json.toString() + key);
            if (signStr.equals(resultStr)) {
                logger.info("签名校验成功");
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("校验签名异常:"+e.getMessage());
            throw new RPCPayException("error","校验签名异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * map转json
     * @Author: Hardy
     * @Date: 2018年08月30日 21:22:13
     * @param map
     * @return: java.lang.String
     **/
    private String mapToJson(Map<String, String> map) throws RPCPayException{
        try {
            List<String> list = new ArrayList<>();
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()){
                list.add(iterator.next());
            }
            Collections.sort(list);

            StringBuffer sb = new StringBuffer();
            sb.append("{");
            for (String key : list ) {
                sb.append("\"").append(key).append("\"");
                sb.append(":");
                sb.append("\"").append(map.get(key)).append("\"");
                sb.append(",");
            }
            String params = sb.toString();
            //去掉最后的一个","
            params.substring(0,params.length()-1);
            params += "}";
            System.out.println("mapToJson=" + params);
            return params;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Map转JSON字符串异常:"+e.getMessage());
            throw new RPCPayException("error","数据转换异常!",e.getMessage());
        }
    }

}
