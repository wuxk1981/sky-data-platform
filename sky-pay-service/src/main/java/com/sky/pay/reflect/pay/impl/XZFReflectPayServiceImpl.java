/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    XZFReflectPayServiceImpl.java 
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
 *    Create at:   2018年09月01日 14:44 
 *
 *    Revision: 
 *
 *    2018/9/1 14:44 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.reflect.pay.impl;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.enums.EnterChannelEnum;
import com.sky.pay.enums.PayChannelEnum;
import com.sky.pay.enums.ViewEnum;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.PayResponse;
import com.sky.pay.reflect.pay.ReflectPayService;
import com.sky.pay.util.HttpUtil;
import com.sky.pay.util.MD5Util;
import com.sky.pay.vo.PayConfigVO;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.MapUtil;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *  * @ClassName XZFReflectPayServiceImpl
 *  * @Description 掌付支付
 *  * @Author Hardy
 *  * @Date 2018年09月01日 14:44
 *  * @Version 1.0.0
 *  
 **/
public class XZFReflectPayServiceImpl implements ReflectPayService {

    //日志
    private final static Logger logger = LoggerFactory.getLogger(XZFReflectPayServiceImpl.class);

    /** 测试商户号 **/
    private String appid;
    /** 测试密钥 ***/
    private String apikey;
    /** 支付地址 **/
    private String url;
    /** 回调地址 **/
    private String tongbu_url;
    /** 商品名称 **/
    private String subject;
    /** 商品描述 **/
    private String body;

    public XZFReflectPayServiceImpl(Map<String, String> pmap) {

        //判断配置文件
        if(MapUtil.isNotEmpty(pmap)){
            if(pmap.containsKey("appid")){
                //测试商户号
                this.appid = pmap.get("appid");
            }
            if(pmap.containsKey("apikey")){
                //测试密钥
                this.apikey = pmap.get("apikey");
            }
            if(pmap.containsKey("url")){
                //支付地址
                this.url = pmap.get("url");
            }
            if(pmap.containsKey("tongbu_url")){
                //回调地址
                this.tongbu_url = pmap.get("tongbu_url");
            }
            if(pmap.containsKey("subject")){
                //商品名称
                this.subject = pmap.get("subject");
            }
            if(pmap.containsKey("body")){
                //商品描述
                this.body = pmap.get("body");
            }
        }
    }

    /**
     * 功能描述:
     * 网银支付
     * @Author: Hardy
     * @Date: 2018年09月01日 14:45:10
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        logger.info("{XZFPay}掌付网银支付开始================start=======================");
        try {
            //组装掌付支付请求参数
            Map<String,String> data = sealRequest(config);
            logger.info("{XZFPay}掌付支付请求参数:"+JSONUtil.toJsonStr(data));
            //发起支付请求
            return sealResponse(data,config.getEnterChannel(),config.getPayChannel());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{XZFPay}掌付网银支付异常:"+e.getMessage());
            throw new RPCPayException("error","掌付网银支付异常",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 扫码支付
     * @Author: Hardy
     * @Date: 2018年09月01日 14:45:19
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        logger.info("{XZFPay}掌付扫码支付开始==============================START=========================");
        try {
            //封装支付请求参数
            Map<String,String> data = sealRequest(config);
            logger.info("{XZFPay}掌付支付请求参数:"+JSONUtil.toJsonStr(data));
            //发起支付请求
            return sealResponse(data,config.getEnterChannel(),config.getPayChannel());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{XZFPay}掌付扫码支付异常:"+e.getMessage());
            throw new RPCPayException("error","掌付扫码支付异常",e.getMessage());
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
     * @Date: 2018年09月01日 15:07:28
     * @param config
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    private Map<String,String> sealRequest(PayConfigVO config) throws RPCPayException{
        logger.info("{XZFPay}掌付支付封装请求参数开始=============start=========================");
        try {
            //格式化金额
            String amount = new DecimalFormat("##").format(NumberUtil.mul(config.getAmount(),100));
            Map<String, String> data = new HashMap<>();
            data.put("bankCode", config.getPayCode());
            data.put("orderid", config.getOrderNo());
            data.put("fee", amount);
            data.put("returnUrl", config.getRetrunUrl());
            data.put("clientIp", config.getIp());// ip
            data.put("appid", appid);
            data.put("subject", subject);
            data.put("body", body);
            data.put("tongbu_url", tongbu_url);
            data.put("cpparam", "");
            data.put("mode", "");
            data.put("appname", "");
            data.put("appbs", "");
            data.put("paytype", "0");
            //网银支付
            if(EnterChannelEnum.PC.getType() == config.getEnterChannel()){
                //进入渠道，PC端
                data.put("sfrom", "pc");
            }else {
                //移动端
                data.put("sfrom", "wap");
            }
            if(!PayChannelEnum.BANK.getCode().equals(config.getPayChannel())){
                //不是网银支付
                if(EnterChannelEnum.PC.getType() == config.getEnterChannel()){
                    data.put("user", "vip");
                }
            }
            //待签名串
            String signStr = appid + config.getOrderNo() + amount + tongbu_url + apikey;
            logger.info("{XZFPay}掌付待签名串:"+signStr);
            String sign = MD5Util.encryptToLower_32bit(signStr);
            logger.info("{XZFPay}掌付加密串:"+sign);
            data.put("sign", sign);
            return  data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{XZFPay}掌付支付封装请求参数异常:"+e.getMessage());
            throw new RPCPayException("error","掌付支付封装请求参数异常",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 封装支付返回结果
     * @Author: Hardy
     * @Date: 2018年09月01日 15:15:54
     * @param data 支付请求参数
     * @param enterChannel 进入渠道
     * @param payChannel  支付渠道
     * @return: com.sky.pay.po.PayResponse
     **/
    private PayResponse sealResponse(Map<String,String> data,Integer enterChannel,String payChannel) throws RPCPayException{
        logger.info("{XZFPay}掌付支付调用开始====================START====================================");
        try {
            String response = null;
            //判断是网银支付还是其他支付
            if(PayChannelEnum.BANK.getCode().equals(payChannel)){
                //网银支付，返回一个form表单
                response = HttpUtil.generatorForm(data,url);
                if(StrUtil.isNotBlank(response)){
                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.FORM.getType(),
                            ViewEnum.FORM.getCode(),"支付成功",response);
                }
            }else {
                response = HttpUtil.toPostJson(data,url);
                if (StrUtil.isNotBlank(response)){
                    JSONObject json = JSONUtil.parseObj(response);
                    if("success".equals(json.get("code"))){
                        String qrcode = json.getStr("qrCode");
                        String payCode = data.get("bankCode");
                        if(EnterChannelEnum.PC.getType() == enterChannel){
                            //PC端
                            if ("34".equals(payCode)) {
                                return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRURL.getType(),
                                        ViewEnum.QRURL.getCode(),"支付成功！",qrcode);
                            }else {
                                return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRCODE.getType(),
                                        ViewEnum.QRCODE.getCode(),"支付成功！",qrcode);
                            }
                        }else {
                            //手机端
                            return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.LINK.getType(),
                                    ViewEnum.LINK.getCode(),"支付成功!",qrcode);
                        }
                    }
                }
            }
            return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败",response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{XZFPay}掌付支付调用异常:"+e.getMessage());
            throw new RPCPayException("error","掌付支付调用异常!",e.getMessage());
        }
    }
}
