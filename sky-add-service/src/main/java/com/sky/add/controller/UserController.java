/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.quick.druid.controller 
 *
 *    Filename:    UserController.java 
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
 *    Create at:   2018年08月26日 13:39 
 *
 *    Revision: 
 *
 *    2018/8/26 13:39 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.add.controller;

import com.sky.add.config.JsonResult;
import com.sky.add.entity.User;
import com.sky.add.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 *  * @ClassName UserController
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 13:39
 *  * @Version 1.0.0
 *  
 **/
@Api(description = "用户管理UserController相关的API")
@RestController
public class UserController {
    // 创建线程安全的Map
    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

    @Autowired
    private UserService userService;
//    @Autowired
//    private RedisService redisService;

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserById(@PathVariable(value = "id") Integer id) {
        JsonResult r = new JsonResult();
        try {
            User user = users.get(id);
            r.setResult(user);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserList() {
        JsonResult r = new JsonResult();
        try {
            List<User> userList = new ArrayList<User>(users.values());
            r.setResult(userList);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> add(@RequestBody User user) {
        JsonResult r = new JsonResult();
        try {
            users.put(user.getId(), user);
            r.setResult(user.getId());
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult> delete(@PathVariable(value = "id") Integer id) {
        JsonResult r = new JsonResult();
        try {
            users.remove(id);
            r.setResult(id);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 根据id修改用户信息
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "更新信息", notes = "根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JsonResult> update(@PathVariable("id") Integer id, @RequestBody User user) {
        JsonResult r = new JsonResult();
        try {
            User u = users.get(id);
            u.setUsername(user.getUsername());
            u.setAge(user.getAge());
            users.put(id, u);
            r.setResult(u);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String jsonTest() {
        return " hi you!";
    }



    //添加用户
    @PostMapping(value="/adduser")
    public @ResponseBody String addUser(
//                                            @RequestParam String dictum,
//                                            @RequestParam String password,
//                                            @RequestParam String username
                                            @RequestBody User user

    ) {
//        User user = new User();
//        user.setDictum(dictum);
//        user.setPassword(password);
//        user.setUsername(username);
        System.out.println(user);
        userService.saveUser(user);
//        redisService.set(user.getId()+"", user);
        return "Saved";
    }
}
