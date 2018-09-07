/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.service.impl 
 *
 *    Filename:    BankServiceImpl.java 
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
 *    Create at:   2018年08月25日 22:13 
 *
 *    Revision: 
 *
 *    2018/8/25 22:13 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.service.impl;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.entity.UserCard;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.mapper.UserCardMapper;
import com.sky.pay.po.PageVO;
import com.sky.pay.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  * @ClassName BankServiceImpl
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Hardy
 *  * @Date 2018年08月25日 22:13
 *  * @Version 1.0.0
 *  
 **/
@Service
public class BankServiceImpl implements BankService {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
    private UserCardMapper userCardMapper;

    /**
     * 功能描述:
     * 分页查询银行卡列表
     * @Author: Hardy
     * @Date: 2018年09月05日 18:36:42
     * @param currentPage
     * @param pageCount
     * @return: com.sky.pay.po.PageVO
     **/
    public PageVO findByPage(int currentPage, int pageCount) throws RPCPayException {
        try {
            //分页查询银行列表
            List<UserCard> userCards = userCardMapper.findByPage(currentPage,pageCount);
            //查询银行列表总条数
            int totalCount = userCardMapper.sumTotalCount();
            return new PageVO(currentPage,pageCount,totalCount,userCards);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("分页查询银行列表异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"分页查询银行列表异常!",e.getMessage());
        }
    }

    @Override
    public List<UserCard> getBankCardsByUid(Integer uid) throws RPCPayException {
        try {
            //分页查询银行列表
            List<UserCard> userCards = userCardMapper.getBankCardsByUid(uid);
            return userCards;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("分页查询银行列表异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"分页查询银行列表异常!",e.getMessage());
        }
    }
}
