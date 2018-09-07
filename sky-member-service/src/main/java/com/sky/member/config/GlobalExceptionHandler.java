/******************************************************************
 *
 *    Powered By tianxia-online.
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.auth.config
 *
 *    Filename:    GlobalExceptionHandler.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2018-2020
 *
 *    Company:     天下网络科技
 *
 *    @author: Golden
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年08月31日 15:38
 *
 *    Revision:
 *
 *    2018/8/31 15:38
 *        - first revision
 *
 *****************************************************************/
package com.sky.member.config;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 对于出现的异常进行处理
 * @Author Golden
 * @Date 2018年08月31日 15:38
 * @Version 1.0.0
 **/
import javax.servlet.http.HttpServletRequest;

import com.xiaoleilu.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     *
     * @param
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject defaultErrorHandler( Exception e){
        logger.error("", e);
//        String method=req.getMethod();
//        Map<String, String[]> param=req.getParameterMap();

        JSONObject jo = new JSONObject();
//        jo.put("message",e.getMessage()+" method="+method+" param="+param);
        jo.put("message",e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            jo.put("code",404);

        } else {
            jo.put("code",500);
        }
        return jo;
    }
}