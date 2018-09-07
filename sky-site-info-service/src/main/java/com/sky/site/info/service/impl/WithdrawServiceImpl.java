/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.service.impl 
 *
 *    Filename:    WithdrawServiceImpl.java 
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
 *    Create at:   2018年08月28日 10:21 
 *
 *    Revision: 
 *
 *    2018/8/28 10:21 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.service.impl;

import com.sky.site.info.mapper.WithdrawVOMapper;
import com.sky.site.info.service.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:WithdrawServiceImpl
 * @Auther: Wilson
 * @Date: 2018/8/28 10:21
 * @Version:1.0.0
 */
@Service
public class WithdrawServiceImpl implements IWithdrawService {

    @Autowired
    private WithdrawVOMapper withdrawVOMapper;

    @Override
    public List<Map<String, Object>> selectWithDrawPage(Map<String, Object> map) {
        List<Map<String, Object>> withDrawVoList= withdrawVOMapper.selectWithDrawPage(map);
        List<Map<String, Object>> count = withdrawVOMapper.selectWithDrawCount(map);
        withDrawVoList.add(0,count.get(0));
        return withDrawVoList;
    }
}