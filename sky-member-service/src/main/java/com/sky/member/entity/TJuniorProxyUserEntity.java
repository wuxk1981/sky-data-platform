package com.sky.member.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 二级代理商
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
public class TJuniorProxyUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//代理账号
	private String userName;
	//代理姓名
	private String name;
	//上层ID
	private String upId;
	//分层
	private String type;
	//更新时间
	private Date upTime;
	//状态
	private String status;
	//域名
	private String domain;
	//邮箱
	private String email;
	//qq
	private String qq;
	//银行名称
	private String bankname;
	//
	private String bankcode;
	//开户行
	private String bankaddress;
	//手机号
	private String mobile;
	//和代理商表关联(t_proxy_user)
	private Integer proxyUserId;
	//
	private String rmk;
	//
	private String userType;

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
	 * 设置：代理账号
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：代理账号
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：代理姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：代理姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：上层ID
	 */
	public void setUpId(String upId) {
		this.upId = upId;
	}
	/**
	 * 获取：上层ID
	 */
	public String getUpId() {
		return upId;
	}
	/**
	 * 设置：分层
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：分层
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpTime() {
		return upTime;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：域名
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * 获取：域名
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 获取：qq
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * 设置：银行名称
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	/**
	 * 获取：银行名称
	 */
	public String getBankname() {
		return bankname;
	}
	/**
	 * 设置：
	 */
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	/**
	 * 获取：
	 */
	public String getBankcode() {
		return bankcode;
	}
	/**
	 * 设置：开户行
	 */
	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}
	/**
	 * 获取：开户行
	 */
	public String getBankaddress() {
		return bankaddress;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：和代理商表关联(t_proxy_user)
	 */
	public void setProxyUserId(Integer proxyUserId) {
		this.proxyUserId = proxyUserId;
	}
	/**
	 * 获取：和代理商表关联(t_proxy_user)
	 */
	public Integer getProxyUserId() {
		return proxyUserId;
	}
	/**
	 * 设置：
	 */
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	/**
	 * 获取：
	 */
	public String getRmk() {
		return rmk;
	}
	/**
	 * 设置：
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取：
	 */
	public String getUserType() {
		return userType;
	}
}
