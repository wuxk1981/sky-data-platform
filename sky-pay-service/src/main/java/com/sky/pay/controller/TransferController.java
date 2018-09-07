/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.controller 
 *
 *    Filename:    TransferController.java 
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
 *    Create at:   2018年08月26日 11:23 
 *
 *    Revision: 
 *
 *    2018/8/26 11:23 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.controller;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.mapper.TransferMapper;
import com.sky.pay.po.PageVO;
import com.sky.pay.po.ResultResponse;
import com.sky.pay.service.TransferService;
import com.xiaoleilu.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  * @ClassName TransferController
 *  * @Description 转账
 *  * @Author Hardy
 *  * @Date 2018年08月26日 11:23
 *  * @Version 1.0.0
 *  
 **/
@RestController
@RequestMapping("/pay/transfer")
@Api(description="转账相关类接口")
public class TransferController {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    @Autowired
    private TransferService transferService;

    /**
     * 功能描述:
     *查询转账状态
     * @Author: Hardy
     * @Date: 2018年08月26日 11:26:06
     * @param
     * @return: com.sky.pay.common.ResponseResult
     **/
    @PostMapping("/queryTransferStatus")
    @ApiOperation(value = "查询转账订单状态接口",notes = "通过转账订单号查询转账订单状态")
    public JSONObject queryTransferStatus(@ApiParam(required = true,name = "orderNo",value = "订单号") @RequestParam(required = true) String orderNo){

        return null;
    }

    @GetMapping("/queryTransferInfo")
    @ApiOperation(value = "查询转账信息接口",notes = "通过转账订单号查询转账订单详情")
    public JSONObject queryTransferInfo(@ApiParam(required = true,name="orderNo",value = "订单号")@RequestParam(required = true) String orderNo) throws RPCPayException{
        return null;
    }

    /**
     * 功能描述:
     * 查询转账分页列表
     * @Author: Hardy
     * @Date: 2018年09月05日 20:24:15
     * @param type
     * @param currentPage
     * @param pageCount
     * @return: com.sky.pay.po.ResultResponse
     **/
    @GetMapping("/findTransferByPage")
    @ApiOperation(value = "分页查询转账记录信息",notes = "分页查询转账记录信息")
    public ResultResponse findTransferByPage(@ApiParam(required = true,name="type",value = "转账类型:1 成功 0 失败")@RequestParam(required = true) Integer type,
                                                @ApiParam(required = true,name="currentPage",value="当前页数")@RequestParam(required = true) Integer currentPage,
                                                @ApiParam(required = true,name="pageCount",value ="每页显示条数")@RequestParam(required = true) Integer pageCount){
        try {
            PageVO page = transferService.findTransferByPage(type,currentPage,pageCount);
            return ResultResponse.success(ResponseConstant.SUCCESS_STATUS,ResponseConstant.SUCCESS_CODE,"查询成功",page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("分页查询转账记录列表异常:"+e.getMessage());
            return ResultResponse.error(ResponseConstant.ERROR_STATUS,ResponseConstant.ERROR_CODE,"分页查询转账记录列表异常");
        }
    }
}
