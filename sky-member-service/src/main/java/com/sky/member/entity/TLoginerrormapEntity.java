package com.sky.member.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户登录失败次数
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 16:15:19
 */
public class TLoginerrormapEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String username;
	//
	private Integer times;
	//
	private Date logintime;

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
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setTimes(Integer times) {
		this.times = times;
	}
	/**
	 * 获取：
	 */
	public Integer getTimes() {
		return times;
	}
	/**
	 * 设置：
	 */
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	/**
	 * 获取：
	 */
	public Date getLogintime() {
		return logintime;
	}
}
