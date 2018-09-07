/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    ReflectClazzUtil.java 
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
 *    Create at:   2018年08月26日 16:56 
 *
 *    Revision: 
 *
 *    2018/8/26 16:56 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import com.sky.pay.reflect.pay.ReflectPayService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 *  * @ClassName ReflectClazzUtil
 *  * @Description 反射类工具类
 *  * @Author Hardy
 *  * @Date 2018年08月26日 16:56
 *  * @Version 1.0.0
 *  
 **/
public class ReflectClazzUtil {
    //支付反射支付实现类包名
    private static final String PAY_PACK_NAME="com.sky.pay.reflect.pay.impl";
    //支付反射支付实现类名
    private static final String PAY_CLAZZ_NAME="ReflectPayServiceImpl";

    /**
     * 功能描述:
     * 获取支付反射支付实现类
     * @Author: Hardy
     * @Date: 2018年08月26日 17:12:37
     * @param prefixName
     * @return: com.sky.pay.reflect.pay.ReflectPayService
     **/
    public static ReflectPayService getPayReflectClazzName(String prefixName,Map<String,String> data) {
        ReflectPayService reflectPayService = null;
        Constructor<?> constructor = null;
        StringBuffer sb = new StringBuffer();
        sb.append(PAY_PACK_NAME).append(".");// 包名
        sb.append(prefixName);//支付商名称
        sb.append(PAY_CLAZZ_NAME);//类名
        try {
            //创建构造器
            constructor = Class.forName(sb.toString()).getConstructor(Map.class);
            reflectPayService = (ReflectPayService)constructor.newInstance(data);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return reflectPayService;
    }

    public static ReflectPayService getPayReflectClazzName(String prefixName,Map<String,String> data,String type){
        ReflectPayService reflectPayService = null;
        Constructor<?> constructor = null;
        StringBuffer sb = new StringBuffer();
        sb.append(PAY_PACK_NAME).append(".");// 包名
        sb.append(prefixName);//支付商名称
        sb.append(PAY_CLAZZ_NAME);//类名
        try {
            //创建构造器
            constructor = Class.forName(sb.toString()).getConstructor(Map.class,String.class);
            reflectPayService = (ReflectPayService)constructor.newInstance(data,type);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return reflectPayService;
    }
}
