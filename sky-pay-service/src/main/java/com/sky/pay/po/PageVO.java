/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.po 
 *
 *    Filename:    PageVO.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Hardy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年09月05日 18:17 
 *
 *    Revision: 
 *
 *    2018/9/5 18:17 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *  * @ClassName PageVO
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Hardy
 *  * @Date 2018年09月05日 18:17
 *  * @Version 1.0.0
 *  
 **/
@ApiModel(value="分页实体类")
public class PageVO implements Serializable {

    private static final long serialVersionUID = -164293692153817961L;

    @ApiModelProperty(value="当前页码",required = true)
    private int currentPage;//当前页码

    @ApiModelProperty(value="每页显示条数",required = true)
    private int pageCount;//每页显示条数

    @ApiModelProperty(value="总条数",required = true)
    private int totalCount;//总条数

    @ApiModelProperty(value="总页数",required = true)
    private int totalPage;//总页数

    @ApiModelProperty(value="包装数据",required = false)
    private Object data;//包装数据

    public PageVO(int currentPage, int pageCount, int totalCount, Object data) {
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        if (pageCount == 0) {
            totalPage = 0;
        } else {
            totalPage = totalCount % pageCount == 0 ? totalCount / pageCount : totalCount / pageCount + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
