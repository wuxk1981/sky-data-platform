/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.service.impl 
 *
 *    Filename:    InternalMessageServiceImpl.java 
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
 *    Create at:   2018年08月30日 14:01 
 *
 *    Revision: 
 *
 *    2018/8/30 14:01 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.service.impl;

import com.sky.site.info.mapper.InternalMessageVOMapper;
import com.sky.site.info.service.IInternalMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InternalMessageServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Wilson
 * @Date 2018年08月30日 14:01
 * @Version 1.0.0
 **/
@Service
public class InternalMessageServiceImpl implements IInternalMessageService {

    @Autowired
    private InternalMessageVOMapper internalMessageVOMapper;

    @Override
    public List<Map<String, Object>> selectMessagePageByStatus(Map<String, Object> params) {
        return internalMessageVOMapper.selectMessagePageByStatus(params);
    }

    @Override
    public List<Map<String, Object>> selectMessageReadCount(Map<String, Object> params) {
        return internalMessageVOMapper.selectMessageReadCount(params);
    }
}