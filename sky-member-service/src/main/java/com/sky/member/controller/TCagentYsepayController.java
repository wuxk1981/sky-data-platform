package com.sky.member.controller;

import com.sky.member.config.GlobalExceptionHandler;
import com.sky.member.entity.TCagentYsepayEntity;
import com.sky.member.service.TCagentYsepayService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;
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

import java.util.List;
import java.util.Map;


/**
 * 在线支付配置
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
@RestController
@RefreshScope
@Api(description = "在线支付信息")
@RequestMapping("/cagentYsepay")
public class TCagentYsepayController {
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private TCagentYsepayService tCagentYsepayService;

    /**
     * 列表
     */

    @ApiOperation(value = "在线支付配置列表", notes = "查询在线支付配置接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        //查询列表数据
        JSONObject jo = new JSONObject();
        try {
            Query query = new Query(params);

            List<TCagentYsepayEntity> tCagentYsepayList = tCagentYsepayService.queryList(query);
            int total = tCagentYsepayService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(tCagentYsepayList, total, query.getLimit(), query.getPage());
            return jo.put("code", 0).put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "具体在线支付配置列表", notes = "查询具体在线支付配置接口")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Integer id) {
        JSONObject jo = new JSONObject();
        try {
            TCagentYsepayEntity tCagentYsepay = tCagentYsepayService.queryObject(id);

            return jo.put("code", 0).put("tCagentYsepay", tCagentYsepay);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存在线支付配置列表", notes = "保存具体在线支付配置接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody TCagentYsepayEntity tCagentYsepay) {
        JSONObject jo = new JSONObject();
        try {
            tCagentYsepayService.save(tCagentYsepay);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改在线支付配置列表", notes = "修改具体在线支付配置接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody TCagentYsepayEntity tCagentYsepay) {
        JSONObject jo = new JSONObject();
        try {
            tCagentYsepayService.update(tCagentYsepay);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除在线支付配置列表", notes = "批量删除在线支付配置接口")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Integer[] ids) {
        JSONObject jo = new JSONObject();
        try {
        tCagentYsepayService.deleteBatch(ids);
            return jo.put("code", 0);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
