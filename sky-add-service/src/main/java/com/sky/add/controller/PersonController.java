/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.add.controller 
 *
 *    Filename:    PersonController.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Tammy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年08月26日 17:52 
 *
 *    Revision: 
 *
 *    2018/8/26 17:52 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.add.controller;

import com.sky.add.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  * @ClassName PersonController
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 17:52
 *  * @Version 1.0.0
 *  
 **/
@Api(description = "用户信息管理")
@RestController
@RequestMapping(value = "/demo")
public class PersonController {
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.Long", name = "id", value = "id", required = true, paramType = "path"),
            @ApiImplicitParam(dataType = "User", name = "user", value = "用户信息", required = true)
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public User insert(@PathVariable Long id, @RequestBody User user) {

        System.out.println("id:" + id + ", user:" + user);
        user.setId(Integer.valueOf(id + ""));


        return user;
    }

    @ApiOperation(value = "获取指定id用户详细信息", notes = "根据user的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "String", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {

        User user = new User();
        user.setId(Integer.valueOf(id + ""));
        user.setPassword("abc");
        user.setUsername("12345");
        user.setCtm(new Date());
        return user;
    }

    @ApiOperation(value = "获取所有用户详细信息", notes = "获取用户列表信息")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUserList() {

        List<User> list = new ArrayList<>();
        User user = new User();
        user.setId(15);
        user.setPassword("ricky");
        user.setUsername("root");
        user.setCtm(new Date());
        list.add(user);

        return list;
    }

    @ApiIgnore
    @ApiOperation(value = "删除指定id用户", notes = "根据id来删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "java.lang.Long", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        System.out.println("delete user:" + id);
        return "OK";
    }
}
