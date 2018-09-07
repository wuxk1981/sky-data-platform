/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.controller 
 *
 *    Filename:    HistoryController.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author: Wilson
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年08月28日 10:58 
 *
 *    Revision: 
 *
 *    2018/8/28 10:58 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.site.info.service.*;
import com.sky.site.info.utils.DateUtils;
import com.sky.site.info.utils.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 历史记录
 * 以下方法均应该从redis中取user值并判断登录状态
 * @ClassName:HistoryController
 * @Auther: Wilson
 * @Date: 2018/8/28 10:58
 * @Version:1.0.0
 */
@Api(description = "历史记录服务")
@RestController
@RequestMapping(value = "/history")
public class HistoryController {

    private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);

    private static final long DAYS_30=30;
    private static final long DAYS_60=60;

    /**存款**/
    @Autowired
    private IRechargeInfoService rechargeInfoService;

    /**提款**/
    @Autowired
    private IWithdrawService withdrawService;

    /**转账**/
    @Autowired
    private ITransferService transferService;

    /**资金流水**/
    @Autowired
    private IUserTreasureService userTreasureService;

    /**注单记录**/
    @Autowired
    private IBetRecordService betRecordService;

   /* 
   *功能描述: 获取存款记录
   * 
   *@Author: Wilson
   *@Date: 2018年08月29日 20:55:16
   * @param params
   * @param pageSize
   * @param pageNo
   *@return: java.lang.Object 
   **/
    @ApiOperation(value = "获取存款记录", notes = "获取存款记录接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"status\":\"0\",\"type\":\"4\",\"bdate\":\"2018-04-01\",\"edate\":\"2018-09-01\",\"uid\":\"526183\"}"),
            @ApiImplicitParam(paramType = "query", name="pageSize",dataType = "integer", required = true,value = "每页记录数"),
            @ApiImplicitParam(paramType = "query", name="pageNo",dataType = "integer", required = true,value = "当前页码")
    })
    @RequestMapping(value = "/getRechargePage", method = RequestMethod.POST)
    @ResponseBody
    public Object getRechargePage(Integer pageSize,Integer pageNo,@RequestParam Map<String,Object> params) {
        //先从Redis中获取并判断登录状态
        try {
            if (params.containsKey("params")){
                params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
            }

            long days = DateUtils.dateDiff(params.get("bdate").toString(),params.get("edate").toString());

            if (days>DAYS_30){
                return "时间错误";
            }

            if(params.containsKey("status")){
                if("0".equals(params.get("status"))){//0  paying
                    params.put("status","paying");
                }else if("1".equals(params.get("status"))){//1 success
                    params.put("status","success");
                }else if("2".equals(params.get("status"))){//2 fail
                    params.put("status","fail");
                }
            }

            int starLimit = (pageNo - 1) * pageSize;
            params.put("starLimit", starLimit);
            params.put("pageSize", pageSize);

            List<Map<String,Object>> rechargeVOList=rechargeInfoService.selectByStatusPage(params);

            return rechargeVOList;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 功能描述: 获取提款记录
     *
     * @Author: Wilson
     * @Date: 2018/8/28 19:07 
     * @param params
     * @param pageSize
     * @param pageNo
     * @return: java.lang.Object
     **/
    @ApiOperation(value = "获取提款记录", notes = "获取提款记录接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"status\":\"0\",\"bdate\":\"2017-04-01\",\"edate\":\"2018-09-01\",\"uid\":\"526717\"}"),
            @ApiImplicitParam(paramType = "query", name="pageSize",dataType = "integer", required = true,value = "每页记录数"),
            @ApiImplicitParam(paramType = "query", name="pageNo",dataType = "integer", required = true,value = "当前页")
    })
    @RequestMapping(value = "/getWithDrawPage", method = RequestMethod.POST)
    @ResponseBody
    public Object getWithDrawPage(@RequestParam Map<String,Object> params, Integer pageSize, Integer pageNo){
        //先从Redis中获取并判断登录状态
        try {
            if (params.containsKey("params")){
                params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
            }

            long days = DateUtils.dateDiff(params.get("bdate").toString(),params.get("edate").toString());

            if (days>DAYS_30){
                return "时间错误";
            }

            int starLimit = (pageNo - 1) * pageSize;
            params.put("starLimit", starLimit);
            params.put("pageSize", pageSize);

            List<Map<String, Object>> withDrawVOList = withdrawService.selectWithDrawPage(params);

            return withDrawVOList;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 功能描述: 获取转账记录
     *
     * @Author: Wilson
     * @Date: 2018/8/29 14:35 
     * @param params
     * @param pageSize
     * @param pageNo
     * @return: java.lang.Object
     **/
    @ApiOperation(value = "获取转账记录", notes = "获取转账记录接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"username\":\"txtianxia019\",\"bdate\":\"2017-01-01\",\"edate\":\"2018-09-01\",\"type\":\"AG\",\"t_type\":\"OUT\"}"),
            @ApiImplicitParam(paramType = "query", name="pageSize",dataType = "int", required = true,value = "每页记录数"),
            @ApiImplicitParam(paramType = "query", name="pageNo",dataType = "int", required = true,value = "当前页")
    })
    @RequestMapping(value="/getTransferPage",method = RequestMethod.POST)
    @ResponseBody
    public Object getTransferPage(@RequestParam Map<String,Object> params, int pageSize, int pageNo){
        try {
            if (params.containsKey("params")){
                params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
            }

            long days = DateUtils.dateDiff(params.get("bdate").toString(),params.get("edate").toString());

            if (days>DAYS_30){
                return "时间错误";
            }

            int starLimit = (pageNo - 1) * pageSize;
            params.put("starLimit", starLimit);
            params.put("pageSize", pageSize);

            List<Map<String, Object>> transferVOList = transferService.selectTransferPage(params);

            return transferVOList;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    @ApiOperation(value = "获取注单记录", notes = "获取注单记录接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"type\":\"LYQP\",\"username\":\"bl1huanghao93\",\"hg_username\":\"bl10526184\",\"startime\":\"2017-01-01\",\"endtime\":\"2018-09-01\"}"),
            @ApiImplicitParam(paramType = "query", name="pageSize",dataType = "integer", required = true,value = "每页记录数"),
            @ApiImplicitParam(paramType = "query", name="pageNo",dataType = "integer", required = true,value = "当前页")
    })
    @RequestMapping(value="/getBetPage",method = RequestMethod.POST)
    @ResponseBody
    public Object getBetPage(@RequestParam Map<String,Object> params, int pageSize, int pageNo){
        try {
            if (params.containsKey("params")){
                params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
            }

            if (!params.containsKey("type")||"".equals(params.get("type"))){
                return "游戏类型参数异常";
            }

            long days = DateUtils.dateDiff(params.get("startime").toString(),params.get("endtime").toString());

            if (days>DAYS_30){
                return "时间错误";
            }

            //应该从redis取值并判断游戏类型是否存在或者大小写是否正确
            params.put("type",params.get("type").toString().toUpperCase());

            int starLimit = (pageNo - 1) * pageSize;
            params.put("starLimit", starLimit);
            params.put("pageSize", pageSize);

            if(params.containsKey("username")){//ag_username
                String ag_username=params.get("username").toString();
                params.put("cagent",ag_username.substring(0,3));
            }

            List<Map<String, String>> betRecordList = betRecordService.selectBetList(params);
            return betRecordList;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 功能描述: 获取用户资金流水
     *
     * @Author: Wilson
     * @Date: 2018/8/29 14:50 
     * @param params
     * @param pageSize
     * @param pageNo
     * @return: java.lang.Object
     **/
    @ApiOperation(value = "获取用户资金流水记录", notes = "获取用户资金流水记录接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"uid\":\"1237\",\"bdate\":\"2017-01-01\",\"edate\":\"2018-09-01\",\"t_type\":\"存款\"}"),
            @ApiImplicitParam(paramType = "query", name="pageSize",dataType = "integer", required = true,value = "每页记录数"),
            @ApiImplicitParam(paramType = "query", name="pageNo",dataType = "integer", required = true,value = "当前页")
    })
    @RequestMapping(value="/getUserTreasurePage",method = RequestMethod.POST)
    @ResponseBody
    public Object getUserTreasurePage(@RequestParam Map<String,Object> params, Integer pageSize, Integer pageNo){
        try {
            if (params.containsKey("params")){
                params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
            }

            long days = DateUtils.dateDiff(params.get("bdate").toString(),params.get("edate").toString());

            if (days>DAYS_30){
                return "时间错误";
            }

            PageInfo pageInfo=new PageInfo(pageNo,pageSize);

            params.put("starLimit", pageInfo.getFrom());
            params.put("pageSize", pageInfo.getPagesize());

            int count = userTreasureService.selectUserTreasurePageCount(params);
            List<Map<String, Object>> userTreasureVOList = userTreasureService.selectUserTreasurePage(params);

            pageInfo.setTotal(count);
            pageInfo.setRows(userTreasureVOList);

            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }
}