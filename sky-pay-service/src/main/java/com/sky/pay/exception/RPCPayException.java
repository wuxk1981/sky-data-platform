/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.exception 
 *
 *    Filename:    RPCPayException.java 
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
 *    Create at:   2018年08月25日 21:47 
 *
 *    Revision: 
 *
 *    2018/8/25 21:47 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.exception;

import lombok.Data;

/**
 *  * @ClassName RPCPayException
 *  * @Description 服务异常
 *  * @Author Hardy
 *  * @Date 2018年08月25日 21:47
 *  * @Version 1.0.0
 *  
 **/
@Data
public class RPCPayException extends Exception {

    private String errorCode;

    private String errorMsg;

    public RPCPayException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RPCPayException(String errorCode, String errorMsg,String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RPCPayException(String errorCode, String errorMsg,Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RPCPayException(String errorCode, String errorMsg,String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RPCPayException(String errorCode, String errorMsg,String message,Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
