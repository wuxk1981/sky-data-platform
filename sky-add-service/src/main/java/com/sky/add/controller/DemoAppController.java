/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.add.controller 
 *
 *    Filename:    DemoAppController.java 
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
 *    Create at:   2018年08月26日 17:45 
 *
 *    Revision: 
 *
 *    2018/8/26 17:45 
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

/**
 *  * @ClassName DemoAppController
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 17:45
 *  * @Version 1.0.0
 *  
 **/
@RestController
@Api(description = "DemoAppController")
public class DemoAppController {

    @ApiOperation(value = "测试post请求", notes = "注意事项")
    @ApiImplicitParam(dataType = "User", name = "user", value = "用户信息", required = true)
    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public String testPost(@RequestBody User user) {
        return "success";
    }


    @ApiOperation(value = "测试get请求", notes = "注意事项")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "String", paramType = "path")
    @RequestMapping(value = "/testGet/{id}", method = RequestMethod.GET)
    public String testGet(@PathVariable String id) {
        return id;
    }

    @ApiOperation(value = "测试组合注解", notes = "注意事项")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "User", name = "user", value = "用户信息", required = true, paramType = "body"),
            @ApiImplicitParam(dataType = "string", name = "id", value = "用户id", required = true, paramType = "path")
    })
    @RequestMapping(value = "/joinAnnotation/{id}", method = RequestMethod.POST)
    public User joinAnnotation(@PathVariable String id, @RequestBody User user) {
        return user;
    }

    @ApiIgnore
    public String testIgnore() {
        return "success";
    }
}
