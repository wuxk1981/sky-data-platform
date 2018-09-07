/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    XINFAReflectPayServiceImpl.java 
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
 *    Create at:   2018年08月30日 21:31 
 *
 *    Revision: 
 *
 *    2018/8/30 21:31 
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
import com.sky.pay.util.MD5Util;
import com.sky.pay.util.xinfa.ToolKit;
import com.sky.pay.vo.PayConfigVO;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 *  * @ClassName XINFAReflectPayServiceImpl
 *  * @Description 鑫发支付
 *  * @Author Hardy
 *  * @Date 2018年08月30日 21:31
 *  * @Version 1.0.0
 *  
 **/
public class XINFAReflectPayServiceImpl implements ReflectPayService {

    private final static Logger logger = LoggerFactory.getLogger(XINFAReflectPayServiceImpl.class);
    static String merchNo ;// 商户号
    static String key ;   // 签名MD5密钥,24位
    static String reqUrl ;// 支付地址

    static String version;
    static String goodsName;
    static String notifyUrl;

    static String PAY_PUBLIC_KEY;// 支付公钥

    public static String MECHA_PRIVATE_KEY;// 商家支付私钥

    public XINFAReflectPayServiceImpl(Map<String, String> pmap) {
        if (pmap != null) {
            if (pmap.containsKey("merchNo")) {
                this.merchNo = pmap.get("merchNo");
            }
            if (pmap.containsKey("key")) {
                this.key = pmap.get("key");
            }
            if (pmap.containsKey("reqUrl")) {
                this.reqUrl = pmap.get("reqUrl");
            }
            if (pmap.containsKey("notifyUrl")) {
                this.notifyUrl = pmap.get("notifyUrl");
            }
            if (pmap.containsKey("version")) {
                this.version = pmap.get("version");
            }
            if (pmap.containsKey("goodsName")) {
                this.goodsName = pmap.get("goodsName");
            }
            if (pmap.containsKey("PAY_PUBLIC_KEY")) {
                this.PAY_PUBLIC_KEY = pmap.get("PAY_PUBLIC_KEY");
            }
            if (pmap.containsKey("MECHA_PRIVATE_KEY")) {
                this.MECHA_PRIVATE_KEY = pmap.get("MECHA_PRIVATE_KEY");
            }
        }
    }

    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        return null;
    }

    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        try {
            //封装支付请求参数
            Map<String,String> data = sealRequest(config);

            //生成签名
            String reqParam = generatorSign(data);

            //发起支付
            return sealResponse(reqParam,config.getEnterChannel());
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("error",e.getErrorCode(),e.getMessage());
            throw new RPCPayException("error",e.getErrorMsg(),e.getMessage());
        }
    }

    @Override
    public boolean notify(Map<String, String> data){
        return false;
    }


    /**
     * 功能描述:
     * 封装请求参数
     * @Author: Hardy
     * @Date: 2018年08月30日 21:33:54
     * @param config
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    private Map<String,String> sealRequest(PayConfigVO config) throws RPCPayException{
        try {
            String amount = new DecimalFormat("#").format(NumberUtil.mul(config.getAmount(),100));
            Map<String, String> metaSignMap = new TreeMap<>();
            metaSignMap.put("orderNo", config.getOrderNo());
            metaSignMap.put("version", version);
            metaSignMap.put("charsetCode", ToolKit.CHARSET);//
            metaSignMap.put("randomNum",RandomUtil.randomString(4));// 4位随机数
            metaSignMap.put("merchNo", merchNo);
            metaSignMap.put("payType", config.getPayCode());// WX:微信支付,ZFB:支付宝支付
            metaSignMap.put("amount", amount);// 单位:分
            metaSignMap.put("goodsName", goodsName);// 商品名称：20位
            metaSignMap.put("notifyUrl", notifyUrl);// 回调地址
            metaSignMap.put("notifyViewUrl", config.getRetrunUrl());// 回显地址
            return metaSignMap;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("封装支付请求参数异常:"+e.getMessage());
            throw new RPCPayException("error","封装支付请求参数异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 生成签名
     * @Author: Hardy
     * @Date: 2018年08月30日 21:43:17
     * @param
     * @return: java.lang.String
     **/
    private String generatorSign(Map<String,String> data) throws RPCPayException{
        try {
            String metaSignJsonStr = ToolKit.mapToJson(data);
            String sign = ToolKit.MD5(metaSignJsonStr + key, ToolKit.CHARSET);// 32位
            System.out.println("sign=" + sign); // 英文字母大写
            data.put("sign", sign);

            byte[] dataStr = ToolKit.encryptByPublicKey(ToolKit.mapToJson(data).getBytes(ToolKit.CHARSET),
                    ToolKit.PAY_PUBLIC_KEY);
            String param = new BASE64Encoder().encode(dataStr);
            String reqParam = "data=" + URLEncoder.encode(param, ToolKit.CHARSET) + "&merchNo=" + data.get("merchNo");
            return reqParam;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("生成签名异常:"+e.getMessage());
            throw new RPCPayException("error","生成签名异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 封装支付返回参数
     * @Author: Hardy
     * @Date: 2018年08月30日 21:59:15
     * @param reqParam
     * @return: com.sky.pay.po.PayResponse
     **/
    private PayResponse sealResponse(String reqParam,Integer enterChannel) throws RPCPayException{
        logger.info("{XINFAPay}鑫发支付开始==========================start====================");
        try {
            String resultJsonStr = ToolKit.request(reqUrl, reqParam);
            if(StrUtil.isBlank(resultJsonStr)){
                return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败!",resultJsonStr);
            }
            // 检查状态
            JSONObject resultJsonObj = JSONUtil.parseObj(resultJsonStr);
            String stateCode = resultJsonObj.getStr("stateCode");
            String resultSign = resultJsonObj.getStr("sign");
            resultJsonObj.remove("sign");
            String targetString = ToolKit.MD5(resultJsonObj.toString() + key, ToolKit.CHARSET);
            if (targetString.equals(resultSign)) {
                System.out.println("签名校验成功");
            }
            if (stateCode.equals("00") && targetString.equals(resultSign)) {
                //判断进入渠道
                if (EnterChannelEnum.PC.getType() == enterChannel){
                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRCODE.getType(),
                            ViewEnum.QRCODE.getCode(),"支付成功",resultJsonObj.getStr("qrcodeUrl"));
                }else {
                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRURL.getType(),
                            ViewEnum.QRURL.getCode(),"支付成功",resultJsonObj.getStr("qrcodeUrl"));
                }
            }
            return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败",resultJsonObj);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("支付异常:"+e.getMessage());
            throw new RPCPayException("error","支付异常",e.getMessage());
        }
    }
}
