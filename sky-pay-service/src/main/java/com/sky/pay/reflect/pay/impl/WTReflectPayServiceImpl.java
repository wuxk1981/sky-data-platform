/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    WTReflectPayServiceImpl.java 
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
 *    Create at:   2018年08月26日 16:51 
 *
 *    Revision: 
 *
 *    2018/8/26 16:51 
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
import com.sky.pay.util.MapSortUtil;
import com.sky.pay.vo.PayConfigVO;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  * @ClassName WTReflectPayServiceImpl
 *  * @Description 万通支付反射支付实现类
 *  * @Author Hardy
 *  * @Date 2018年08月26日 16:51
 *  * @Version 1.0.0
 *  
 **/
public class WTReflectPayServiceImpl implements ReflectPayService {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(WTReflectPayServiceImpl.class);

    // 商户id
    private String uid;

    // 商户secret
    private String secret;

    // 支付url
    private String payUrl;

    // 回调url
    private String notifyUrl;

    public WTReflectPayServiceImpl(Map<String, String> pmap) {
        if (pmap != null) {
            if (pmap.containsKey("uid")) {
                uid = pmap.get("uid");
            }
            if (pmap.containsKey("secret")) {
                secret = pmap.get("secret");
            }
            if (pmap.containsKey("payUrl")) {
                payUrl = pmap.get("payUrl");
            }
            if (pmap.containsKey("notifyUrl")) {
                notifyUrl = pmap.get("notifyUrl");
            }
        }
    }
    /**
     * 功能描述:
     * 网页支付
     * @Author: Hardy
     * @Date: 2018年08月26日 16:55:02
     * @param config
     * @return: java.lang.String
     **/
    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        return null;
    }

    /**
     * 功能描述:
     * 扫码支付
     * @Author: Hardy
     * @Date: 2018年08月26日 16:55:10
     * @param config
     * @return: java.lang.String
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        try {
            Map<String, String> data = sealRequest(config);
            if(data == null || data.isEmpty()){
                logger.error("组装支付参数异常!");
                throw new RPCPayException("error","组装支付参数异常!");
            }
            //WT支付待签名参数
            logger.info("WT支付待签名参数:"+JSONUtil.toJsonStr(data));
            //发起支付
            //组装返回结果
            return sealResponse(data,config.getEnterChannel());
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.error(ResponseConstant.ERROR_CODE,"支付异常:"+e.getErrorMsg(),e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"支付异常:"+e.getErrorMsg(),e.getMessage());
        }
    }

    @Override
    public boolean notify(Map<String, String> data) {
        return false;
    }

    /**
     * 功能描述:
     * 分装请求参数
     * @Author: Hardy
     * @Date: 2018年08月29日 16:38:45
     * @param config
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    private Map<String,String> sealRequest(PayConfigVO config) throws RPCPayException{
        //组装支付参数
        Map<String, String> data = new HashMap<String, String>();
        try {
            double amount = config.getAmount();// "8.02";// 订单金额
            String price = new DecimalFormat("##").format(amount*100);
            data.put("uid", uid);
            data.put("price", price);
            data.put("paytype", config.getPayCode());
            data.put("notifyurl", notifyUrl);
            data.put("returnurl", config.getRetrunUrl());
            data.put("orderid", config.getOrderNo());
            data.put("orderuid", String.valueOf(config.getUid()));
            Map<String,String> treemap = MapSortUtil.sortByKeys(data);//签名key
            StringBuffer sb = new StringBuffer();
            Iterator<String> iterator = treemap.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                String value = treemap.get(key);
                if (StrUtil.isBlank(value)){
                    continue;
                }
                sb.append("&").append(key).append("=").append(value);
            }
            sb.append(secret);
            String signStr = sb.toString().replaceFirst("&","");
            logger.info("WT支付待签名字符串:"+signStr);
            String sign = MD5Util.encryptToLower_32bit(signStr);
            logger.info("WT支付签名字符串:{"+sign+"}");
            treemap.put("sign",sign);
            return treemap;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("组装支付参数异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"WT支付组装支付参数异常",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 组装支付返回结果
     * @Author: Hardy
     * @Date: 2018年09月04日 22:34:01
     * @param data
     * @param enterChannel
     * @return: com.sky.pay.po.PayResponse
     **/
    private PayResponse sealResponse(Map<String,String> data,Integer enterChannel)throws RPCPayException{
        logger.info("{WTPay}万通支付开始===========================START===============================");
        try {
            String response = HttpUtil.toPostJson(data,payUrl);
            if (StrUtil.isNotBlank(response)){
                logger.info("{WTPay}万通支付发起支付返回结果:"+response);
                //解析支付结果
                JSONObject jsonObject = JSONUtil.parseObj(response);
                if ("1".equals(jsonObject.getStr("code"))) {
                    // 支付成功success
                    JSONObject responseData = JSONUtil.parseObj(jsonObject.get("data"));
                    JSONObject responseResult = JSONUtil.parseObj(responseData.get("result"));
                    // 返回地址
                    String resultUrl = responseResult.getStr("url");
                    if(EnterChannelEnum.PC.getType() == enterChannel){
                        //PC端
                        return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,
                                ViewEnum.QRCODE.getType(),ViewEnum.QRCODE.getCode(),"支付成功",response);
                    }else{
                        //移动端
                        return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,
                                ViewEnum.QRURL.getType(),ViewEnum.QRURL.getCode(),"支付成功",response);
                    }
                }
            }
            //支付失败
            return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败!",response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付结果解析异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"WT支付结果解析异常",e.getMessage());
        }
    }
}
