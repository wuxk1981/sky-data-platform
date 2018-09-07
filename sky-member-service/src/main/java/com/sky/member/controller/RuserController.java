package com.sky.member.controller;

import com.sky.member.config.GlobalExceptionHandler;
import com.sky.member.service.RoleService;
import com.sky.member.service.RuserRoleService;
import com.sky.member.service.RuserService;
import com.sky.member.utils.PageUtils;
import com.sky.member.utils.Query;
import com.sky.member.utils.RedisUtil;
import com.sky.member.utils.token.TokenUtils;
import com.xiaoleilu.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.sky.member.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户
 *
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@RestController
@RefreshScope
@Api(description = "管理员信息")
@RequestMapping("/user")
public class RuserController {
    @Autowired
    private GlobalExceptionHandler exceptionHandle;
    @Autowired
    private RuserService ruserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RuserRoleService ruserRoleService;
//    @Autowired
//    private RedisTemplate<Object, Object> template;

    @ApiOperation(value = "用户登录", notes = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "path")})
    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.POST)
    public JSONObject login(@PathVariable("username") String username, @PathVariable("password") String password, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {

            RuserEntity user = ruserService.queryRuserEntity(username, password);

            String token = null;
            //Token tk=null;
            //JwtAccessTokenConverter converter=null;
            if (null != user) {
                Set<RoleEntity> roles = roleService.queryRolesList(user.getId());
                if (!CollectionUtils.isEmpty(roles)) {
                    user.setRoleSet(roles);
                    request.getSession().setAttribute("user", user);
                }
                token = TokenUtils.getToken(user.getId().toString(), TokenUtils.getDate(new Date()));
                // template.opsForValue().set(user.getId().toString(), user);
                //RedisUtil redisUtil=new RedisUtil();
                //redisUtil.set(user.getId().toString(),user);
//			TokenFactory tokenFactory=new TokenFactory(new TokenProperties());
//			tk=tokenFactory.createAccessToken(user);
            } else {
                return jo.put("code", "0").put("message", "查无此人");
            }
            return jo.put("code", "0").put("user", user).put("token", token);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;

    }


    @ApiOperation(value = "用户名查询用户", notes = "用户名查询用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "查询参数", required = true, dataType = "String", paramType = "path")})
    @RequestMapping(value = "/findByUsername/{username}", method = RequestMethod.GET)
    public RuserEntity findByUsername(@PathVariable String username) {
        try {
            RuserEntity user = ruserService.queryByUsername(username);
            Set<RoleEntity> roles = roleService.queryRolesList(user.getId());
            user.setRoleSet(roles);
            return user;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 列表
     */
    @ApiOperation(value = "用户列表", notes = "查询用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map", paramType = "body")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JSONObject list(@RequestBody Map<String, Object> params) {
        JSONObject jo = new JSONObject();
        try {
            //查询列表数据
            Query query = new Query(params);

            List<RuserEntity> ruserList = ruserService.queryList(query);
            int total = ruserService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(ruserList, total, query.getLimit(), query.getPage());

            return jo.put("code", "0").put("page", pageUtil);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "查询用户", notes = "根据用户ID进行查找")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "查询参数", required = true, dataType = "Long", paramType = "path")})
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public JSONObject info(@PathVariable("id") Long id) {
        JSONObject jo = new JSONObject();
        try {
            RuserEntity ruser = ruserService.queryObject(id);
            return jo.put("code", "0").put("user", ruser);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "存储用户", notes = "添加新的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ruser", value = "添加参数", required = true, dataType = "RuserEntity", paramType = "body")})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject save(@RequestBody RuserEntity ruser) {
        JSONObject jo = new JSONObject();
        try {
            ruserService.save(ruser);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改用户", notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ruser", value = "修改参数", required = true, dataType = "RuserEntity", paramType = "body")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject update(@RequestBody RuserEntity ruser) {
        JSONObject jo = new JSONObject();
        try {
            ruserService.update(ruser);
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除用户", notes = "删除用户信息")
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject delete(@PathVariable Long[] ids, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {

            ruserService.deleteBatch(ids);
            ruserRoleService.deleteBatchByUserIds(ids);
            //删除用户表，用户角色关联表
            RedisUtil redisUtil = new RedisUtil();

            for (int i = 0; i < ids.length; i++) {
//                template.delete(ids[i].toString());
                RuserEntity ruser = ruserService.queryObject(ids[i]);
                //request.getSession().setAttribute("user", user);
                request.getSession().removeAttribute("user");
            }
            return jo.put("code", "0");
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }

}
