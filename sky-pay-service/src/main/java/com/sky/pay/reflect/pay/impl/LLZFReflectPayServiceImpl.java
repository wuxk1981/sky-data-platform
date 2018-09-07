/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    LLZFReflectPayServiceImpl.java 
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
 *    Create at:   2018年08月31日 16:45 
 *
 *    Revision: 
 *
 *    2018/8/31 16:45 
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
import com.sky.pay.util.MapSortUtil;
import com.sky.pay.vo.PayConfigVO;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  * @ClassName LLZFReflectPayServiceImpl
 *  * @Description 连连支付
 *  * @Author Hardy
 *  * @Date 2018年08月31日 16:45
 *  * @Version 1.0.0
 *  
 **/
public class LLZFReflectPayServiceImpl implements ReflectPayService {

    //日志
    private final static Logger logger = LoggerFactory.getLogger(LLZFReflectPayServiceImpl.class);

    /** 支付地址 */
    private String payUrl;

    /** 加密因子,md5_key */
    private String md5_key;
    /** 商户号 */
    private String oid_partner;
    /** 通知地址 */
    private String notify_url;

    /** 商品名称 */
    private String name_goods;
    /** 商品描述 */
    private String info_order;

    private String scan_url;

    public LLZFReflectPayServiceImpl(Map<String, String> pmap) {
        if (pmap != null) {
            if (pmap.containsKey("payUrl")) {
                this.payUrl = pmap.get("payUrl");
            }
            if (pmap.containsKey("name_goods")) {
                this.name_goods = pmap.get("name_goods");
            } else {
                this.name_goods = "";
            }
            if (pmap.containsKey("info_order")) {
                this.info_order = pmap.get("info_order");
            } else {
                this.info_order = "";
            }
            if (pmap.containsKey("md5_key")) {
                this.md5_key = pmap.get("md5_key");
            }
            if (pmap.containsKey("oid_partner")) {
                this.oid_partner = pmap.get("oid_partner");
            }
            if (pmap.containsKey("notify_url")) {
                this.notify_url = pmap.get("notify_url");
            }
            if (pmap.containsKey("scan_url")) {
                this.scan_url = pmap.get("scan_url");
            }
        }
    }

    /**
     * 功能描述:
     * 网银支付
     * @Author: Hardy
     * @Date: 2018年08月31日 16:46:11
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        try {
           //封装请求数据
            Map<String,String> data = sealRequest(config);
            //生成签名
            String sign = generatorSign(data);
            //封装支付返回结果
            data.put("sign",sign);
            return sealResponse(data,config.getEnterChannel(),config.getPayChannel());
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("{LLPay}连连网银支付异常:"+e.getMessage());
            throw new RPCPayException("error","连连网银支付异常",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 扫码支付
     * @Author: Hardy
     * @Date: 2018年08月31日 16:46:24
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        logger.info("{LLPay}连连扫码支付开始========================start======================");
        try {
            //组装支付请求参数
            Map<String,String> data = sealRequest(config);
            //生成签名
            String sign = generatorSign(data);
            //封装支付返回结果
            data.put("sign",sign);
            return sealResponse(data,config.getEnterChannel(),config.getPayChannel());
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("{LLZFPay}连连扫码支付异常:"+e.getErrorMsg());
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
     * @Date: 2018年08月31日 16:50:22
     * @param config
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    private Map<String,String> sealRequest(PayConfigVO config) throws RPCPayException{
        try {
            // 18位序列号,可以自行调整。
            String user_id = DateUtil.format(new Date(),"yyyyMMddHHmmss") + RandomUtil.randomNumbers(4);
            String amount = new DecimalFormat("#######.00").format(config.getAmount());//订单金额
            Map<String, String> params = new HashMap<>();
            params.put("oid_partner",oid_partner);
            params.put("notify_url",notify_url);
            params.put("return_url",config.getRetrunUrl());
            params.put("user_id", user_id);
            params.put("sign_type","MD5");
            params.put("no_order", config.getOrderNo());
            params.put("time_order",DateUtil.format(new Date(),"yyyyMMddHHmmss"));
            params.put("money_order",amount);
            params.put("name_goods",name_goods);
            params.put("info_order",info_order);
            params.put("pay_type", config.getPayCode());
            //网银支付
            if (PayChannelEnum.BANK.getCode().equals(config.getPayChannel())){
                params.put("pay_type","11");
            }else{
                //其他支付方式
                if(EnterChannelEnum.PC.getType() == config.getEnterChannel()){
                    params.put("pay_type", config.getPayCode());
                }else {
                    params.put("pay_type","11");
                }
            }
            //按照key的升序排序
            Map<String,String> sortMap = MapSortUtil.sortByKeys(params);
            return sortMap;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("组装支付请求参数异常:"+e.getMessage());
            throw new RPCPayException("error","组装支付请求参数异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 生成签名,按照升序组装签名串
     * @Author: Hardy
     * @Date: 2018年08月31日 17:00:29
     * @param data
     * @return: java.lang.String
     **/
    private String generatorSign(Map<String,String> data) throws RPCPayException{
        try {
            StringBuilder buf = new StringBuilder();
            for (String key : data.keySet()) {
                String value = data.get(key);
                if (StrUtil.isBlank(value)) {
                    continue;
                }
                buf.append(key).append("=").append(value).append("&");
            }
            String signatureStr = buf.substring(0, buf.length() - 1);
            signatureStr = signatureStr + md5_key;
            logger.info("待签名字符串：" + signatureStr);
            String signature = MD5Util.encryptToLower_32bit(signatureStr);
            return signature;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("生成签名异常:"+e.getMessage());
            throw new RPCPayException("error","生成签名异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 封装支付返回结果
     * @Author: Hardy
     * @Date: 2018年08月31日 17:07:26
     * @param data 支付请求参数
     * @param enterChannel 进入渠道
     * @return: com.sky.pay.po.PayResponse
     **/
    private PayResponse sealResponse(Map<String,String> data,Integer enterChannel,String payChannel) throws RPCPayException{
        try {
            String response = null;
            if (PayChannelEnum.BANK.getCode().equals(payChannel)){
                //网银支付
                response = HttpUtil.generatorForm(data,payUrl);
                return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.FORM.getType(),
                        ViewEnum.FORM.getCode(),"支付成功",response);
            }else {
                //其他支付方式
                if(EnterChannelEnum.PC.getType() == enterChannel){
                    //PC端
                    response = HttpUtil.post(data,scan_url,null);
                    logger.info("连连支付扫码响应：" + response);
                    if(StrUtil.isBlank(response)){
                        return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败!",response);
                    }
                    JSONObject json = JSONUtil.parseObj(response);
                    if (json.containsKey("ret_code") && "0000".equals(json.getStr("ret_code"))) {
                        return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRCODE.getType(),
                                ViewEnum.QRCODE.getCode(),"生成二维码图片成功",json.getStr("dimension_url"));
                    }
                    return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败!",response);
                }else {
                    //移动端
                    response = HttpUtil.generatorForm(data,payUrl);
                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.FORM.getType(),
                            ViewEnum.FORM.getCode(),"支付成功",response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("{LLZFPay}连连支付异常:"+e.getMessage());
            throw new RPCPayException("error","支付异常",e.getMessage());
        }
    }
}
