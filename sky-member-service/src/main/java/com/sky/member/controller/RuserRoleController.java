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
import com.sky.member.entity.*;
import com.sky.member.service.RuserRoleService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;


/**
 * 用户角色
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@RestController
@RefreshScope
@Api(description = "管理员角色信息")
@RequestMapping("/userrole")
public class RuserRoleController {
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private RuserRoleService ruserRoleService;

    /**
     * 列表
     */
    @ApiOperation(value = "用户角色列表", notes = "查询用户角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        JSONObject jo = new JSONObject();
        try {
            //查询列表数据
            Query query = new Query(params);
            List<RuserRoleEntity> ruserRoleList = ruserRoleService.queryList(query);
            int total = ruserRoleService.queryTotal(query);
            PageUtils pageUtil = new PageUtils(ruserRoleList, total, query.getLimit(), query.getPage());
            return jo.put("code", 0).put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "具体用户角色列表", notes = "查询具体用户角色接口")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Long id) {
        JSONObject jo = new JSONObject();
        try {
            RuserRoleEntity ruserRole = ruserRoleService.queryObject(id);
            return jo.put("code", 0).put("ruserRole", ruserRole);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "添加用户角色列表", notes = "添加具体用户角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ruserRole", value = "查询参数", required = true, dataType = "RuserRoleEntity", paramType = "body")})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody RuserRoleEntity ruserRole) {
        JSONObject jo = new JSONObject();
        try {
            ruserRoleService.save(ruserRole);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 批量添加用户角色
     */
    @ApiOperation(value = "批量添加用户角色列表", notes = "批量添加具体用户角色接口")
    @RequestMapping(value = "/saveBatch/{userId}/{roleIds}", method = RequestMethod.GET)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject saveBatch(@PathVariable("userId") Long userId, @PathVariable Long[] roleIds) {
        JSONObject jo = new JSONObject();
        try {
            ruserRoleService.saveBatch(userId, roleIds);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改用户角色列表", notes = "修改具体用户角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ruserRole", value = "查询参数", required = true, dataType = "RuserRoleEntity", paramType = "body")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody RuserRoleEntity ruserRole) {
        JSONObject jo = new JSONObject();
        try {
            ruserRoleService.update(ruserRole);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除用户角色列表", notes = "删除具体用户角色接口")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Long[] ids) {
        JSONObject jo = new JSONObject();
        try {
            ruserRoleService.deleteBatch(ids);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
