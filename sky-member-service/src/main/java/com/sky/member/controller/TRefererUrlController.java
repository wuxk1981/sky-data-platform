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

import com.sky.member.entity.TRefererUrlEntity;
import com.sky.member.service.TRefererUrlService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;



/**
 * 域名白名单
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 09:22:01
 */

@RestController
@RefreshScope
@Api(description = "域名白名单")
@RequestMapping("/trefererurl")
public class TRefererUrlController {
    @Autowired
    private GlobalExceptionHandler exceptionHandle;

    @Autowired
    private TRefererUrlService tRefererUrlService;

    @ApiOperation(value = "域名白名单列表", notes = "查询域名白名单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        //查询列表数据
        JSONObject jo = new JSONObject();
        try {
            Query query = new Query(params);

            List<TRefererUrlEntity> tRefererUrlList = tRefererUrlService.queryList(query);
            int total = tRefererUrlService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(tRefererUrlList, total, query.getLimit(), query.getPage());

            return jo.put("code", 0).put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "具体域名白名单列表", notes = "查询具体域名白名单接口")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Integer id) {
        JSONObject jo = new JSONObject();
        try {
             TRefererUrlEntity tRefererUrl = tRefererUrlService.queryObject(id);
            return jo.put("code", 0).put("tRefererUrl", tRefererUrl);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "保存域名白名单列表", notes = "保存具体域名白名单接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody TRefererUrlEntity tRefererUrl) {
        JSONObject jo = new JSONObject();
        try {
            tRefererUrlService.save(tRefererUrl);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "修改域名白名单列表", notes = "修改域名白名单接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody TRefererUrlEntity tRefererUrl) {
        JSONObject jo = new JSONObject();
        try {
            tRefererUrlService.update(tRefererUrl);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "批量删除域名白名单", notes = "批量删除域名白名单接口")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Integer[] ids) {
        JSONObject jo = new JSONObject();
        try {
            tRefererUrlService.deleteBatch(ids);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


}
