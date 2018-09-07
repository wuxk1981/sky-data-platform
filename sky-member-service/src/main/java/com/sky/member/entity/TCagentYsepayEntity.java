package com.sky.member.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 在线支付配置
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
@Data
public class TCagentYsepayEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer cid;
	//合作商家私钥pkcs12证书路径
	private String pathParterPkcs12;
	//合作商家私钥pkcs12证书密码
	private String passwordParterPkcs12;
	//合作商家私钥pkcs12证书密码
	private String pathYsepayPublicCert;
	//rsa算法名
	private String rsaAlgorithm;
	//rsa算法名
	private String signAlgorithm;
	//默认编码方式
	private String defaultCharset;
	//
	private String partnerId;
	//
	private String sellerId;
	//
	private String sellerName;
	//商户版本号
	private String version;
	// 银盛支付接入网关url
	private String ysepayGetwayUrl;
	// 银盛支付接入网关url
	private String ysepayGetwayUrlDf;
	//
	private String notifyUrl;
	//
	private String retrunUrl;
	//更新人
	private Date updatetime;
	//
	private Integer uid;
	//支付通道 :1:网银 ,2:支付宝 3: 微信,4财付通，5,手机网银
	private String type;
	//最小额度
	private Double minquota;
	//最大额度
	private Double maxquota;
	//
	private Double qrmaxquota;
	//
	private Double qrminquota;
	//单日最大额度
	private Double dayquota;
	//彩金倍率
	private Float dividendRate;
	//打码量倍率
	private Float codingRate;
	//0:启用，1：停用
	private Integer status;
	//备注
	private String rmk;
	//可用渠道
	private String channel;
	//支付url地址
	private String payUrl;
	//支付商名称
	private String paymentName;
	//支付商配置信息
	private String paymentConfig;
	//最小额度
	private Double aliMinquota;
	//最大额度
	private Double aliMaxquota;
	//最小额度
	private Double wxMinquota;
	//最大额度
	private Double wxMaxquota;
	//排序字段
	private Integer caIndex;
	//是否支持 支付提现下发  0:不支持 1:支持
	private Integer isIssued;
	//最大额度
	private Double ylMaxquota;
	//最小额度
	private Double ylMinquota;
	//最大额度
	private Double jdMaxquota;
	//最小额度
	private Double jdMinquota;
	//快捷最大额度
	private Double kjMaxquota;
	//快捷最小额度
	private Double kjMinquota;
	//手机支付宝类型 0:H5 1:扫码
	private Integer ish5Ali;
	//手机微信类型 0:H5 1:扫码
	private Integer ish5Wx;
	//手机财付通类型 0:H5 1:扫码
	private Integer ish5Cft;
	//手机JD类型 0:H5 1:扫码
	private Integer ish5Jd;
	//手机银联类型 0:H5 1:扫码
	private Integer ish5Yl;
	//快捷最小额度
	private Double wxtmMinquota;
	//快捷最大额度
	private Double wxtmMaxquota;
	//快捷最小额度
	private Double alitmMinquota;
	//快捷最大额度
	private Double alitmMaxquota;


}
