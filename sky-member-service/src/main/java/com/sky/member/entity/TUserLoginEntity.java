package com.sky.member.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
public class TUserLoginEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//账号ID
	private Integer uid;
	//登录时间
	private Date loginTime;
	//登录IP
	private String loginIp;
	//是否已登录
	private Integer isLogin;
	//登录次数
	private Integer loginNum;
	//登录状态
	private String status;
	//是否手机
	private String isMobile;
	//来源地址
	private String address;
	//来源地址
	private String refurl;

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
	 * 设置：账号ID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：账号ID
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取：登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * 设置：登录IP
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/**
	 * 获取：登录IP
	 */
	public String getLoginIp() {
		return loginIp;
	}
	/**
	 * 设置：是否已登录
	 */
	public void setIsLogin(Integer isLogin) {
		this.isLogin = isLogin;
	}
	/**
	 * 获取：是否已登录
	 */
	public Integer getIsLogin() {
		return isLogin;
	}
	/**
	 * 设置：登录次数
	 */
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}
	/**
	 * 获取：登录次数
	 */
	public Integer getLoginNum() {
		return loginNum;
	}
	/**
	 * 设置：登录状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：登录状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：是否手机
	 */
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}
	/**
	 * 获取：是否手机
	 */
	public String getIsMobile() {
		return isMobile;
	}
	/**
	 * 设置：来源地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：来源地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：来源地址
	 */
	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}
	/**
	 * 获取：来源地址
	 */
	public String getRefurl() {
		return refurl;
	}
}
