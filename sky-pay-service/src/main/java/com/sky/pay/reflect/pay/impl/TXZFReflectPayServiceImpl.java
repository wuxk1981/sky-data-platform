/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    TXZFReflectPayService.java 
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
 *    Create at:   2018年09月02日 21:56 
 *
 *    Revision: 
 *
 *    2018/9/2 21:56 
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
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import com.xiaoleilu.hutool.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  * @ClassName TXZFReflectPayService
 *  * @Description 天下支付
 *  * @Author Hardy
 *  * @Date 2018年09月02日 21:56
 *  * @Version 1.0.0
 *  
 **/
public class TXZFReflectPayServiceImpl implements ReflectPayService {

    //日志
    private final static Logger logger = LoggerFactory.getLogger(TXZFReflectPayServiceImpl.class);

    private String payKey;//商户支付Key

    private String paySecret;//签名key

    private String notifyUrl;//回调地址

    private String payUrl;//网银支付地址

    private String scanUrl;//扫码支付地址
    // wx类型
    private String wxproductType;
    // zfb类型
    private String zfbproductType;
    // qq类型
    private String qqproductType;
    // zfb手机端类型
    private String zfbwapproductType;
    // kl类型
    private String kjproductType;
    // kl手机端类型
    private String kjwapproductType;
    // jd类型
    private String jdproductType;
    // yl类型
    private String ylproductType;

    private String html = "no";

    public TXZFReflectPayServiceImpl(Map<String,String> pmap) {
        if (pmap != null) {
            if (pmap.containsKey("payKey")) {
                payKey = pmap.get("payKey");
            }
            if (pmap.containsKey("notifyUrl")) {
                notifyUrl = pmap.get("notifyUrl");
            }
            if (pmap.containsKey("payUrl")) {
                payUrl = pmap.get("payUrl");
            }
            if (pmap.containsKey("scanUrl")) {
                scanUrl = pmap.get("scanUrl");
            }
            if (pmap.containsKey("paySecret")) {
                paySecret = pmap.get("paySecret");
            }
            if (pmap.containsKey("wxproductType")) {
                wxproductType = pmap.get("wxproductType");
            }
            if (pmap.containsKey("zfbproductType")) {
                zfbproductType = pmap.get("zfbproductType");
            }
            if (pmap.containsKey("zfbproductType")) {
                qqproductType = pmap.get("qqproductType");
            }
            if (pmap.containsKey("zfbwapproductType")) {
                zfbwapproductType = pmap.get("zfbwapproductType");
            }
            if (pmap.containsKey("jdproductType")) {
                jdproductType = pmap.get("jdproductType");
            }
            if (pmap.containsKey("ylproductType")) {
                ylproductType = pmap.get("ylproductType");
            }
            if (pmap.containsKey("kjproductType")) {
                kjproductType = pmap.get("kjproductType");
            }
            if (pmap.containsKey("kjwapproductType")) {
                kjwapproductType = pmap.get("kjwapproductType");
            }
            if (pmap.containsKey("html")) {
                html = pmap.get("html");
            }
        }
    }

    /**
     * 功能描述:
     * 网银支付
     * @Author: Hardy
     * @Date: 2018年09月02日 21:56:57
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        logger.info("[TXZF]网银支付开始===========================START============================");
        try {
            //封装支付请求参数
            Map<String,String> data = sealRequest(config);
            //发起支付请求，并获取封装返回结果
            return sealResponse(data,config.getEnterChannel(),config.getPayChannel(),config.getPayCode());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[TXZF]网银支付异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"[TXZF]网银支付异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 扫码支付
     * @Author: Hardy
     * @Date: 2018年09月02日 21:57:05
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        logger.info("[TXZF]扫码支付开始===========================START============================");
        try {
            //封装支付请求参数
            Map<String,String> data = sealRequest(config);
            //发起支付请求，并获取封装返回结果
            return sealResponse(data,config.getEnterChannel(),config.getPayChannel(),config.getPayCode());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[TXZF]扫码支付异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"[TXZF]扫码支付异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 回调
     * @Author: Hardy
     * @Date: 2018年09月05日 10:23:11
     * @param data
     * @return: boolean
     **/
    @Override
    public boolean notify(Map<String, String> data){
        return false;
    }

