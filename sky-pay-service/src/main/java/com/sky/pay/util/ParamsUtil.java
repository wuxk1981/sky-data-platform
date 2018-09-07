/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    ParamsUtil.java 
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
 *    Create at:   2018年08月26日 17:41 
 *
 *    Revision: 
 *
 *    2018/8/26 17:41 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import com.sky.pay.exception.RPCPayException;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.security.Key;
import java.util.*;

/**
 *  * @ClassName ParamsUtil
 *  * @Description 参数工具类
 *  * @Author Hardy
 *  * @Date 2018年08月26日 17:41
 *  * @Version 1.0.0
 *  
 **/
public class ParamsUtil {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(ParamsUtil.class);

    /**
     * 功能描述:
     * 解析JSON格式的请求参数
     * @Author: Hardy
     * @Date: 2018年09月02日 22:30:48
     * @param request
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    public static Map<String,String> parseJsonParams(HttpServletRequest request) throws RPCPayException{
        try {
            Map<String, String> data = new HashMap<>();
            Enumeration enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                data.put(key, request.getParameter(key));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("解析JSON格式的请求参数异常:"+e.getMessage());
            throw new RPCPayException("error","解析JSON格式的请求参数异常",e.getMessage());
        }
    }

    /**
     * 功能描述:
     * 解析IO流格式的请求参数
     * @Author: Hardy
     * @Date: 2018年09月02日 22:31:49
     * @param request
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    public static Map<String,String> parseIOParams(HttpServletRequest request) throws RPCPayException{
        return null;
    }

    /**
     * 功能描述:
     * 解析XML格式的请求参数
     * @Author: Hardy
     * @Date: 2018年09月02日 22:32:41
     * @param request
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    public static Map<String,String> paraseXMlParams(HttpServletRequest request) throws RPCPayException{
        return null;
    }

}
