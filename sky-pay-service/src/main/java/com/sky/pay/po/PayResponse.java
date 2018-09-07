/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.po 
 *
 *    Filename:    PayResponse.java 
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
 *    Create at:   2018年08月29日 15:23 
 *
 *    Revision: 
 *
 *    2018/8/29 15:23 
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
 *  * @ClassName PayResponse
 *  * @Description 支付返回结果
 *  * @Author Hardy
 *  * @Date 2018年08月29日 15:23
 *  * @Version 1.0.0
 *  
 **/
@Data
@ApiModel(value="支付结果包装实体")
public class PayResponse implements Serializable {

    private static final long serialVersionUID = 8574700518420802176L;

    @ApiModelProperty(value="支付请求结果状态值 0 失败 1 成功",required = true)
    private int status;//支付请求结果状态值 0 失败 1 成功

    @ApiModelProperty(value="支付请求结果 error 异常 faild 失败 success 成功",required = true)
    private String result;//支付请求结果 error 异常 faild 失败 success 成功

    @ApiModelProperty(value="视图类型 1 form表单 2 二维码生成图片 3 二维码链接地址 4 浏览器跳转链接",required = true)
    private int type;//视图类型 1 form表单 2 二维码生成图片 3 二维码链接地址 4 浏览器跳转链接

    @ApiModelProperty(value="视图编码 from 表单 qrcode 二维码生成图片 qrurl 二维码链接地址 link 浏览器跳转链接",required = true)
    private String code;//视图编码 from 表单 qrcode 二维码生成图片 qrurl 二维码链接地址 link 浏览器跳转链接

    @ApiModelProperty(value="返回信息",required = true)
    private String message;//返回信息

    @ApiModelProperty(value="返回json参数",required = true)
    private Object data;//返回json参数

    public PayResponse(int status, String result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public PayResponse(int status, String result, String message, Object data) {
        this.status = status;
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public PayResponse(int status, String result, int type, String code, String message, Object data) {
        this.status = status;
        this.result = result;
        this.type = type;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //异常
    public static PayResponse error(int status, String result, String message) {
        return new PayResponse(status,result,message);
    }

    //失败
    public static PayResponse faild(int status, String result, String message, Object data) {
        return new PayResponse(status,result,message,data);
    }

    //成功
    public static PayResponse success(int status, String result, int type, String code, String message, Object data) {
        return new PayResponse(status,result,type,code,message,data);
    }


}
