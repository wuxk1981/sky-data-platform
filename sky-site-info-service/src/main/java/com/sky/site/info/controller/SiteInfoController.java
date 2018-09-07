package com.sky.site.info.controller;

import cn.hutool.core.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import com.sky.site.info.entity.ContactConfigVO;
import com.sky.site.info.service.IContactConfigService;
import com.sky.site.info.service.IInternalMessageService;
import com.sky.site.info.service.IWebComConfigService;
import com.sky.site.info.utils.PageInfo;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 平台(网站信息)服务
 * 以下方法均应该从redis中取user值并判断登录状态
 * @ClassName:HistoryController
 * @Auther: Wilson
 * @Date: 2018/8/23 09:58
 * @Version:1.0.0
 */
@Api(description = "平台(网站信息)服务")
@RestController
@RequestMapping(value = "/site")
public class SiteInfoController {

    private static final Logger logger = LoggerFactory.getLogger(SiteInfoController.class);

    //联系方式
    @Autowired
    private IContactConfigService contactConfigService;

    //网站配置
    @Autowired
    private IWebComConfigService webComConfigService;

    //站内信
    @Autowired
    private IInternalMessageService internalMessageService;

    /*
     *功能描述:获取联系方式
     *
     *@Author: Wilson
     *@Date: 2018年08月30日 15:10:24
     * @param params
     *@return: java.lang.Object
     **/
    @ApiOperation(value = "获取联系方式", notes = "获取联系方式接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"cagent\":\"QUC\",\"website\":\"www.baidu.com\"}"),
    })
    @ResponseBody
    @RequestMapping(value = "/getContactInfo",method = RequestMethod.POST)
    public Object getContactInfo(@RequestParam Map<String,Object> params){

        if (params.containsKey("params")){
            params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
        }

        ContactConfigVO contactConfigVOs = contactConfigService.selectContactByCagent(params);

        return contactConfigVOs;
    }

    /*
     *功能描述:获取轮播图且需要指定数量
     *
     *@Author: Wilson
     *@Date: 2018年08月30日 15:10:14
     * @param params
     *@return: java.lang.Object
     **/
    @ApiOperation(value = "获取轮播图且需要指定数量", notes = "获取轮播图接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"cagent\":\"TXW\",\"count\":5}"),
    })
    @ResponseBody
    @RequestMapping(value = "/getCarouselFigure",method = RequestMethod.POST)
    public Object getCarouselFigure(@RequestParam Map<String,Object> params){

        if (params.containsKey("params")){
            params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
        }
//        if(!params.containsKey("count")){
//            return "请指定需要获取图片的数量";
//        }
        params.put("type", 6);//6表示是轮播图

        List<Map<String, Object>> carouselFigureList = webComConfigService.selectCarouselFigureByCagent(params);

        return carouselFigureList;
    }

    /* 
    *功能描述: 分页获取站内信列表
    * 
    *@Author: Wilson
    *@Date: 2018年08月30日 15:10:02
    * @param params
    * @param pageSize
    * @param pageNo 
    *@return: java.lang.Object 
    **/
    @ApiOperation(value = "分页获取站内信列表", notes = "获取站内信列表接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"uid\":\"1237\",\"bdate\":\"2017-01-01\",\"edate\":\"2018-09-01\",\"status\":0}"),
            @ApiImplicitParam(paramType = "query", name="pageSize",dataType = "integer", required = true,value = "每页记录数"),
            @ApiImplicitParam(paramType = "query", name="pageNo",dataType = "integer", required = true,value = "当前页")
    })
    @ResponseBody
    @RequestMapping(value = "/getMessagePage",method = RequestMethod.POST)
    public Object getMessagePage(@RequestParam Map<String,Object> params,
                                 @RequestParam(name = "pageSize") Integer pageSize,
                                 @RequestParam(name = "pageNo") Integer pageNo){
        if (params.containsKey("params")){
            params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
        }
        PageInfo pageInfo=new PageInfo(pageNo,pageSize);

        params.put("starLimit", pageInfo.getFrom());
        params.put("pageSize", pageInfo.getPagesize());

        List<Map<String, Object>> internalMessageList = internalMessageService.selectMessagePageByStatus(params);

        pageInfo.setRows(internalMessageList);

        return pageInfo;
    }

    /*
     *功能描述:获取站内信数量
     *
     *@Author: Wilson
     *@Date: 2018年08月30日 15:09:46
     * @param params
     *@return: java.lang.Object
     **/
    @ApiOperation(value = "获取站内信数量", notes = "获取站内信数量接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"uid\":\"1237\",\"bdate\":\"2017-01-01\",\"edate\":\"2018-09-01\"}")
    })
    @ResponseBody
    @RequestMapping(value = "/getMessageCount",method = RequestMethod.POST)
    public Object getMessageCount(@RequestParam Map<String,Object> params){
        if (params.containsKey("params")){
            params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
        }
        List<Map<String, Object>> countList = internalMessageService.selectMessageReadCount(params);
        return countList;
    }

    
    /*
     *功能描述:获取优惠活动信息
     *
     *@Author: Wilson
     *@Date: 2018年08月30日 15:09:34
     * @param params
     *@return: java.lang.Object
     **/
    @ApiOperation(value = "获取优惠活动信息", notes = "获取优惠活动信息接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"cagent\":\"TXW\"}")
    })
    @ResponseBody
    @RequestMapping(value = "/getDiscountInfo",method = RequestMethod.POST)
    public Object getDiscountInfo(@RequestParam Map<String,Object> params){
        if (params.containsKey("params")){
            params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
        }
        params.put("type", 4);//4表示是优惠活动图片

        List<Map<String, Object>> discountInfoList = webComConfigService.selectDiscountInfoByCagent(params);
        for (Map<String, Object> discountInfoMap : discountInfoList) {
            // 修改特殊符号被转义的问题
            String rmk=StringEscapeUtils.escapeHtml(discountInfoMap.get("rmk").toString());
            discountInfoMap.put("rmk",rmk);
        }
        return discountInfoList;
    }

    @ApiOperation(value = "获取手机端网站图片", notes = "获取手机端网站图片接口")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "params",required = true, dataType = "string",
                    value = "参数列表,如:{\"cagent\":\"GYC\",\"type\":11}")
    })
    @ResponseBody
    @RequestMapping(value = "/getMobileFigure",method = RequestMethod.POST)
    public Object getMobileFigure(@RequestParam Map<String,Object> params){
        if (params.containsKey("params")){
            params=(Map<String, Object>) JSONObject.parseObject(params.get("params").toString());
        }

        if(!params.containsKey("type")){
            return "请指定Type";
        }

        List<Map<String, Object>> mobileWebComConfigList = webComConfigService.selectMobileWebComConfigByCagent(params);
        return mobileWebComConfigList;
    }
}