    private Map<String,String> sealRequest(PayConfigVO config) throws RPCPayException{
        logger.info("封装[TXZF]支付请求参数开始==========================START=======================");
        try {
            // 2位小数
            String amount = new DecimalFormat("0.00").format(config.getAmount());// 订单金额
            Map<String, String> params = new HashMap<>();
            params.put("payKey", payKey); // 商户支付Key
            params.put("orderPrice", amount);// 订单金额，单位：元保留小数点后两位
            params.put("outTradeNo", config.getOrderNo());// 商户支付订单号
            params.put("payCertNo", ""); // 证件号码
            // D0快捷支付 40000502
            // 14位日期
            params.put("orderTime", DateUtil.format(new Date(),"yyyyMMddHHmmss")); // 下单时间，格式：yyyyMMddHHmmss
            params.put("payBankAccountNo", config.getPayCode());// 银行卡号
            params.put("payPhoneNo", "");// 银行预留手机号码
            params.put("payBankAccountName", "");// 银行账户名称
            params.put("orderIp", config.getIp()); // orderIp
            params.put("returnUrl", config.getRetrunUrl()); // 页面通知地址
            params.put("notifyUrl", notifyUrl);// 后台异步通知地址
            params.put("remark", ""); // remark

            if (PayChannelEnum.BANK.getCode().equals(config.getPayChannel())){
                //网银支付
                params.put("productType", "50000103"); // D0网银支付 50000102
                params.put("productName", "D0test");// 支付产品名称
            }else {
                //其他支付方式
                params.put("productName", "charge");// 支付产品名称
                if (EnterChannelEnum.PC.getType() == config.getEnterChannel()){
                    // pc端产品
                    if ("WX".equals(config.getPayCode()))
                        params.put("productType", getProductType(wxproductType, config.getPayCode()));
                    if ("ZFB".equals(config.getPayCode()))
                        params.put("productType", getProductType(zfbproductType, config.getPayCode()));
                    if ("QQ".equals(config.getPayCode()))
                        params.put("productType", getProductType(qqproductType, config.getPayCode()));
                    if ("YL".equals(config.getPayCode()))
                        params.put("productType", getProductType(ylproductType, config.getPayCode()));
                    if ("JD".equals(config.getPayCode()))
                        params.put("productType", getProductType(jdproductType, config.getPayCode()));
                    if ("KJ".equals(config.getPayCode()))
                        params.put("productType", getProductType(kjproductType, config.getPayCode()));
                }else{
                    if ("ZFB".equals(config.getPayCode()))
                        params.put("productType", getWAPProductType(zfbwapproductType, config.getPayCode()));
                    if ("KJ".equals(config.getPayCode()))
                        params.put("productType", getWAPProductType(kjwapproductType, config.getPayCode()));
                }
            }
            //签名规则:
            // 平台默认使用MD5签名方式进行数据验签，保证数据完整性。
            //请求方在请求数据是将请求数据按照键值对的方式通过'&'符号进行拼接后 + “&paySecret=xxxxxxxxxxx”，
            // 获取到签名源文。将源文进行MD5签名后，作为sign字段放在请求报文中。
            //源文拼接方式为：按照参数名称进行ASCII编码排序，如果参数值为空，则不参与签名。

            //参数排序
            Map<String,String> treemap = MapSortUtil.sortByKeys(params);
            //拼装签名字符串
            StringBuffer sb = new StringBuffer();
            Iterator<String> iterator = treemap.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                String value = treemap.get(key);
                if (StrUtil.isBlank(value)){
                    continue;
                }
                sb.append(key).append("=").append(value).append("&");
            }
            sb.append("paySecret").append("=").append(paySecret);
            //待签名串
            String signStr = sb.toString();
            logger.info("[TXZF]生成待签名串:"+signStr);
            //签名
            String sign = MD5Util.encryptToUpper_32bit(signStr);
            logger.info("[TXZF]生成签名加密串:"+sign);
            treemap.put("sign",sign);//签名串
            return treemap;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("封装{TXZF}支付参数异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"封装支付请求参数异常!",e.getMessage());
        }
    }

