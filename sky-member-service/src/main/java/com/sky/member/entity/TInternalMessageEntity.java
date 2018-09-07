package com.sky.member.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 消息提示表
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-06 10:43:55
 */
public class TInternalMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//会员ID
	private Integer uid;
	//消息内容
	private String message;
	//状态: 0:未读,1:已读
	private String status;
	//发送时间
	private Date addtime;
	//发送用户ID
	private Integer adduserid;

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
	 * 设置：会员ID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：会员ID
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：消息内容
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 获取：消息内容
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 设置：状态: 0:未读,1:已读
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态: 0:未读,1:已读
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：发送时间
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	/**
	 * 获取：发送时间
	 */
	public Date getAddtime() {
		return addtime;
	}
	/**
	 * 设置：发送用户ID
	 */
	public void setAdduserid(Integer adduserid) {
		this.adduserid = adduserid;
	}
	/**
	 * 获取：发送用户ID
	 */
	public Integer getAdduserid() {
		return adduserid;
	}
}
