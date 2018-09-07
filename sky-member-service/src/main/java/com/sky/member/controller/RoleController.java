package com.sky.member.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sky.member.config.GlobalExceptionHandler;
import com.sky.member.service.ResourceService;
import com.sky.member.service.RoleResourceService;
import com.sky.member.service.RuserRoleService;
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
import com.sky.member.service.RoleService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;


/**
 * 角色
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@RestController
@RefreshScope
@Api(description = "用户角色信息")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RuserRoleService ruserRoleService;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private ResourceService resourceService;

    /**
     * 列表
     */
    @ApiOperation(value = "角色列表", notes = "查询角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        JSONObject jo = new JSONObject();
        try {
            //查询列表数据
            Query query = new Query(params);
            List<RoleEntity> roleList = roleService.queryList(query);
            int total = roleService.queryTotal(query);
            PageUtils pageUtil = new PageUtils(roleList, total, query.getLimit(), query.getPage());
            return jo.put("code", "0").put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "具体角色查询", notes = "依据角色的ID进行查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "查询参数", required = true, dataType = "Long", paramType = "path")})
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Long id) {
        JSONObject jo = new JSONObject();
        try {
            RoleEntity role = roleService.queryObject(id);
            Set<ResourceEntity> resourceSet = resourceService.queryResourcesByRoleId(role.getId());
            role.setResourceSet(resourceSet);
            return jo.put("code", "0").put("role", role);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存角色", notes = "保存角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "查询参数", required = true, dataType = "RoleEntity", paramType = "body")})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody RoleEntity role) {
        JSONObject jo = new JSONObject();
        try {
            roleService.save(role);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改角色", notes = "修改具体角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "查询参数", required = true, dataType = "RoleEntity", paramType = "body")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody RoleEntity role) {
        JSONObject jo = new JSONObject();
        try {
            roleService.update(role);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除角色", notes = "删除指定的角色ID数组")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Long[] ids) {
        JSONObject jo = new JSONObject();
        try {
            roleService.deleteBatch(ids);
            //将两张关联的表中的记录清空
            ruserRoleService.deleteBatchByRolesIds(ids);
            roleResourceService.deleteBatchByRolesIds(ids);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
