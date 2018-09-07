package com.sky.common.result;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: [返回查询的结果集 单条或多条]</p>
 * Created on 2017年12月11日
 * @author  <a href="mailto: cuichunsong@camelotchina.com">崔春松</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司
 */
public class QueryResult<T> extends SimpleResult<T> {
	
	private static final long serialVersionUID = 1552850936989972117L;
	
	private List<String> errorMessage = new ArrayList<String>();
	
	public QueryResult() {
		super();
	}
	
	public QueryResult(Integer code, boolean success, List<String> errorMessage) {
		super();
		this.code = code;
		this.success = success;
		this.errorMessage = errorMessage;
	}
	
	public static <T> QueryResult<T> error(List<String> msg) {
		return new QueryResult<T>(-1, false, msg);
	}
	
	public static <T> QueryResult<T> error(String msg) {
		List<String> list = new ArrayList<String>();
		list.add(msg);
		return new QueryResult<T>(-1, false, list);
	}
	
	public static <T> QueryResult<T> success(T result) {
		QueryResult<T> normalResult = new QueryResult<T>();
		normalResult.setData(result);
		return normalResult;
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
	
	public List<String> getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}
}
