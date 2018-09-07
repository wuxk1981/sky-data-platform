/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.service.impl 
 *
 *    Filename:    UserTreasureServiceImpl.java 
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
 *    Create at:   2018年08月28日 10:44 
 *
 *    Revision: 
 *
 *    2018/8/28 10:44 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.service.impl;

import com.sky.site.info.mapper.UserTreasureVOMapper;
import com.sky.site.info.service.IUserTreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:UserTreasureServiceImpl
 * @Auther: Wilson
 * @Date: 2018/8/28 10:44
 * @Version:1.0.0
 */
@Service
public class UserTreasureServiceImpl implements IUserTreasureService {

    @Autowired
    private UserTreasureVOMapper userTreasureVOMapper;

    @Override
    public int selectUserTreasurePageCount(Map<String, Object> map) {
        return userTreasureVOMapper.selectUserTreasurePageCount(map);
    }

    @Override
    public List<Map<String, Object>> selectUserTreasurePage(Map<String, Object> map) {
        return userTreasureVOMapper.selectUserTreasurePage(map);
    }
}