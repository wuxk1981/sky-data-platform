/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.vo 
 *
 *    Filename:    PayVO.java 
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
 *    Create at:   2018年08月25日 21:38 
 *
 *    Revision: 
 *
 *    2018/8/25 21:38 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 *  * @ClassName PayVO
 *  * @Description 支付vo类
 *  * @Author Hardy
 *  * @Date 2018年08月25日 21:38
 *  * @Version 1.0.0
 *  
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayVO implements Serializable {
    private static final long serialVersionUID = -4525950099345621622L;
    // 来源域名
    private String refererUrl;
    // ip
    private String ip;
    // 用户id
    private String uId;
    // 支付类型编码
    private String payCode;
    // 金额
    private double amount;
    // 支付商编码
    private String topay;
    // 订单号
    private String orderNo;
    // 支付网关跳转url
    private String payUrl;
    // 支付商
    private String payId;
    // 用户姓名
    private String username;
    // 用于手机h5支付参数
    private String mobile;
    // 支付商配置
    private Map<String, String> payConfig;
    // 扩展参数
    private Map<String, Object> extendMap;
}
