/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    PropertiesUtil.java 
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
 *    Create at:   2018年08月26日 20:16 
 *
 *    Revision: 
 *
 *    2018/8/26 20:16 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import com.xiaoleilu.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *  * @ClassName PropertiesUtil
 *  * @Description 配置文件工具类
 *  * @Author Hardy
 *  * @Date 2018年08月26日 20:16
 *  * @Version 1.0.0
 *  
 **/
public class PropertiesUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 功能描述:
     * 获取PC端的properties文件
     * @Author: Hardy
     * @Date: 2018年08月26日 20:18:59
     * @param
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    public static Map<String, String> readPcProperties() {
        Map<String,String> pcmap = new HashMap<>();
        Properties pro = new Properties();
        //基于ClassLoder读取配置文件
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("conf/scanpay.properties");
        try {
            pro.load(in);
        } catch (Exception e) {
            logger.info("读取PC端Properties文件异常"+e.getMessage());
            System.out.println("load file faile." + e);
        }

        @SuppressWarnings("unchecked")
        Map<String, String> map = new HashMap<>((Map) pro);
        Set propertySet = map.entrySet();
        for (Object o : propertySet) {
            Map.Entry entry = (Map.Entry) o;
            pcmap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return pcmap;
    }

    /**
     * 功能描述:
     * 获取移动端properties文件
     * @Author: Hardy
     * @Date: 2018年08月26日 20:22:53
     * @param
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    public static Map<String, String> readMobileProperties() {
        Map<String,String> mobilemap = new HashMap<>();
        Properties pro = new Properties();
        InputStream in;
        in = PropertiesUtil.class.getClassLoader().getResourceAsStream("conf/scanmobilepay.properties");
        try {
            pro.load(in);
        } catch (Exception e) {
            System.out.println("load file faile." + e);
        }

        @SuppressWarnings("unchecked")
        Map<String, String> map = new HashMap<String, String>((Map) pro);
        Set propertySet = map.entrySet();
        for (Object o : propertySet) {
            Map.Entry entry = (Map.Entry) o;
            mobilemap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return mobilemap;
    }

    /**
     * 功能描述:
     * 读取file.properties文件
     * @Author: Hardy
     * @Date: 2018年09月03日 15:07:40
     * @param
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    public static Map<String,String> readFileProperties() throws Exception{
        try {
            Map<String,String> mobilemap = new HashMap<>();
            Properties pro = new Properties();
            InputStream in;
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("conf/file.properties");
            pro.load(in);
            @SuppressWarnings("unchecked")
            Map<String, String> map = new HashMap<String, String>((Map) pro);
            Set propertySet = map.entrySet();
            for (Object o : propertySet) {
                Map.Entry entry = (Map.Entry) o;
                mobilemap.put(entry.getKey().toString(), entry.getValue().toString());
            }
            return mobilemap;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("load file faile."+e.getMessage());
            throw new Exception("load file faild from file.properties");
        }
    }


    public static void main(String[] args) {
        Map<String,String> data = readPcProperties();

        if(data != null && !data.isEmpty()){
            System.out.println(JSONUtil.toJsonStr(data));
        }
    }
}
