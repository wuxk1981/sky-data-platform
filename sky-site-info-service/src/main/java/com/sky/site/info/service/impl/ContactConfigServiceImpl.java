/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.service.impl 
 *
 *    Filename:    ContactConfigServiceImpl.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author: TX
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年08月28日 19:32 
 *
 *    Revision: 
 *
 *    2018/8/28 19:32 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.service.impl;

import com.sky.site.info.entity.ContactConfigVO;
import com.sky.site.info.mapper.ContactConfigVOMapper;
import com.sky.site.info.service.IContactConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:ContactConfigServiceImpl
 * @Auther: Wilson
 * @Date: 2018/8/28 19:32
 * @Version:1.0.0
 */
@Service
public class ContactConfigServiceImpl implements IContactConfigService {

    @Autowired
    private ContactConfigVOMapper contactConfigVOMapper;

    @Override
    public ContactConfigVO selectContactByCagent(Map<String, Object> map) {
        return contactConfigVOMapper.selectContactByCagent(map);
    }
}