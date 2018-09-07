/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.utils 
 *
 *    Filename:    DateUtils.java
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author: Wilson
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年09月02日 14:06 
 *
 *    Revision: 
 *
 *    2018/9/2 14:06 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.utils;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

/**
 * @ClassName DateUtils
 * @Description JDK8日期处理
 * @Author Wilson
 * @Date 2018年09月02日 14:06
 * @Version 1.0.0
 **/
public class DateUtils {

    public static long dateDiff(String startDate, String endDate) {
        return DateUtil.between(DateUtil.parseDate(startDate), DateUtil.parseDate(endDate), DateUnit.DAY);
    }
}