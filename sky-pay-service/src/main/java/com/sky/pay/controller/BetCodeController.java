/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.controller 
 *
 *    Filename:    BetCodeController.java 
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
 *    Create at:   2018年09月05日 16:27 
 *
 *    Revision: 
 *
 *    2018/9/5 16:27 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.controller;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.BetCodeResponse;
import com.sky.pay.po.ResultResponse;
import com.sky.pay.service.BetCodeService;
import com.sky.pay.util.HttpUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  * @ClassName BetCodeController
 *  * @Description 下注(打码量)接口
 *  * @Author Hardy
 *  * @Date 2018年09月05日 16:27
 *  * @Version 1.0.0
 *  
 **/
@RestController
@RequestMapping("/pay/betcode")
public class BetCodeController {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(BetCodeController.class);

    @Autowired
    private BetCodeService betCodeService;

    /**
     * 功能描述:
     * 获取用户的打码量
     * @Author: Hardy
     * @Date: 2018年09月05日 16:30:09
     * @param uid
     * @return: com.sky.pay.po.ResultResponse
     **/
    @GetMapping(value="/findBetCodeByUid")
    @ApiOperation(value = "查询用户打码量接口",notes = "查询用户打码量接口")
    public ResultResponse findBetCodeByUid(@ApiParam(required = true,name="uid",value = "用户ID") @RequestParam(required = true) Integer uid){
        try {
            BetCodeResponse betcode = betCodeService.findBetCodeByUid(uid);
            return ResultResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,"查询成功",betcode);
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.error("查询用户打码量异常:"+e.getMessage());
            return ResultResponse.error(ResponseConstant.ERROR_STATUS,ResponseConstant.ERROR_CODE,e.getErrorMsg());
        }
    }
}
