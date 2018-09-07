/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.constant 
 *
 *    Filename:    ResultResponse.java 
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
 *    Create at:   2018年08月27日 22:14 
 *
 *    Revision: 
 *
 *    2018/8/27 22:14 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *  * @ClassName ResultResponse
 *  * @Description 返回结果类
 *  * @Author Hardy
 *  * @Date 2018年08月27日 22:14
 *  * @Version 1.0.0
 *  
 **/
@Data
@ApiModel(value="返回结果包装类")
public class ResultResponse implements Serializable{

    private static final long serialVersionUID = 3216491320941198287L;

    @ApiModelProperty(value="返回状态值 0 异常 1 成功 2 失败",required = true)
    private int status;//返回状态值

    @ApiModelProperty(value="返回状态码 error 异常 faild 失败 success 成功",required = true)
    private String code;//返回状态码

    @ApiModelProperty(value="返回信息",required = true)
    private String message;//返回信息

    @ApiModelProperty(value="返回数据",required = true)
    private Object data;//返回数据

    public ResultResponse() {
    }

    public ResultResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ResultResponse(int status, String code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultResponse success(int status, String code, String message, Object data){
        return new ResultResponse(status, code, message, data);
    }

    public static ResultResponse faild(int status, String code, String message, Object data){
        return new ResultResponse(status, code, message, data);
    }

    public static ResultResponse error(int status, String code,String message){
        return new ResultResponse(status,code,message);
    }


}
