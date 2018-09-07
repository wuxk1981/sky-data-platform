/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.quick.druid.controller 
 *
 *    Filename:    SwaggerDemoController.java 
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
 *    Create at:   2018年08月26日 10:23 
 *
 *    Revision: 
 *
 *    2018/8/26 10:23 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.add.controller;

import com.sky.add.entity.Student;
import com.sky.add.service.StudentService;
import com.xiaoleilu.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  * @ClassName SwaggerDemoController
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 10:23
 *  * @Version 1.0.0
 *  
 **/
@RestController
@RequestMapping("api")
@Api(description = "SwaggerDemoController相关的API")
public class SwaggerDemoController {
    @Autowired
    private StudentService studentService;

    private static final Logger logger= LoggerFactory.getLogger(SwaggerDemoController.class);

    @ApiOperation(value = "根据id查询学生信息", notes = "查询数据库中某个的学生信息")
    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "integer")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable int id) {
        logger.info("开始查询某个学生信息");
        return studentService.selectStudentById(id);
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public String getStudent(@PathVariable Long[] ids) {
        System.out.println("-----ids:--------"+JSONUtil.toJsonStr(ids));
        logger.info("开始查询某个学生信息");
        return JSONUtil.toJsonStr(ids);
    }



}
