package com.sky.member.controller;

import java.util.List;
import java.util.Map;

import com.sky.member.config.GlobalExceptionHandler;
import com.xiaoleilu.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.sky.member.entity.TPlatformConfigEntity;
import com.sky.member.service.TPlatformConfigService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;


/**
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */

@RestController
@RefreshScope
@Api(description = "游戏平台配置信息")
@RequestMapping("/tplatformconfig")
public class TPlatformConfigController {
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private TPlatformConfigService tPlatformConfigService;

    /**
     * 列表
     */
    @ApiOperation(value = "游戏平台配置列表", notes = "查询游戏平台配置接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        //查询列表数据
        JSONObject jo = new JSONObject();
        try {
            Query query = new Query(params);

            List<TPlatformConfigEntity> tPlatformConfigList = tPlatformConfigService.queryList(query);
            int total = tPlatformConfigService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(tPlatformConfigList, total, query.getLimit(), query.getPage());

            return jo.put("code", 0).put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "具体游戏平台配置列表", notes = "查询具体游戏平台配置接口")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Integer id) {
        JSONObject jo = new JSONObject();
        try {
            TPlatformConfigEntity tPlatformConfig = tPlatformConfigService.queryObject(id);

            return jo.put("code", 0).put("tPlatformConfig", tPlatformConfig);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 保存
     */

    @ApiOperation(value = "保存游戏平台配置列表", notes = "保存具体游戏平台配置接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody TPlatformConfigEntity tPlatformConfig) {
        JSONObject jo = new JSONObject();
        try {
            tPlatformConfigService.save(tPlatformConfig);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改游戏平台配置列表", notes = "修改具体游戏平台配置接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody TPlatformConfigEntity tPlatformConfig) {
        JSONObject jo = new JSONObject();
        try {
            tPlatformConfigService.update(tPlatformConfig);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除游戏平台配置列表", notes = "批量删除游戏平台配置接口")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Integer[] ids) {
        JSONObject jo = new JSONObject();
        try {
            tPlatformConfigService.deleteBatch(ids);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
