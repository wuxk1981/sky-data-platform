/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    TXPReflectPayServiceImpl.java 
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
 *    Create at:   2018年08月31日 10:23 
 *
 *    Revision: 
 *
 *    2018/8/31 10:23 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.reflect.pay.impl;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.enums.ViewEnum;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.PayResponse;
import com.sky.pay.reflect.pay.ReflectPayService;
import com.sky.pay.util.HttpUtil;
import com.sky.pay.util.MD5Util;
import com.sky.pay.util.PayUtil;
import com.sky.pay.vo.PayConfigVO;
import com.xiaoleilu.hutool.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  * @ClassName TXPReflectPayServiceImpl
 *  * @Description 天信支付
 *  * @Author Hardy
 *  * @Date 2018年08月31日 10:23
 *  * @Version 1.0.0
 *  
 **/
public class TXPReflectPayServiceImpl implements ReflectPayService {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(TXPReflectPayServiceImpl.class);
    private String payUrl;
    private String pay_memberid;
    private String keyValue;// MD5key
    private String pay_notifyurl;
    private String pay_productname;

    public TXPReflectPayServiceImpl(Map<String, String> pmap) {
        if (null != pmap) {
            if(pmap.containsKey("payUrl")){
                this.payUrl = pmap.get("payUrl");
            }
            if(pmap.containsKey("pay_memberid")){
                this.pay_memberid = pmap.get("pay_memberid");
            }
            if(pmap.containsKey("keyValue")){
                this.keyValue = pmap.get("keyValue");
            }
            if(pmap.containsKey("pay_notifyurl")){
                this.pay_notifyurl = pmap.get("pay_notifyurl");
            }
            if(pmap.containsKey("pay_productname")){
                this.pay_productname = pmap.get("pay_productname");
            }
        }
    }

    /**
     * 功能描述:
     * 网银支付
     * @Author: Hardy
     * @Date: 2018年08月31日 10:23:57
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
     * @Date: 2018年08月31日 10:24:09
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        try {
            //组装支付参数
            Map<String,String> data = sealRequest(config);
            //发起支付请求
            String response = HttpUtil.generatorForm(data,payUrl);
            return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.FORM.getType(),
                    ViewEnum.FORM.getCode(),"支付成功!",response);
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("扫码支付异常:"+e.getMessage());
            throw new RPCPayException("error",e.getErrorMsg(),e.getMessage());
        }
    }

    @Override
    public boolean notify(Map<String, String> data) {
        return false;
    }

    /**
     * 功能描述:
     * 装配请求参数
     * @Author: Hardy
     * @Date: 2018年08月31日 10:33:09
     * @param config
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    private Map<String,String> sealRequest(PayConfigVO config) throws RPCPayException{
        try {
            //格式化订单金额
            String amount = new DecimalFormat("############").format(config.getAmount());
            Map<String, String> map = new HashMap<>();
            map.put("pay_bankcode", config.getPayCode());
            map.put("pay_orderid", config.getOrderNo());
            map.put("pay_callbackurl", config.getRetrunUrl());
            map.put("pay_amount", amount);
            map.put("pay_memberid", pay_memberid);
            String pay_applydate = DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");
            map.put("pay_applydate", pay_applydate);
            String stringSignTemp = "pay_amount=" + amount + "&pay_applydate=" + pay_applydate + "&pay_bankcode="
                    + config.getPayCode() + "&pay_callbackurl=" + config.getRetrunUrl() + "&pay_memberid=" + pay_memberid
                    + "&pay_notifyurl=" + pay_notifyurl + "&pay_orderid=" + config.getOrderNo() + "&key=" + keyValue + "";
            logger.info("待签名字符串:" + stringSignTemp);
            String pay_md5sign = MD5Util.encryptToUpper_32bit(stringSignTemp);
            map.put("pay_md5sign", pay_md5sign);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("装配支付请求参数异常:"+e.getMessage());
            throw new RPCPayException("error","装配支付请求参数异常",e.getMessage());
        }
    }
}
