/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.exception 
 *
 *    Filename:    PayHttpException.java 
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
 *    Create at:   2018年08月26日 16:48 
 *
 *    Revision: 
 *
 *    2018/8/26 16:48 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.exception;

/**
 *  * @ClassName PayHttpException
 *  * @Description 支付请求异常
 *  * @Author Hardy
 *  * @Date 2018年08月26日 16:48
 *  * @Version 1.0.0
 *  
 **/
public class PayHttpException extends Exception{

    private String errorCode;

    private String errorMsg;

    public PayHttpException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public PayHttpException(String message, String errorCode, String errorMsg) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public PayHttpException(String message, Throwable cause, String errorCode, String errorMsg) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public PayHttpException(Throwable cause, String errorCode, String errorMsg) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public PayHttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode, String errorMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
