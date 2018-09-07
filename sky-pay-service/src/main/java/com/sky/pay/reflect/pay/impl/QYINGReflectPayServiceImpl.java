/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    QIANQYINGReflectPayServiceImpl.java 
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
 *    Create at:   2018年08月31日 20:07 
 *
 *    Revision: 
 *
 *    2018/8/31 20:07 
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
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *  * @ClassName QIANQYINGReflectPayServiceImpl
 *  * @Description 千应支付
 *  * @Author Hardy
 *  * @Date 2018年08月31日 20:07
 *  * @Version 1.0.0
 *  
 **/
public class QYINGReflectPayServiceImpl implements ReflectPayService {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(QYINGReflectPayServiceImpl.class);

    private final static String CHARSET_UTF_8="UTF-8";//编码类型

    private String uid;//商户ID，有千应支付分配

    private String callbackurl;//下行异步通知地址

    private String sercet;//签名key

    private String payUrl;//支付地址

    public QYINGReflectPayServiceImpl(Map<String,String> pmap) {

        if (pmap != null && !pmap.isEmpty()){

            if (pmap.containsKey("uid")){
                this.uid = pmap.get("uid");
            }

            if (pmap.containsKey("callbackurl")){
                this.callbackurl = pmap.get("callbackurl");
            }

            if (pmap.containsKey("sercet")){
                this.sercet = pmap.get("sercet");
            }

            if (pmap.containsKey("payUrl")){
                this.payUrl = pmap.get("payUrl");
            }
        }
    }

    /**
     * 功能描述:
     * 网银支付
     * @Author: Hardy
     * @Date: 2018年08月31日 20:07:39
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
     * @Date: 2018年08月31日 20:07:47
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        logger.info("{QIANYINGPay}千应扫码支付开始=======================start================");
        try {
            //封装支付请求参数
            Map<String,String> map = sealRequest(config);
            return sealResponse(map,config.getEnterChannel());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{QIANYINGPay}千应扫码支付异常:"+e.getMessage());
            throw new RPCPayException("error","千应扫码支付异常!",e.getMessage());
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
     * @Date: 2018年08月31日 20:24:50
     * @param config
     * @return: java.util.Map<java.lang.String   ,   java.lang.String>
     **/
    private Map<String,String> sealRequest(PayConfigVO config) throws RPCPayException{
        try {
            //订单金额:单位元（人民币），正整数（不能带小数，最小支付金额为1）
            String amount = new DecimalFormat("##").format(config.getAmount());
            Map<String,String> map = new HashMap<>();
            map.put("uid",uid);
            map.put("type",config.getPayCode());
            map.put("m",amount);
            map.put("orderid",config.getOrderNo());
            map.put("callbackurl",callbackurl);
            //待签名串
            String signStr = "uid="+uid+"&type=101&m="+amount+"&orderid="+config.getOrderNo()+"&callbackurl="+callbackurl+sercet;
            //进行MD5加密签名,32位小写
            String sign = MD5Util.encryptToLower_32bit(signStr);
            map.put("uuid", String.valueOf(config.getUid()));//系统用户ID
            map.put("sign", sign);//签名
            map.put("gofalse",config.getRetrunUrl());//同步回调地址
            map.put("gotrue",config.getRetrunUrl());//同步回调地址
            map.put("charset",CHARSET_UTF_8);
            map.put("ts",System.currentTimeMillis()+"");//订单时间
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("封装支付请求参数异常:"+e.getMessage());
            throw new RPCPayException("error","封装支付请求参数异常!",e.getMessage());
        }
    }

    private PayResponse sealResponse(Map<String,String> data,Integer enterChannel) throws RPCPayException{
        try {
            String response = HttpUtil.toPostForm(data, payUrl);
            if(StrUtil.isNotBlank(response)){
                JSONObject json = JSONUtil.parseObj(response);
                String status = json.getStr("Status");//状态 0失败 1 成功
                if(status.equals("1")){
                    //成功
                    String QRTxt = json.getStr("QRTxt");//二维码解析路径
                    String QRImg = json.getStr("QRImg");//二维码图片
                    if(EnterChannelEnum.PC.getType()==enterChannel){
                        return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRCODE.getType(),
                                ViewEnum.QRCODE.getCode(),"支付成功",QRImg);
                    }else {
                        return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRURL.getType(),
                                ViewEnum.QRURL.getCode(),"支付成功",QRTxt);
                    }
                }
            }
            return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.SUCCESS_CODE,"支付失败",response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{QIANYINGPay}千应支付发起支付请求异常:"+e.getMessage());
            throw new RPCPayException("error","支付发起支付请求异常",e.getMessage());
        }
    }
}