    private PayResponse sealResponse(Map<String,String> data,Integer enterChannel,String payChannel,String payCode)throws RPCPayException{
        logger.info("[TXZF]发起第三方支付开始==============START==========================");
        try {
            //支付返回结果
            String response = null;
            //判断是否为网银支付
            if(PayChannelEnum.BANK.getCode().equals(payChannel) || PayChannelEnum.KY.getCode().equals(payChannel)){
                //网银支付 或者 快捷支付
                response = HttpUtil.generatorForm(data,payUrl);
                if (StrUtil.isNotBlank(response)){
                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.FORM.getType(),
                            ViewEnum.FORM.getCode(),"网银支付成功!",response);
                }
            }else {
                //其他支付
                response = HttpUtil.toPostJson(data,scanUrl);
                if (StrUtil.isNotBlank(response)){
                    logger.info("[TXZF]发起第三方支付结果:"+response);
                    //解析支付结果
                    JSONObject jsonObject = JSONUtil.parseObj(response);
                    String resultCode = jsonObject.getStr("resultCode");
                    if (resultCode.equals("0000")){
                        String htm = jsonObject.getStr("payMessage");
                        htm.replaceAll("\\\\", "");
                        //支付成功,PC端
                        if(EnterChannelEnum.PC.getType() == enterChannel){
                            return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.QRCODE.getType(),
                                    ViewEnum.QRCODE.getCode(),"扫码支付成功",htm);
                        }else {
                            //支付宝
                            JSONObject xmlJson = JSONUtil.parseObj(XmlUtil.readObjectFromXml(htm));
                            String postUrl = xmlJson.getJSONObject("body").getJSONObject("form").getStr("@action");
                            if (payCode.equals("ZFB")){
                                if ("yes".equals(html)) {
                                    logger.info("手机支付宝新渠道html解析");
                                    JSONArray jsonArray = xmlJson.getJSONObject("body").getJSONObject("form")
                                            .getJSONArray("input");
                                    Map<String, String> postMap = new HashMap<>();
                                    for (int i = 0; i < jsonArray.size(); i++) {
                                        // 得到json数组中的每一个json对象
                                        JSONObject obj = (JSONObject) jsonArray.get(i);
                                        String name = obj.getStr("@name");
                                        String value = obj.getStr("@value");
                                        postMap.put(name, value);
                                    }
                                    String result = HttpUtil.generatorForm(postMap, postUrl);
                                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.FORM.getType(),
                                            ViewEnum.FORM.getCode(),"支付成功",result);
                                } else {
                                    logger.info("手机支付宝url跳转");
                                    return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.LINK.getType(),
                                            ViewEnum.LINK.getCode(),"支付成功",htm);
                                }
                            }
                            return PayResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,ViewEnum.LINK.getType(),
                                    ViewEnum.LINK.getCode(),"支付成功",postUrl);
                        }
                    }
                    return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"支付失败,失败原因["+jsonObject.getStr("errMsg")+"]",response);
                }
            }
            return PayResponse.faild(ResponseConstant.FAILD_STATUS,ResponseConstant.FAILD_CODE,"调用第三方支付链接超时!",response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[TXZF]发起第三方支付异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"调用第三方支付异常!",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 获取手机端的支付产品编号
     * @Author: Hardy
     * @Date: 2018年09月05日 14:42:35
     * @param productType
     * @param type
     * @return: java.lang.Object
     **/
    private String getWAPProductType(String productType, String type) {
        if ("D0".equals(productType)) {
            if ("KJ".equals(type))
                return "40000702";
            if ("ZFB".equals(type))
                return "20000202";
        } else if ("T0".equals(productType)) {
            if ("WX".equals(type))
                return "10000203";
            if ("KJ".equals(type))
                return "40000503";
            if ("ZFB".equals(type))
                return "20000203";
        } else if ("T1".equals(productType)) {
            if ("KJ".equals(type))
                return "40000701";
            if ("ZFB".equals(type))
                return "20000201";
        }
        return null;
    }

    /**
     * 功能描述:
     * 获取PC端支付产品编码
     * @Author: Hardy
     * @Date: 2018年09月05日 14:43:38
     * @param productType
     * @param type
     * @return: java.lang.Object
     **/
    private String getProductType(String productType, String type) {
        if ("D0".equals(productType)) {
            if ("WX".equals(type))
                return "10000102";
            if ("ZFB".equals(type))
                return "20000302";
            if ("QQ".equals(type))
                return "70000202";
            if ("YL".equals(type))
                return "60000102";
            if ("KJ".equals(type))
                return "40000502";
        } else if ("T0".equals(productType)) {
            if ("WX".equals(type))
                return "10000103";
            if ("ZFB".equals(type))
                return "20000303";
            if ("QQ".equals(type))
                return "70000203";
            if ("YL".equals(type))
                return "60000103";
            if ("KJ".equals(type))
                return "40000503";
        } else if ("T1".equals(productType)) {
            if ("WX".equals(type))
                return "10000101";
            if ("ZFB".equals(type))
                return "20000301";
            if ("QQ".equals(type))
                return "70000201";
            if ("YL".equals(type))
                return "60000101";
            if ("KJ".equals(type))
                return "40000501";
        }
        return null;
    }
}
