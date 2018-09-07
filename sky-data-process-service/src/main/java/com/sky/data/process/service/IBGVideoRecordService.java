/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.data.process.service.impl 
 *
 *    Filename:    IBGVideoRecordService.java 
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
 *    Create at:   2018年09月07日 23:04 
 *
 *    Revision: 
 *
 *    2018/9/7 23:04 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.data.process.service;

import com.sky.data.process.entity.BGVideoRecord;

/**
 *  * @ClassName IBGVideoRecordService
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年09月07日 23:04
 *  * @Version 1.0.0
 *  
 **/
public interface IBGVideoRecordService {
    BGVideoRecord selectByPrimaryKey(Long id);
}
