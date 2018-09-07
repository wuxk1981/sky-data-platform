/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    RCZFReflectPayServiceImpl.java 
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
 *    Create at:   2018年08月29日 21:10 
 *
 *    Revision: 
 *
 *    2018/8/29 21:10 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.reflect.pay.impl;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.enums.PayChannelEnum;
import com.sky.pay.enums.ViewEnum;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.PayResponse;
import com.sky.pay.reflect.pay.ReflectPayService;
import com.sky.pay.util.HttpUtil;
import com.sky.pay.util.RSAUtil;
import com.sky.pay.vo.PayConfigVO;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.*;

/**
 *  * @ClassName RCZFReflectPayServiceImpl
 *  * @Description 融灿支付(作废)
 *  * @Author Hardy
 *  * @Date 2018年08月29日 21:10
 *  * @Version 1.0.0
 *  
 **/
@Deprecated
public class RCZFReflectPayServiceImpl implements ReflectPayService {

    private final static Logger logger = LoggerFactory.getLogger(RCZFReflectPayServiceImpl.class);

    private String privateKey ;//= "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMMm1rHzTn99rMDQv0CW6jcA8spjCFejkZBcdyp5kPqGuII3QJXKH9TV68Uz+7A3aFeJvTJ7xVT4/2+L3xq3FpEqqHBkCTfDkHf7GB4AhzJymBdXkJC7wYLvhYGNf59mvUqnm6SU0XktOZb6itKvnLIyupQFJqDKE54HeLMBQxYrAgMBAAECgYAobb5ipT4o6VdFprlIXztsY7TourV6uncoig9h7Eddr1VAHMQzg+kuRZcPhqJoskHaiL16XOvXm7IHYNm6hh2VXh1ZMtrKaV7bA7FLBmKSNV39Y/MIGzgLMxS96mt2JnxD2LRWDV1KyzQlmBNSf5lijhuQcttm+a7BAV7NSnNJCQJBAPsRn3+GMcRvY8Vt/VcldS/PVDtiAkmTogIluKwCQnrAiAr+PG/tLRY5PLTnh/DgAmZYJCyM06c2UtO/OgQEV+0CQQDG/BBMsC1YRch+qrx9J5P3+yX9MceXkRGvXBuiWspNmBDmaVoCGNgjd0cN3XgOXZ1i3gri4TqSSUQI3qlxYjN3AkAefZJoM0zh9UEhnezxY2wq5TvuhkWO1+4J4rjdstyN+cnLw/plAWHDXCoiMigRObMw6K1j96pQmUlPy95o1Ho1AkA+RdhcB67JN12dtpUyndZC/0hOSuvp1S6xsKO9VaiGTBbN5R6UFW5e+w8zmaHe7RE6Rb8mbdJEwcUW+YgRwefVAkEA+uSciKgIfuYIrytDmDH2IFpBHIkZCKwgK4tREspKvfNhq6hrJ+VTcc8umOY0XdSujBsakvnQHYVjIx+OyESu0A==";
    private String publicKey ;//= "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0sJMGLD0UQUYObjsMHBGUYQEVEOCkBCNzzkYWSM0RYToK49hLpmxpNLbNcSMSUwOs6AfzDW9Tbpcotjg4JiphZqrBjG4Vj2acQPxBp06oJBYdvoCM42AFFLthHNDTmP+O7OYPrwiTTSYPlIUO8HyojhfQ6Dc9guiit7L98FWhmQIDAQAB";
    private String payUrl ;//= "https://api.rcpays.com/gateway/bank";
    private String merchantCode ;//= "M0000001";// 商户号
    private String remark ;//= "pay";// 订单描述
    private String notifyUrl ;//= "http://www.baidu.com";//回调地址
    private String charset ;//= "UTF-8";//编码

    public RCZFReflectPayServiceImpl(Map<String, String> pmap) {
        if(pmap != null) {
            if (pmap.containsKey("privateKey")) {
                this.privateKey = pmap.get("privateKey");
            }
            if (pmap.containsKey("publicKey")) {
                this.publicKey = pmap.get("publicKey");
            }
            if (pmap.containsKey("payUrl")) {
                this.payUrl = pmap.get("payUrl");
            }
            if (pmap.containsKey("merchantCode")) {
                this.merchantCode = pmap.get("merchantCode");
            }
            if (pmap.containsKey("remark")) {
                this.remark = pmap.get("remark");
            }
            if (pmap.containsKey("notifyUrl")) {
                this.notifyUrl = pmap.get("notifyUrl");
            }
            if (pmap.containsKey("charset")) {
                this.charset = pmap.get("charset");
            }
        }
    }

