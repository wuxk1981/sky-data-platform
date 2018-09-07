package com.sky.common.result;


import com.sky.common.Pager;

/**
 * <p>
 * Description: [分页返回结果集]
 * </p>
 * Created on 2017年12月11日
 * 
 * @author <a href="mailto: cuichunsong@camelotchina.com">崔春松</a>
 * @version 1.0 Copyright (c) 2017 北京柯莱特科技有限公司
 */
public class PageResult<T> extends QueryResult<T> {

	private static final long serialVersionUID = -6635827407292341329L;

	private Pager pager;

	private T result;

	public PageResult() {
		super();
	}

	public PageResult(Integer code, String msg, boolean success) {
		super();
		this.code = code;
		this.msg = msg;
		this.success = success;
	}

	public static <T> PageResult<T> error(String msg) {
		return new PageResult<T>(-1, msg, false);
	}

	public static <T> PageResult<T> success(T result, Pager pager) {
		PageResult<T> pageResult = new PageResult<T>();
		pageResult.setResult(result);
		pageResult.setPager(pager);
		return pageResult;
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

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
