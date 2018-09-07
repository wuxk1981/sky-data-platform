/******************************************************************
 *
 *    Powered By tianxia-online.
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.common.enums
 *
 *    Filename:    PlatformEnum.java
 *
 *    Description: 平台枚举类
 *
 *    Copyright:   Copyright (c) 2018-2020
 *
 *    Company:     天下网络科技
 *
 *    @author:     Tammy
 *
 *    @version:    1.0.0
 *
 *    Create at:   2018年09月01日 10:37
 *
 *    Revision:
 *
 *    2018/9/1 10:37
 *        - first revision
 *
 *****************************************************************/
package com.sky.common.enums;

/**
 *  * @ClassName PlatformEnum
 *  * @Description 平台枚举类
 *  * @Author Tammy
 *  * @Date 2018年09月01日 10:37
 *  * @Version 1.0.0
 *  
 **/
public enum PlatformEnum implements BaseEnum {

	JJF(0, "JJF"),
	TXMANAGER(1, "txManager"),
	TXMANAGERDL(2, "txManagerDL"),
	RENRENSECURITY(3, "renrenSecurity");

	private Integer code;
	private String description;

	private PlatformEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
