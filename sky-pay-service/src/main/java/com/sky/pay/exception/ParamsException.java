/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.exception 
 *
 *    Filename:    ParamsException.java 
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
 *    Create at:   2018年08月25日 21:46 
 *
 *    Revision: 
 *
 *    2018/8/25 21:46 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.exception;

/**
 *  * @ClassName ParamsException
 *  * @Description 参数异常
 *  * @Author Hardy
 *  * @Date 2018年08月25日 21:46
 *  * @Version 1.0.0
 *  
 **/
public class ParamsException extends Exception{

    private String errorCode;

    private String errorMsg;

    public ParamsException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ParamsException(String errorCode, String errorMsg,String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ParamsException(String errorCode, String errorMsg,Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ParamsException(String errorCode, String errorMsg,String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
