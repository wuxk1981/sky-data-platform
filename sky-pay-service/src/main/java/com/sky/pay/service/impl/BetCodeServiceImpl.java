/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.service.impl 
 *
 *    Filename:    BetCodeServiceImpl.java 
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
 *    Create at:   2018年09月05日 16:32 
 *
 *    Revision: 
 *
 *    2018/9/5 16:32 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.service.impl;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.entity.UserPlatformReport;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.mapper.UserPlatformReportMapper;
import com.sky.pay.po.BetCodeResponse;
import com.sky.pay.service.BetCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  * @ClassName BetCodeServiceImpl
 *  * @Description 下注(打码量)
 *  * @Author Hardy
 *  * @Date 2018年09月05日 16:32
 *  * @Version 1.0.0
 *  
 **/
@Service
public class BetCodeServiceImpl implements BetCodeService {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(BetCodeServiceImpl.class);

    @Autowired
    private UserPlatformReportMapper userPlatformReportMapper;

    /**
     * 功能描述:
     * 获取用户的打码量
     * @Author: Hardy
     * @Date: 2018年09月05日 16:36:48
     * @param uid
     * @param pageNo
     * @param pageSize
     * @return: void
     **/
    @Override
    public BetCodeResponse findBetCodeByUid(Integer uid) throws RPCPayException {
        logger.info("获取用户的下注(打码量)开始========================START==================================");
        BetCodeResponse betcode = new BetCodeResponse();
        try {
            //查询用户打码量列表
            List<UserPlatformReport> userPlatformReports = userPlatformReportMapper.findBetCodeByUid(uid);
            if (userPlatformReports != null && userPlatformReports.size() > 0){
                betcode.setBetcodes(userPlatformReports);
            }
            //统计用户总的打码量
            UserPlatformReport userPlatformReport = userPlatformReportMapper.sumBetCodeByUid(uid);
            if (userPlatformReport != null){
                betcode.setTotalBetAmount(userPlatformReport.getBetAmount());
                betcode.setTotalNetAmount(userPlatformReport.getNetAmount());
                betcode.setTotalNoteNum(userPlatformReport.getNoteNum());
                betcode.setTotalValidBetAmount(userPlatformReport.getValidBetAmount());
            }

            return betcode;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取用户下注(打码量)异常:"+e.getMessage());
            throw new RPCPayException(ResponseConstant.ERROR_CODE,"获取用户下注(打码量)异常",e.getMessage());
        }
    }
}
