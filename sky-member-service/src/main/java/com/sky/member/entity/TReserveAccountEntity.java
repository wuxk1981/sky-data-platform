package com.sky.member.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 系统保留账号
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
public class TReserveAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户名
	private String username;
	//代理号
	private String cagent;
	//备注
	private String rmk;
	//添加时间
	private Date addtime;
	//添加用户ID(登录用户ID)
	private Integer adduid;
	//
	private Integer cid;

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
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：代理号
	 */
	public void setCagent(String cagent) {
		this.cagent = cagent;
	}
	/**
	 * 获取：代理号
	 */
	public String getCagent() {
		return cagent;
	}
	/**
	 * 设置：备注
	 */
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	/**
	 * 获取：备注
	 */
	public String getRmk() {
		return rmk;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getAddtime() {
		return addtime;
	}
	/**
	 * 设置：添加用户ID(登录用户ID)
	 */
	public void setAdduid(Integer adduid) {
		this.adduid = adduid;
	}
	/**
	 * 获取：添加用户ID(登录用户ID)
	 */
	public Integer getAdduid() {
		return adduid;
	}
	/**
	 * 设置：
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：
	 */
	public Integer getCid() {
		return cid;
	}
}
