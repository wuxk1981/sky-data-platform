package com.sky.common.result;

/**
 * <p>Description: [只返回是否成功的结果集]</p>
 * Created on 2017年12月11日
 * @author  <a href="mailto: cuichunsong@camelotchina.com">崔春松</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司
 */
public class SimpleResult<T> extends Result<T> {
	
	private static final long serialVersionUID = 9034101196269221332L;
	
	public static <T> SimpleResult<T> success() {
		return new SimpleResult<T>();
	}
	
	public static <T> SimpleResult<T> success(T data) {
		return new SimpleResult<T>(data);
	}
	
	public static <T> SimpleResult<T> error(String msg) {
		return new SimpleResult<T>(-1, false, msg);
	}
	
	public SimpleResult() {
		super();
	}
	
	public SimpleResult(Integer code, boolean success, String message) {
		super();
		this.code = code;
		this.success = success;
		this.msg = message;
	}
	
	public SimpleResult(T data) {
		super();
		this.data = data;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
