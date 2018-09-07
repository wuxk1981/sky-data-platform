package com.sky.member.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;


import com.sky.member.entity.ResourceEntity;
import com.sky.member.entity.RoleEntity;
import com.sky.member.config.GlobalExceptionHandler;
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

import com.sky.member.entity.TInternalMessageEntity;
import com.sky.member.service.TInternalMessageService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 消息提示表
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-06 10:43:55
 */

@RestController
@RefreshScope
@Api(description = "消息信息")
@RequestMapping("/message")
public class TInternalMessageController {

    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private TInternalMessageService tInternalMessageService;

    @ApiOperation(value = "删除站内信 ", notes = "删除站内信接口")
    @RequestMapping(value = "/deleteMessage/{id}", method = RequestMethod.GET)
    public JSONObject deleteMessage(@PathVariable String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String uid = session.getAttribute("uid").toString();
        JSONObject jo = new JSONObject();
        try {

            String[] a = id.split(",");
            for (int i = 0; i < a.length; i++) {
                tInternalMessageService.deleteMessage(uid, a[i]);
            }
            jo.put("status", "success");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "读取站内信 ", notes = "读取站内信接口")
    @RequestMapping(value = "/getMessageInfo/{id}", method = RequestMethod.GET)
    public JSONObject getMessageInfo(@PathVariable String id, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {

            HttpSession session = request.getSession();
            String uid = session.getAttribute("uid").toString();
            if (id == null || "".endsWith(id)) {
                return null;
            }
            List<Map<String, Object>> lm = tInternalMessageService.selectMessageInfo(uid, id);
            tInternalMessageService.updateMessageInfo(uid, id);
            if (lm.size() > 0) {
                return jo.put("mess", lm.get(0));
            } else {
                return null;
            }
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "站内信息列表", notes = "查询站内信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        JSONObject jo = new JSONObject();
        try {
            //查询列表数据
            Query query = new Query(params);

            List<TInternalMessageEntity> tInternalMessageList = tInternalMessageService.queryList(query);
            int total = tInternalMessageService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(tInternalMessageList, total, query.getLimit(), query.getPage());
            return jo.put("code", "0").put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "具体站内信息查询", notes = "依据站内信息的ID进行查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "查询参数", required = true, dataType = "Long", paramType = "path")})
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Long id) {
        JSONObject jo = new JSONObject();
        try {
            TInternalMessageEntity tInternalMessage = tInternalMessageService.queryObject(id);
            return jo.put("code", "0").put("tInternalMessage", tInternalMessage);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    @ApiOperation(value = "保存站内信息", notes = "保存站内信息信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "查询参数", required = true, dataType = "RoleEntity", paramType = "body")})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody TInternalMessageEntity tInternalMessage) {
        JSONObject jo = new JSONObject();
        try {
            tInternalMessageService.save(tInternalMessage);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "修改站内信息", notes = "修改具体站内信息信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "查询参数", required = true, dataType = "RoleEntity", paramType = "body")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody TInternalMessageEntity tInternalMessage) {
        JSONObject jo = new JSONObject();
        try {
            tInternalMessageService.update(tInternalMessage);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    @ApiOperation(value = "删除站内信息", notes = "删除指定的站内信息ID数组")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Long[] ids) {
        JSONObject jo = new JSONObject();
        try {
            tInternalMessageService.deleteBatch(ids);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


}
