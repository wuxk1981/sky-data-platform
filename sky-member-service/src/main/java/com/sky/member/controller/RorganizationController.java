package com.sky.member.controller;

import java.util.List;
import java.util.Map;
import com.sky.member.entity.*;
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

import com.sky.member.service.RorganizationService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;


/**
 * 组织机构
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@RestController
@RefreshScope
@Api(description = "组织机构信息")
@RequestMapping("/organization")
public class RorganizationController {
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private RorganizationService rorganizationService;

    /**
     * 列表
     */
    @ApiOperation(value = "组织机构资源列表", notes = "查询组织机构资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        JSONObject jo = new JSONObject();
        try {
            //查询列表数据
            Query query = new Query(params);

            List<RorganizationEntity> rorganizationList = rorganizationService.queryList(query);
            int total = rorganizationService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(rorganizationList, total, query.getLimit(), query.getPage());
            return jo.put("code", 0).put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "具体组织机构资源列表", notes = "查询具体组织机构资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "查询参数", required = true, dataType = "Long", paramType = "path")})
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Long id) {
        JSONObject jo = new JSONObject();
        try {
            RorganizationEntity rorganization = rorganizationService.queryObject(id);
            return jo.put("code", 0).put("rorganization", rorganization);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "添加组织机构资源列表", notes = "添加具体组织机构资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rorganization", value = "查询参数", required = true, dataType = "RorganizationEntity", paramType = "body")})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody RorganizationEntity rorganization) {
        JSONObject jo = new JSONObject();
        try {
            rorganizationService.save(rorganization);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改组织机构资源列表", notes = "修改具体组织机构资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rorganization", value = "查询参数", required = true, dataType = "RorganizationEntity", paramType = "body")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody RorganizationEntity rorganization) {
        JSONObject jo = new JSONObject();
        try {
            rorganizationService.update(rorganization);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除组织机构资源列表", notes = "删除具体组织机构资源接口")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Long[] ids) {
        JSONObject jo = new JSONObject();
        try {
            rorganizationService.deleteBatch(ids);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
