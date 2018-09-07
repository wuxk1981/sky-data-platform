package com.sky.member.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 域名白名单
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 09:22:01
 */
public class TRefererUrlEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String domain;
	//
	private String name;
	//
	private String rmk;
	//
	private Date addtime;

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
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * 获取：
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
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
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	/**
	 * 获取：
	 */
	public Date getAddtime() {
		return addtime;
	}
}
