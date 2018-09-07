package com.sky.common;

import java.io.Serializable;

/**
 * <p>Description: [分页类]</p>
 * Created on 2017年12月21日
 * @author  <a href="mailto: ningxiaoqiang@camelotchina.com">宁晓强</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司
 */
public class Pager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 默认每页记录数为10条 */
	public static final int DEFAULT_PAGESIZE = 10;
	
	/*
	 * 创建对象时必填字段
	 */
	private int page = 1;// 当前页
	private int rows = DEFAULT_PAGESIZE;// 每页显示记录数
	private int totalCount;// 总记录数
	
	/*
	 * 数据库相关字段
	 */
	private int pageOffset;// 当前页起始记录
	private String sort = "ID";// 排序字段及排序方式
	private String order = "ASC"; // asc/desc  暂未用
	private int totalPage;// 总页数
	
	private int pageCode = 7; // 页码数量：分页页面在页面上显示多少页的索引
	private int startPageIndex;// 开始页
	private int endPageIndex;// 结束页
	private int previewPage = 1;// 上一页
	private int nextPage = 1; // 下一页

	public Pager() {
	}

	/**
	 * @param page 当前页
	 * @rows 每页记录数大小
	 */
	public Pager(int page, int rows) {
		this.page = page;
		this.rows = rows;
	}

	/**
	 * @param page 当前页
	 */
	public Pager(int page) {
		this.page = page;
		this.rows = DEFAULT_PAGESIZE;
	}

	public int getPageOffset() {
		pageOffset = (page - 1) * rows;
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page > 0)
			this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		if (rows > 0)
			this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;

		this.startPageIndex = this.page
				- (pageCode % 2 == 0 ? pageCode / 2 - 1 : pageCode / 2);
		this.endPageIndex = this.page + pageCode / 2;
		if (this.startPageIndex < 1) {
			this.startPageIndex = 1;
			if (totalPage >= pageCode) {
				this.endPageIndex = pageCode;
			} else {
				this.endPageIndex = totalPage;
			}
		}
		if (this.endPageIndex > totalPage) {
			this.endPageIndex = totalPage;
			if ((this.endPageIndex - pageCode) > 0) {
				this.startPageIndex = this.endPageIndex - pageCode + 1;
			} else
				this.startPageIndex = 1;
		}
		this.previewPage = this.page - 1;
		this.nextPage = this.page + 1;
		if (this.page <= 1) {
			this.previewPage = 1;
		}
		if (this.page >= this.totalPage) {
			this.nextPage = this.totalPage;
		}
		if(this.totalPage==0){
			this.startPageIndex=0;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}	

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		setTotalPage((int) (totalCount % rows == 0 ? totalCount / rows : (totalCount / rows + 1)));
	}

	public int getStartPageIndex() {
		return startPageIndex;
	}

	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}

	public int getEndPageIndex() {
		if(this.endPageIndex<this.startPageIndex){
			this.endPageIndex=this.startPageIndex;
		}
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public int getPageCode() {
		return pageCode;
	}

	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	public int getPreviewPage() {
		return previewPage;
	}

	public void setPreviewPage(int previewPage) {
		this.previewPage = previewPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		return "Pager [page=" + page + ", rows=" + rows + ", totalCount=" + totalCount + ", pageOffset=" + pageOffset
				+ ", sort=" + sort + ", order=" + order + ", totalPage=" + totalPage + ", pageCode=" + pageCode
				+ ", startPageIndex=" + startPageIndex + ", endPageIndex=" + endPageIndex + ", previewPage="
				+ previewPage + ", nextPage=" + nextPage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endPageIndex;
		result = prime * result + nextPage;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + page;
		result = prime * result + pageCode;
		result = prime * result + pageOffset;
		result = prime * result + previewPage;
		result = prime * result + rows;
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result + startPageIndex;
		result = prime * result + totalCount;
		result = prime * result + totalPage;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pager other = (Pager) obj;
		if (endPageIndex != other.endPageIndex)
			return false;
		if (nextPage != other.nextPage)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (page != other.page)
			return false;
		if (pageCode != other.pageCode)
			return false;
		if (pageOffset != other.pageOffset)
			return false;
		if (previewPage != other.previewPage)
			return false;
		if (rows != other.rows)
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (startPageIndex != other.startPageIndex)
			return false;
		if (totalCount != other.totalCount)
			return false;
		if (totalPage != other.totalPage)
			return false;
		return true;
	}
	
	
	
}
