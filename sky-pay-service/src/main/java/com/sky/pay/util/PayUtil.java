/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    PayUtil.java 
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
 *    Create at:   2018年08月26日 18:26 
 *
 *    Revision: 
 *
 *    2018/8/26 18:26 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import com.sky.pay.constant.PayConstant;
import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 *  * @ClassName PayUtil
 *  * @Description 支付工具类
 *  * @Author Hardy
 *  * @Date 2018年08月26日 18:26
 *  * @Version 1.0.0
 *  
 **/
public class PayUtil {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(PayUtil.class);

    private static final String HORIZONTAL = "-";

    /**
     * 功能描述:
     * 生成订单号
     * @Author: Hardy
     * @Date: 2018年08月27日 18:35:46
     * @param cagent 平台商编码
     * @param paymentName 支付商名称
     * @return: java.lang.String
     **/
    public static String generatorOrderNo(String cagent, String paymentName) {
        logger.info("---------------生成支付订单号 开始------------------------");
        // 14位 当前时间 yyyyMMddHHmmss
        String currTime = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = RandomUtil.randomNumbers(4);//四位随机数
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        // 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
        String orderNo = paymentName + cagent + currTime + strReq;
        logger.info("---------------生成支付订单号 结束------------------------");
        /***added by hb at 2018-06-22 全谷迪卿支付订单号长度减少到17位 start */
        // 特殊生成全谷迪卿订单号
        if(PayConstant.CONSTANT_QGDL.equals(paymentName)) {
            String payName = paymentName.substring(0, 2);
            orderNo = payName + DateUtil.format(DateUtil.parse(currTime,"yyyyMMddHHmmss"),"yyMMddHHmmssSSS");
        }
        /*** added by hb at 2018-06-22 全谷迪卿支付订单号长度减少到17位 end */
        /***added by hb at 2018-06-25 免签支付订单号长度20位 start */
        // 特殊生成全谷迪卿订单号
        if(PayConstant.CONSTANT_MQZF.equals(paymentName)) {
            orderNo = "MQ" + currTime+RandomUtil.randomString(6);
        }
        //万通支付订单号只要20位
        if(PayConstant.CONSTANT_WT.equals(paymentName) || PayConstant.CONSTANT_MJF.equals(paymentName)
                || PayConstant.CONSTANT_XINFA.equals(paymentName)){
            orderNo = orderNo.substring(0,21);
        }

        //掌付支付订单号
        if (PayConstant.CONSTANT_XZF.equals(paymentName)){
            orderNo = UUID.randomUUID().toString().replace(HORIZONTAL, StringUtils.EMPTY);
        }
        return orderNo;
    }
}
