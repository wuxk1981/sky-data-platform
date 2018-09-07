package com.sky.member.controller;

import com.sky.member.config.GlobalExceptionHandler;
import com.sky.member.service.RoleResourceService;
import com.xiaoleilu.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import com.sky.member.entity.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.sky.member.service.ResourceService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;


/**
 * 资源
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@RestController
@RefreshScope
@Api(description = "用户资源信息")
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 列表
     */
    @ApiOperation(value = "资源列表", notes = "查询资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        JSONObject jo = new JSONObject();
        try {
            //查询列表数据
            Query query = new Query(params);

            List<ResourceEntity> resourceList = resourceService.queryList(query);
            int total = resourceService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(resourceList, total, query.getLimit(), query.getPage());


            return jo.put("code", "0").put("page", pageUtil);

        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "用户名ID及所给的Url查询用户权限", notes = "用户名ID及所给的Url(/请转换为@)查询用户权限接口")
    @RequestMapping(value = "/isAuth/{userId}/{url:.*}", method = RequestMethod.POST)
    public JSONObject isAuth(@PathVariable("userId") Long userId, @PathVariable("url") String url) {
        JSONObject jo = new JSONObject();
        Boolean isAuth = false;
        try {
             url=url.replaceAll("@","/");
            Set<ResourceEntity> resourceSet = resourceService.queryResourceByUserId(userId);
            if (!CollectionUtils.isEmpty(resourceSet)) {
                //对于求得的Set进行过滤 ，满足URL 为给定的全部放入resourceFilter
                ResourceEntity resourceEntity=null;
                for (ResourceEntity obj: resourceSet) {
                    if(obj.getUrl().equalsIgnoreCase(url)){
                        resourceEntity=obj;
                        break;
                    }
                }

               // List<ResourceEntity> resourceFilter = resourceSet.stream().filter(ResourceEntity -> ResourceEntity.getUrl().equalsIgnoreCase(url)).collect(Collectors.toList());
                if (null!=resourceEntity) {
                    isAuth = true;
                    //给出当前目录下所有的权限或者资源
                    resourceSet = resourceService.queryResourceByPreResId(resourceEntity.getId());
                    if(CollectionUtils.isEmpty(resourceSet))
                    {
                        return   jo.put("code",0).put("isAuth",isAuth);
                    }else{
                        return   jo.put("code",0).put("isAuth",isAuth).put("permit",resourceSet);
                    }
                }else{
                    return   jo.put("code",0).put("isAuth",isAuth);
                }
            }
            return   jo.put("code",0).put("isAuth",isAuth);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 信息
     */
    @ApiOperation(value = "具体资源信息", notes = "查询具体资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "查询参数", required = true, dataType = "Long", paramType = "path")})
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Long id) {
        JSONObject jo = new JSONObject();
        try {
            ResourceEntity resource = resourceService.queryObject(id);

            return jo.put("code", "0").put("resource", resource);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;

    }

    /**
     * 保存
     */
    @ApiOperation(value = "添加资源信息", notes = "添加具体资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resource", value = "查询参数", required = true, dataType = "ResourceEntity", paramType = "body")})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody ResourceEntity resource) {
        JSONObject jo = new JSONObject();
        try {
            resourceService.save(resource);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改资源信息", notes = "修改具体资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resource", value = "查询参数", required = true, dataType = "ResourceEntity", paramType = "body")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody ResourceEntity resource) {
        JSONObject jo = new JSONObject();
        try {
            resourceService.update(resource);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除资源信息", notes = "删除具体资源接口")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Long[] ids) {
        JSONObject jo = new JSONObject();
        try {
            resourceService.deleteBatch(ids);
            roleResourceService.deleteBatchByResourceIds(ids);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
