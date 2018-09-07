/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.util 
 *
 *    Filename:    MapUtil.java 
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
 *    Create at:   2018年08月31日 10:46 
 *
 *    Revision: 
 *
 *    2018/8/31 10:46 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 *  * @ClassName MapUtil
 *  * @Description this is a about map tools clazz
 *  * @Author Hardy
 *  * @Date 2018年08月31日 10:46
 *  * @Version 1.0.0
 *  
 **/
public class MapSortUtil {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(MapSortUtil.class);

    /**
     * 功能描述:
     * this is a method about map sort by keys
     * @Author: Hardy
     * @Date: 2018年08月31日 14:36:40
     * @param data
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    public static Map<String,String> sortByKeys(Map<String,String>data)throws Exception{

        try {
            // let's sort this map by values first
            Map<String, String> sorted = data
                    .entrySet()
                    .stream()
                    .sorted(comparingByKey())
                    .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
            return sorted;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Map is sort by keys is exception:"+e.getMessage());
            throw new Exception("Map is sort by keys is exception");
        }
    }

    /**
     * 功能描述:
     * this is a method about map sort by values
     * @Author: Hardy
     * @Date: 2018年08月31日 14:37:16
     * @param data
     * @return: java.util.Map<java.lang.String , java.lang.String>
     **/
    public static Map<String,String> sortByValues(Map<String,String> data) throws Exception{
        try {
            // now let's sort the map in decreasing order of value
            Map<String, String> sorted = data
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(comparingByValue()))
                    .collect(
                            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                    LinkedHashMap::new));

            return sorted;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Map is sort by values is exception:"+e.getMessage());
            throw new Exception("Map is sort by values is exception!");
        }
    }
}
