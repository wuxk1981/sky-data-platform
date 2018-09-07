/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.service.impl 
 *
 *    Filename:    TransferServiceImpl.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Hardy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年08月26日 11:36 
 *
 *    Revision: 
 *
 *    2018/8/26 11:36 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.service.impl;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.entity.Transfer;
import com.sky.pay.entity.TransferFaild;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.mapper.TransferFaildMapper;
import com.sky.pay.mapper.TransferMapper;
import com.sky.pay.po.PageVO;
import com.sky.pay.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  * @ClassName TransferServiceImpl
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Hardy
 *  * @Date 2018年08月26日 11:36
 *  * @Version 1.0.0
 *  
 **/
@Service
public class TransferServiceImpl implements TransferService {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    @Autowired
    private TransferMapper transferMapper;

    @Autowired
    private TransferFaildMapper transferFaildMapper;

    @Override
    public PageVO findTransferByPage(Integer type, Integer currentPage, Integer pageCount) throws RPCPayException {
        try {
            int totalCount = 0;
            if (type == 1){
                //转账成功
                List<Transfer> transfers = transferMapper.findByPage(currentPage,pageCount);
                //查询总条数
                totalCount = transferMapper.sumTotalCount();
                return new PageVO(currentPage,pageCount,totalCount,transfers);
            }else {
                //转账失败
                List<TransferFaild> transferFailds = transferFaildMapper.findByPage(currentPage,pageCount);
                //查询总条数
                totalCount = transferFaildMapper.sumTotalCount();
                return new PageVO(currentPage,pageCount,totalCount,transferFailds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("分页查询转账信息列表异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"分页查询转账信息列表异常",e.getMessage());
        }
    }

}