    /*
     * 功能描述:
     * 网银支付
     * @Author: Hardy
     * @Date: 2018年08月29日 21:11:38
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        logger.info("{RCPay}荣灿网银支付开始================================start=======================");
        try {
            //封装网银支付请求参数
            Map<String,String> data = sealRequest(config);
            logger.info("{RCPay}荣灿支付封装请求参数:"+JSONUtil.toJsonStr(data));
            //生成签名串
            String sign = generatorSign(data,config.getPayChannel());
            logger.info("RCPay}荣灿支付签名字符串:"+sign);
            data.put("sign", sign);
            //发起支付，获取支付返回结果
            return sealResponse(data,config.getPayChannel());
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("{RCPay}荣灿网银支付异常:"+e.getMessage());
            throw new RPCPayException("error",e.getErrorMsg(),e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 扫码支付
     * @Author: Hardy
     * @Date: 2018年08月29日 21:11:52
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        try {
            //封装扫码支付请求参数
            Map<String,String> data = sealRequest(config);
            logger.info("{RCPay}荣灿支付封装请求参数:"+JSONUtil.toJsonStr(data));
            //生成签名串
            String sign = generatorSign(data,config.getPayChannel());
            logger.info("RCPay}荣灿支付签名字符串:"+sign);
            data.put("sign", sign);
            //发起支付，获取支付返回结果
            return sealResponse(data,config.getPayChannel());
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("{RCPay}荣灿扫码支付异常:"+e.getMessage());
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
     * @Date: 2018年08月29日 21:15:29
     * @param config
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    private Map<String,String> sealRequest(PayConfigVO config) throws RPCPayException{
        try {
            String orderNo = config.getOrderNo();//new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());// 商户订单号
            String amount = new DecimalFormat("#").format(NumberUtil.mul(config.getAmount(),100));// 金额
            String returnUrl = config.getRetrunUrl();
            Map<String, String> params = new HashMap<>();
            params.put("merchantCode", merchantCode);
            params.put("orderNo", orderNo);
            params.put("charset", charset);
            params.put("amount", amount);
            params.put("notifyUrl", notifyUrl);
            params.put("remark", remark);
            params.put("returnUrl", returnUrl);
            params.put("extraReturnParam", "");
            params.put("signType", "RSA");// 签名方式（不参与签名）
            //根据支付渠道组装不同的参数
            if(PayChannelEnum.BANK.getCode().equals(config.getPayChannel())){
                //网银支付参数
                params.put("channel", "BANK");//通道
                params.put("bankCode", "CASHIER");//// 收银台支付方式
            }else {
                params.put("channel", config.getPayCode());
            }
            return params;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{RCPay}荣灿支付组装支付参数异常:"+e.getMessage());
            throw new RPCPayException("error","组装支付参数异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 生成签名串
     * @Author: Hardy
     * @Date: 2018年08月29日 21:26:37
     * @param data
     * @return: java.lang.String
     **/
    private String generatorSign(Map<String,String> data,String payChannel)throws RPCPayException{
        // 拼接签名字符串，顺序固定
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("charset").append(data.get("charset"));
            sb.append("&merchantCode").append(data.get("merchantCode"));
            sb.append("&orderNo").append(data.get("orderNo"));
            sb.append("&amount").append(data.get("amount"));
            sb.append("&channel").append(data.get("channel"));
            sb.append("&remark").append(data.get("remark"));
            sb.append("&notifyUrl").append(data.get("notifyUrl"));
            sb.append("&returnUrl").append(data.get("returnUrl"));
            sb.append("&extraReturnParam").append(data.get("extraReturnParam"));
            if (PayChannelEnum.BANK.getCode().equals(payChannel)){
                //网银支付签名字符串
                sb.append("&bankCode").append(data.get("bankCode"));
            }
            String sign = sb.toString();
            logger.info("{RCPay}荣灿支付待签名字符串:"+sign);
            return RSAUtil.sign(sign,privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{RCPay}荣灿支付生成签名串异常:"+e.getMessage());
            throw new RPCPayException("error","签名异常",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 发起支付，获取支付结果
     * @Author: Hardy
     * @Date: 2018年08月29日 21:34:44
     * @param data
     * @return: com.sky.pay.po.PayResponse
     **/
    private PayResponse sealResponse(Map<String,String> data,String payChannel) throws RPCPayException{
        //发起支付
        try {
            String response = null;
            if(PayChannelEnum.BANK.getCode().equals(payChannel)){
                //网银
                response = HttpUtil.generatorForm(data,payUrl);
                return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.FORM.getType(),
                                                ViewEnum.FORM.getCode(),"支付成功",response);
            }else{
                payUrl = "https://api.rcpays.com/gateway/scanpay";
                //拼装请求参数
                String params = generatorStrParams(data);
                logger.info("{RCPay}荣灿扫码支付请求参数字符串:"+params);
                response = HttpUtil.toPostJson(params,payUrl);
                if(StrUtil.isBlank(response)){
                    logger.info("{RCPay}荣灿支付失败!");
                    throw new RPCPayException("error","{RCPay}荣灿支付失败,支付结果为:"+response);
                }
                logger.info("{RCPay}荣灿支付:"+JSONUtil.toJsonStr(response));
                JSONObject json = JSONUtil.parseObj(response);
                return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败",response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{RCPay}荣灿支付异常:"+e.getMessage());
            throw new RPCPayException("error","{RCPay}荣灿支付异常:",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 生成扫码支付字符串请求参数
     * @Author: Hardy
     * @Date: 2018年08月29日 22:44:32
     * @param data
     * @return: java.lang.String
     **/
    private String generatorStrParams(Map<String,String>data) throws RPCPayException{
        try {
            //排序
            Map<String,String> treemap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            //按照升序排序
            treemap.putAll(data);

            //封装String字符串
            StringBuffer sb = new StringBuffer();
            Iterator<String> iterator = data.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                String value = data.get(key);
                sb.append("&").append(key).append("=").append(value);
            }
            String params = sb.toString();
            return params.replaceFirst("&","");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("拼装扫码支付参数异常:"+e.getMessage());
            throw new RPCPayException("error","拼装扫码支付参数异常!",e.getMessage());
        }
    }
}
