/******************************************************************
 *
 *    Powered By tianxia-online.
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.common.web.entity
 *
 *    Filename:    RetEntity.java
 *
 *    Description: 统一常量池
 *
 *    Copyright:   Copyright (c) 2018-2020
 *
 *    Company:     天下网络科技
 *
 *    @author:     Tammy
 *
 *    @version:    1.0.0
 *
 *    Create at:   2018年09月01日 10:37
 *
 *    Revision:
 *
 *    2018/9/1 10:37
 *        - first revision
 *
 *****************************************************************/
package com.sky.common;

import org.apache.http.HttpStatus;

/**
 *  * @ClassName RetEntity
 *  * @Description 统一常量池
 *  * @Author Tammy
 *  * @Date 2018年09月01日 10:37
 *  * @Version 1.0.0
 *  
 **/
public class Constant {

    public static class Code implements HttpStatus {

        // feign客户端调用失败
        public final static int SC_FEIGN_FALLBACK = 5001;

    }

    public static class Message {
        public final static String SC_OK = "操作成功.";
        public final static String SC_INTERNAL_SERVER_ERROR = "操作失败(服务器内部错误).";
        public final static String SC_FEIGN_FALLBACK = "服务器开小差了~请稍后重试!";
        public final static String SC_FORBIDDEN = "没有权限访问该资源~";
        public final static String SC_UNAUTHORIZED = "授权失败~";
    }

    /**
     * 微服务名统一管理
     */
    public static class Service {
        public final static String FASTJEE_REGISTRATION = "fastjee-registration";
        public final static String FASTJEE_CONFIG = "fastjee-config";
        public final static String FASTJEE_GATEWAY = "fastjee-gateway";
        public final static String FASTJEE_AUTHORITY = "fastjee-authority";
        public final static String FASTJEE_USERCENTER = "fastjee-usercenter";
        public final static String SKY_MEMBER_SERVICE = "SKY-MEMBER-SERVICE";
    }

    public static class Auth {
        public final static String TOKEN_HEADER = "Authorization";
        public final static String TOKEN_VALUE_PREV = "Bearer ";
        // TODO: JWT签名,暂时写死
        public final static String JWT_SIGNING_KEY = "wenzewoo";
    }
}
