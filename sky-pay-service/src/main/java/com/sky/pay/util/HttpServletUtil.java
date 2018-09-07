/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    HttpServletUtil.java 
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
 *    Create at:   2018年08月28日 17:37 
 *
 *    Revision: 
 *
 *    2018/8/28 17:37 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import com.xiaoleilu.hutool.util.StrUtil;

import javax.servlet.http.HttpServletRequest;

/**
 *  * @ClassName HttpServletUtil
 *  * @Description 请求servlet工具类
 *  * @Author Hardy
 *  * @Date 2018年08月28日 17:37
 *  * @Version 1.0.0
 *  
 **/
public class HttpServletUtil {

    /**
     * 功能描述:
     * 获取请求IP
     * @Author: Hardy
     * @Date: 2018年08月28日 17:36:18
     * @param request
     * @return: java.lang.String
     **/
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StrUtil.isNotBlank(ip) && ip.equals("unKnown")){
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }else{
            ip = request.getHeader("X-Real-IP");
            if(StrUtil.isNotBlank(ip) && ip.equals("unKnown")){
                return ip;
            }
        }
        return String.valueOf(request.getRemoteAddr());
    }

    /**
     * 功能描述:
     * 获取请求URL
     * @Author: Hardy
     * @Date: 2018年08月28日 17:38:52
     * @param request
     * @return: java.lang.String
     **/
    public static String getUrl(HttpServletRequest request){
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        return tempContextUrl;
    }
}
