/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.controller 
 *
 *    Filename:    IssuedController.java 
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
 *    Create at:   2018年08月26日 14:45 
 *
 *    Revision: 
 *
 *    2018/8/26 14:45 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.controller;

import com.sky.pay.exception.RPCPayException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  * @ClassName IssuedController
 *  * @Description 下发(提现)接口
 *  * @Author Hardy
 *  * @Date 2018年08月26日 14:45
 *  * @Version 1.0.0
 *  
 **/
@RestController
@RequestMapping("/pay/issued")
@Api(description = "下发(提现、代付)相关类接口")
public class IssuedController {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(IssuedController.class);

    @PostMapping("/submit")
    @ApiOperation(value = "下发申请接口",notes = "下发(代付、提现)申请接口")
    public String submit(
            @ApiParam(required = true,name = "orderNo",value = "订单号")
            @RequestParam(required = true) String orderNo,
            @ApiParam(required = true,name = "amount",value = "订单金额")
            @RequestParam(required = true) Double amount)throws RPCPayException{

        return null;
    }
}
