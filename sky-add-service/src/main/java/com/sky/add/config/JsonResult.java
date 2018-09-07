/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.quick.druid.config 
 *
 *    Filename:    JsonResult.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Tammy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年08月26日 13:40 
 *
 *    Revision: 
 *
 *    2018/8/26 13:40 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.add.config;

import lombok.Data;

/**
 *  * @ClassName JsonResult
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 13:40
 *  * @Version 1.0.0
 *  
 **/
@Data
public class JsonResult {
    private String status = null;

    private Object result = null;

}
