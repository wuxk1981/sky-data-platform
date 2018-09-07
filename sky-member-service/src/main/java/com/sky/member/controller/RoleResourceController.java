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

import com.sky.member.service.RoleResourceService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;
import com.sky.member.entity.RoleResourceEntity;


/**
 * 角色资源
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@RestController
@RefreshScope
@Api(description = "角色资源信息")
@RequestMapping("/roleresource")
public class RoleResourceController {
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 列表
     */
    @ApiOperation(value = "角色资源列表", notes = "查询角色资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        JSONObject jo = new JSONObject();
        try {
            //查询列表数据
            Query query = new Query(params);

            List<RoleResourceEntity> roleResourceList = roleResourceService.queryList(query);
            int total = roleResourceService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(roleResourceList, total, query.getLimit(), query.getPage());
            return jo.put("code", 0).put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "具体角色资源列表", notes = "查询具体角色资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "查询参数", required = true, dataType = "Long", paramType = "path")})
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Long id) {
        JSONObject jo = new JSONObject();
        try {
            RoleResourceEntity roleResource = roleResourceService.queryObject(id);
            return jo.put("code", 0).put("roleResource", roleResource);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "存储角色资源列表", notes = "存储具体角色资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleResource", value = "查询参数", required = true, dataType = "RoleResourceEntity", paramType = "body")})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody RoleResourceEntity roleResource) {
        JSONObject jo = new JSONObject();
        try {
            roleResourceService.save(roleResource);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 批量添加角色资源
     */
    @ApiOperation(value = "批量添加角色资源列表", notes = "批量添加具体角色资源接口")
    @RequestMapping(value = "/save/{roleId}/{resourceIds}", method = RequestMethod.GET)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject saveBatch(@PathVariable("roleId") Long roleId, @PathVariable Long[] resourceIds) {
        JSONObject jo = new JSONObject();
        try {
            roleResourceService.saveBatch(roleId, resourceIds);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改角色资源列表", notes = "修改具体角色资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleResource", value = "查询参数", required = true, dataType = "RoleResourceEntity", paramType = "body")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody RoleResourceEntity roleResource) {
        JSONObject jo = new JSONObject();
        try {
            roleResourceService.update(roleResource);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除角色资源列表", notes = "删除具体角色资源接口")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Long[] ids) {
        JSONObject jo = new JSONObject();
        try {
            roleResourceService.deleteBatch(ids);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
