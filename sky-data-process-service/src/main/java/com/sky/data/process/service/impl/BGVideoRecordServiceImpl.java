/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.data.process.service.impl 
 *
 *    Filename:    BGVideoRecordServiceImpl.java 
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
 *    Create at:   2018年09月07日 23:05 
 *
 *    Revision: 
 *
 *    2018/9/7 23:05 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.data.process.service.impl;

import com.sky.data.process.entity.BGVideoRecord;
import com.sky.data.process.mapper.BGVideoRecordMapper;
import com.sky.data.process.service.IBGVideoRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  * @ClassName BGVideoRecordServiceImpl
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年09月07日 23:05
 *  * @Version 1.0.0
 *  
 **/
@Service
public class BGVideoRecordServiceImpl implements IBGVideoRecordService {

    @Autowired
    private BGVideoRecordMapper bgVideoRecordMapper;
    @Override
    public BGVideoRecord selectByPrimaryKey(Long id) {
        return bgVideoRecordMapper.selectByPrimaryKey(id);
    }
}
