/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.vo 
 *
 *    Filename:    PayConfigVO.java 
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
 *    Create at:   2018年08月27日 17:53 
 *
 *    Revision: 
 *
 *    2018/8/27 17:53 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *  * @ClassName PayConfigVO
 *  * @Description 支付配置VO类
 *  * @Author Hardy
 *  * @Date 2018年08月27日 17:53
 *  * @Version 1.0.0
 *  
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayConfigVO implements Serializable {

    private static final long serialVersionUID = -4725111474625857768L;

    private String orderNo;//订单号

    private double amount;

    private Integer enterChannel;//进入渠道,1 PC端 2 移动端 3 APP 端

    private String payChannel;//支付渠道

    private String payCode;//支付类型

    private String ip;//ip地址

    private Integer id;//支付商ID

    private Integer uid;//用户ID

    private String username;//用户名称

    private String mobile;//用户手机号码

    private String cagent;//平台编码

    private Integer cid;//平台商ID

    //###用户渠道表信息start
    private String channel;//渠道商ID(channel代替t_user_channel表中的type字段)

    private Integer typeid;//分层ID

    private Float dividendRate;//彩金倍率

    private Float codingRate;//打码量倍率
    //###用户渠道表信息start

    private String pathParterPkcs12;//合作商家私钥pkcs12证书路径

    private String passwordParterPkcs12;//合作商家私钥pkcs12证书密码

    private String pathYsepayPublicCert;//合作商家私钥pkcs12证书密码

    private String rsaAlgorithm;//rsa算法名

    private String signAlgorithm;//rsa算法名

    private String defaultCharset;//默认编码方式

    private String partnerId;//商户ID

    private String sellerId;//商铺ID

    private String sellerName;//商铺名称

    private String version;//商户版本号

    private String ysepayGetwayUrl;//银盛支付接入网关URL

    private String ysepayGetwayUrlDf;//银盛支付接入网关URL

    private String notifyUrl;//异步回调URL

    private String retrunUrl;//同步回调URL

    private Date updatetime;//更新时间

    private String type;//支付通道 :1:网银 ,2:支付宝 3: 微信,4财付通，5,手机网银

    private Double minquota;//最小额度

    private Double maxquota;//最大额度

    private Double qrmaxquota;

    private Double qrminquota;

    private Double dayquota;//单日最大额度

    private String payUrl;//支付URL地址

    private String paymentName;//支付商名称

    private String paymentConfig;//支付商配置信息

    private Double aliMinquota;//最小额度

    private Double aliMaxquota;//最大额度

    private Double wxMinquota;//最小额度

    private Double wxMaxquota;//最大额度

    private Integer caIndex;//排序字段

    private Integer isIssued;//是否支持 支付提现下发  0:不支持 1:支持

    private Double ylMaxquota;//最大额度

    private Double ylMinquota;//最小额度

    private Double jdMaxquota;//最大额度

    private Double jdMinquota;//最小额度

    private Double kjMaxquota;//快捷最大额度

    private Double kjMinquota;//快捷最小额度

    private Integer ish5Ali;//手机支付宝类型 0:H5 1:扫码

    private Integer ish5Wx;//手机微信类型 0:H5 1:扫码

    private Integer ish5Cft;//手机财付通类型 0:H5 1:扫码

    private Integer ish5Jd;//手机JD类型 0:H5 1:扫码

    private Integer ish5Yl;//手机银联类型 0:H5 1:扫码

    private Double wxtmMinquota;//快捷最小额度

    private Double wxtmMaxquota;//快捷最大额度

    private Double alitmMinquota;//快捷最小额度

    private Double alitmMaxquota;//快捷最大额度

}
