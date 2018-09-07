/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.service.impl 
 *
 *    Filename:    RechargeInfoServiceImpl.java 
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
 *    Create at:   2018年08月27日 14:37 
 *
 *    Revision: 
 *
 *    2018/8/27 14:37 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.service.impl;

import com.sky.site.info.mapper.RechargeVOMapper;
import com.sky.site.info.service.IRechargeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:RechargeInfoServiceImpl
 * @Auther: Wilson
 * @Date: 2018/8/27 14:37
 * @Version:1.0.0
 */
@Service
public class RechargeInfoServiceImpl implements IRechargeInfoService {

    @Autowired
    private RechargeVOMapper rechargeVOMapper;

    @Override
    public List<Map<String,Object>> selectByStatusPage(Map<String, Object> map) {
        List<Map<String, Object>> rechargeVoList = rechargeVOMapper.selectByStatusPage(map);
        List<Map<String, Object>> count = rechargeVOMapper.selectReChargeCount(map);
        rechargeVoList.add(0,count.get(0));
        return rechargeVoList;
    }


}