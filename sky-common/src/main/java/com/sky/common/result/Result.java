package com.sky.common.result;


import com.xiaoleilu.hutool.util.StrUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: [返回结果集]</p>
 * Created on 2017年12月11日
 * 
 * @author <a href="mailto: cuichunsong@camelotchina.com">崔春松</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 4760645896692860229L;
	
	protected Integer code = 0;
	
	protected String msg = "success";
	
	protected boolean success = true;
	
	protected T data;
	
	protected Map<String, Object> EnumVal = new HashMap<String, Object>();

	public Result() {
		super();
	}

	public Result(Integer code, String msg, boolean success) {
		super();
		this.code = code;
		this.msg = msg;
		this.success = success;
	}

	public static <T> Result<T> error(String msg) {
		return new Result<T>(-1, msg, false);
	}

	public static <T> Result<T> success() {
		return success(null, null);
	}

	public static <T> Result<T> success(String msg) {
		return success(msg, null);
	}

	public static <T> Result<T> success(T data) {
		return success(null, data);
	}

	public static <T> Result<T> success(String msg, T data) {
		Result<T> result = new Result<T>();
		if (StrUtil.isNotBlank(msg)) {
			result.setMsg(msg);
			result.setCode(0);
			result.setSuccess(true);
		}
		if (null != result) {
			result.setData(data);
		}
		return result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Map<String, Object> getEnumVal() {
		return EnumVal;
	}

	public void putEnumVal(String key, Object val) {
		EnumVal.put(key, val);
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", success=" + success + ", data=" + data + ", EnumVal="
				+ EnumVal + "]";
	}

}
