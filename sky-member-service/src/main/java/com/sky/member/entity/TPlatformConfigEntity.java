package com.sky.member.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
@Data
public class TPlatformConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//游戏平台key
	private String platformKey;
	//游戏平台名称
	private String platformName;
	//平台API配置
	private String platformConfig;
	//状态
	private String platformStatus;
	//维护提示信息
	private String tipName;
	//创建时间
	private Date addTime;
	//
	private Date updateTime;
	//
	private Integer updateUid;


}
