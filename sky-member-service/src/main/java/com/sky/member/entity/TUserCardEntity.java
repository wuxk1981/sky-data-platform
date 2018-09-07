package com.sky.member.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 会员银行卡信息
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 21:34:09
 */
public class TUserCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户ID
	private Integer uid;
	//开卡人姓名
	private String cardUsername;
	//银行名称
	private Integer bankId;
	//卡号
	private String cardNum;
	//开户行
	private String cardAddress;
	//加入时间
	private Date addTime;
	//
	private String isDelete;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户ID
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：开卡人姓名
	 */
	public void setCardUsername(String cardUsername) {
		this.cardUsername = cardUsername;
	}
	/**
	 * 获取：开卡人姓名
	 */
	public String getCardUsername() {
		return cardUsername;
	}
	/**
	 * 设置：银行名称
	 */
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	/**
	 * 获取：银行名称
	 */
	public Integer getBankId() {
		return bankId;
	}
	/**
	 * 设置：卡号
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	/**
	 * 获取：卡号
	 */
	public String getCardNum() {
		return cardNum;
	}
	/**
	 * 设置：开户行
	 */
	public void setCardAddress(String cardAddress) {
		this.cardAddress = cardAddress;
	}
	/**
	 * 获取：开户行
	 */
	public String getCardAddress() {
		return cardAddress;
	}
	/**
	 * 设置：加入时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：加入时间
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：
	 */
	public String getIsDelete() {
		return isDelete;
	}
}
