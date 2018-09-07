package com.sky.add.controller;

import com.alibaba.fastjson.JSON;
import com.sky.add.entity.City;
import com.sky.add.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Author: wangxc
 * @GitHub: https://github.com/vector4wang
 * @CSDN: http://blog.csdn.net/qqhjqs?viewmode=contents
 * @BLOG: http://vector4wang.tk
 * @wxid: BMHJQS
 */

@Api(description = "ApiController相关的API")
@RestController
public class ApiController {

    private static final Logger addLogger = LoggerFactory.getLogger("addLogger");

    @Resource
    private CityService cityService;

    @ApiOperation(value = "根据id查询城市信息", notes = "查询数据库中某个的城市信息")
    @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
    public City getCityById(@PathVariable("id") int id) {
        City city = cityService.getCityById(id);
        addLogger.info("参数: id: "+ id+ ", 返回结果: "+ JSON.toJSONString(city));
        return city;
    }

    @ApiOperation(value = "保存城市信息", notes = "保存城市信息")
    @RequestMapping(value = "/transaction/save", method = RequestMethod.POST)
    public String saveTransaction() {
        cityService.saveTransaction();
        return "saveTransaction";
    }

}
