/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    HLSYReflectPayServiceImpl.java 
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
 *    Create at:   2018年08月29日 17:56 
 *
 *    Revision: 
 *
 *    2018/8/29 17:56 
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
 *  * @ClassName HLSYReflectPayServiceImpl
 *  * @Description 华菱盛业支付
 *  * @Author Hardy
 *  * @Date 2018年08月29日 17:56
 *  * @Version 1.0.0
 *  
 **/
public class HLSYReflectPayServiceImpl implements ReflectPayService {

    private final static Logger logger = LoggerFactory.getLogger(HLSYReflectPayServiceImpl.class);

    private String merchantNo;// 商户编号

    private String notifyUrl;// 通知地址

    private String payUrl;// 支付地址

    private String secret;//MD5签名key


    public HLSYReflectPayServiceImpl(Map<String, String> pmap) {
        if (pmap != null) {
            if (pmap.containsKey("p1_MerchantNo")) {
                merchantNo = pmap.get("p1_MerchantNo");
            }
            if (pmap.containsKey("p6_NotifyUrl")) {
                notifyUrl = pmap.get("p6_NotifyUrl");
            }
            if (pmap.containsKey("H5_pay_url")) {
                payUrl = pmap.get("H5_pay_url");
            }
            if (pmap.containsKey("MD5Key")) {
                secret = pmap.get("MD5Key");
            }
        }
    }

    /**
     * 功能描述:
     * 网页支付
     * @Author: Hardy
     * @Date: 2018年08月29日 17:57:00
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        return null;
    }

    /**
     * 功能描述:
     * 扫码支付
     * @Author: Hardy
     * @Date: 2018年08月29日 17:57:10
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        logger.info("发起支付开始==============================Start=========================");
        try {
            //组装支付请求参数
            Map<String,String> data = sealRequest(config);

            //生成支付签名
            String sign = generatorSign(data);

            //发起支付
            data.put("sign",sign);

            return sealResponse(data,config);
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("支付异常:"+e.getErrorMsg());
            throw new RPCPayException("error",e.getErrorMsg(),e.getMessage());
        }
    }

    @Override
    public boolean notify(Map<String, String> data){
        return false;
    }

    /**
     * 功能描述:
     * 组装支付请求参数
     * @Author: Hardy
     * @Date: 2018年08月29日 18:03:56
     * @param config
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    private Map<String,String> sealRequest(PayConfigVO config) throws  RPCPayException{
        logger.info("组装支付请求参数开始====================Start============================");
        //去掉小数点后的数字，并且以分为单位
        try {
            String price = new DecimalFormat("#").format(NumberUtil.mul(config.getAmount(),100));
            Map<String,String> data = new HashMap<>();
            data.put("p1_MerchantNo", merchantNo);              //商户编号
            data.put("p2_OrderNo", config.getOrderNo());        //商户订单号
            data.put("p3_Amount", price);                       //订单金额
            data.put("p4_Cur", "1");                            //币种
            data.put("p5_ProductName", "充值");                 //商户名称
            data.put("p6_NotifyUrl", notifyUrl);  // 通知地址
            data.put("p7_ReturnUrl", config.getRetrunUrl());  // 回调地址
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("组装支付请求参数异常:"+e.getMessage());
            throw new RPCPayException("error","组装支付请求参数异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 生成md5签名串
     * @Author: Hardy
     * @Date: 2018年08月29日 18:36:06
     * @param data
     * @return: java.lang.String
     **/
    public String generatorSign(Map<String,String> data)throws RPCPayException{
        logger.info("生成支付签名开始================start=========================");
        try {
            //组装签名串
            StringBuffer sb = new StringBuffer();
            if(data.containsKey("p1_MerchantNo") && StrUtil.isNotBlank(data.get("p1_MerchantNo"))){
                sb.append(data.get("p1_MerchantNo"));
            }
            if(data.containsKey("p2_OrderNo") && StrUtil.isNotBlank(data.get("p2_OrderNo"))){
                sb.append(data.get("p2_OrderNo"));
            }
            if(data.containsKey("p3_Amount") && StrUtil.isNotBlank(data.get("p3_Amount"))){
                sb.append(data.get("p3_Amount"));
            }
            if(data.containsKey("p4_Cur") && StrUtil.isNotBlank(data.get("p4_Cur"))){
                sb.append(data.get("p4_Cur"));
            }
            if(data.containsKey("p5_ProductName") && StrUtil.isNotBlank(data.get("p5_ProductName"))){
                sb.append(data.get("p5_ProductName"));
            }
            if(data.containsKey("p6_NotifyUrl") && StrUtil.isNotBlank(data.get("p6_NotifyUrl"))){
                sb.append(data.get("p6_NotifyUrl"));
            }
            if(data.containsKey("p7_ReturnUrl") && StrUtil.isNotBlank(data.get("p7_ReturnUrl"))){
                sb.append(data.get("p7_ReturnUrl"));
            }
            logger.info("待签名字符串:"+sb.toString());
            //生成签名串
            return MD5Util.encryptToLower_32bit(sb.toString()+secret);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("生成签名异常:"+e.getMessage());
            throw new RPCPayException("error","签名异常",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 封装支付返回结果
     * @Author: Hardy
     * @Date: 2018年08月29日 20:23:42
     * @param data
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    private PayResponse sealResponse(Map<String,String> data,PayConfigVO config) throws RPCPayException{
        try {
            String response = HttpUtil.toPostForm(data,payUrl);
            if(StrUtil.isBlank(response)){
                logger.info("支付结果为空！"+response);
                return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败",response);
            }
            //转json
            JSONObject json = JSONUtil.parseObj(response);
            if(json.getStr("retCode").equals("00000")){

                if(EnterChannelEnum.PC.getType() == config.getEnterChannel()){
                    //PC端进来的
                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRCODE.getType(),
                            ViewEnum.QRCODE.getCode(),"支付成功",response);
                }else {
                    //移动端
                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRURL.getType(),
                            ViewEnum.QRURL.getCode(),"支付成功",response);
                }
            }
            return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败",response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("发起支付异常:"+e.getMessage());
            throw new RPCPayException("error","发起支付异常",e.getMessage());
        }
    }
}
