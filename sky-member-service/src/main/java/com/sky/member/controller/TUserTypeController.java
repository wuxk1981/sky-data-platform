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

import com.sky.member.entity.TUserTypeEntity;
import com.sky.member.service.TUserTypeService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;



/**
 * 会员分层
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
@RestController
@RefreshScope
@Api(description = "会员分层")
@RequestMapping("/tusertype")
public class TUserTypeController {

	@Autowired
	private GlobalExceptionHandler exceptionHandle;
	@Autowired
	private TUserTypeService tUserTypeService;
	
	/**
	 * 列表
	 */
	@ApiOperation(value = "会员分层列表", notes = "查询会员分层接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JSONObject list(@RequestBody Map<String, Object> params) {
		//查询列表数据
		JSONObject jo = new JSONObject();
		try {
			Query query = new Query(params);

			List<TUserTypeEntity> tUserTypeList = tUserTypeService.queryList(query);
			int total = tUserTypeService.queryTotal(query);

			PageUtils pageUtil = new PageUtils(tUserTypeList, total, query.getLimit(), query.getPage());

			return jo.put("code", 0).put("page", pageUtil);
		} catch (Exception e) {
			jo = exceptionHandle.defaultErrorHandler(e);
		}
		return jo;
	}

	
	
	/**
	 * 信息
	 */
	@ApiOperation(value = "具体会员分层列表", notes = "查询具体会员分层接口")
	@RequestMapping(value = "/info/{uid}", method = RequestMethod.GET)
	public JSONObject info(@PathVariable("uid") Integer uid) {
		JSONObject jo = new JSONObject();
		try {
			TUserTypeEntity tUserType = tUserTypeService.queryObject(uid);
			return jo.put("code", 0).put("tUserType", tUserType);
		} catch (Exception e) {
			jo = exceptionHandle.defaultErrorHandler(e);
		}
		return jo;
	}
	/**
	 * 保存
	 */
	@ApiOperation(value = "保存会员分层列表", notes = "保存具体会员分层接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JSONObject save(@RequestBody TUserTypeEntity tUserType) {
		JSONObject jo = new JSONObject();
		try {
			tUserTypeService.save(tUserType);
			return jo.put("code", 0);
		} catch (Exception e) {
			jo = exceptionHandle.defaultErrorHandler(e);
		}
		return jo;
	}


	/**
	 * 修改
	 */
	@ApiOperation(value = "修改会员分层列表", notes = "修改会员分层接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JSONObject update(@RequestBody TUserTypeEntity tUserType) {
		JSONObject jo = new JSONObject();
		try {
			tUserTypeService.update(tUserType);
			return jo.put("code", 0);
		} catch (Exception e) {
			jo = exceptionHandle.defaultErrorHandler(e);
		}
		return jo;
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "批量删除会员分层", notes = "批量删除会员分层接口")
	@RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JSONObject delete(@PathVariable Integer[] ids) {
		JSONObject jo = new JSONObject();
		try {
			tUserTypeService.deleteBatch(ids);
			return jo.put("code", 0);
		} catch (Exception e) {
			jo = exceptionHandle.defaultErrorHandler(e);
		}
		return jo;
	}
}
