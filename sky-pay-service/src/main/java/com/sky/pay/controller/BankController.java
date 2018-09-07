/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.controller 
 *
 *    Filename:    BankController.java 
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
 *    Create at:   2018年08月25日 21:55 
 *
 *    Revision: 
 *
 *    2018/8/25 21:55 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.controller;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.entity.UserCard;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.PageVO;
import com.sky.pay.po.ResultResponse;
import com.sky.pay.service.BankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  * @ClassName BankController
 *  * @Description 银行卡接口
 *  * @Author Hardy
 *  * @Date 2018年08月25日 21:55
 *  * @Version 1.0.0
 *  
 **/
@RestController
@RequestMapping("/pay/bank")
@Api(description="银行相关接口")
public class BankController {

    //银行卡log日志
    private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    @Autowired
    private BankService bankService;

    /**
     * 功能描述:
     *分页查询银行卡列表
     * @Author: Hardy
     * @Date: 2018年08月25日 22:12:10
     * @param currentPage
    * @param pageCount
     * @return: com.sky.pay.common.ResponseResult
     **/
    @GetMapping("/findByPage")
    @ApiOperation(value="银行卡列表接口", notes="分页查询银行卡列表")
    public ResultResponse findByPage(@ApiParam(required=true, name="currentPage", value="分页页数")
                                         @RequestParam(required = true) int currentPage,
                                             @ApiParam(required=true, name="pageCount", value="分页条数")
                                         @RequestParam(required = true) int pageCount){
        try {
            PageVO page = bankService.findByPage(currentPage,pageCount);
            return ResultResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,"查询成功",page);
        } catch (RPCPayException e) {
            e.printStackTrace();
            return ResultResponse.success(ResponseConstant.ERROR_STATUS,ResponseConstant.ERROR_CODE,"查询异常:"+e.getErrorMsg(),null);
        }
    }

    @GetMapping("/getBankCards")
    @ApiOperation(value="获取用户银行卡列表", notes="获取用户银行卡列表")
    public ResultResponse getBankCardsByUid(@ApiParam(required = true,name="uid",value = "用户ID") @RequestParam(required = true) Integer uid){
        try {
            List<UserCard> cards = bankService.getBankCardsByUid(uid);
            return ResultResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,"查询成功",cards);
        } catch (RPCPayException e) {
            e.printStackTrace();
            return ResultResponse.success(ResponseConstant.ERROR_STATUS,ResponseConstant.ERROR_CODE,"查询异常:"+e.getErrorMsg(),null);
        }
    }
}
