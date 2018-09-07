/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.service.impl 
 *
 *    Filename:    TransferServiceImpl.java 
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
 *    Create at:   2018年08月28日 10:36 
 *
 *    Revision: 
 *
 *    2018/8/28 10:36 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.service.impl;

import com.sky.site.info.mapper.TransferVOMapper;
import com.sky.site.info.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:TransferServiceImpl
 * @Auther: Wilson
 * @Date: 2018/8/28 10:36
 * @Version:1.0.0
 */
@Service
public class TransferServiceImpl implements ITransferService {

    @Autowired
    private TransferVOMapper transferVOMapper;

    @Override
    public List<Map<String, Object>> selectTransferPage(Map<String, Object> map) {
        List<Map<String, Object>> transferVOList = transferVOMapper.selectTransferPage(map);
        List<Map<String, Object>> count = transferVOMapper.selectTransferCount(map);
        transferVOList.add(0,count.get(0));
        return transferVOList;
    }
}