/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.example.web 
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
 *    Create at:   2018年08月25日 11:49 
 *
 *    Revision: 
 *
 *    2018/8/25 11:49 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.random.controller;
import com.sky.random.entity.Student;
import com.sky.random.repository.StudentRepository;
import com.sky.random.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  * @ClassName SwaggerDemoController
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月25日 11:49
 *  * @Version 1.0.0
 *  
 **/
@RestController
@RequestMapping("api")
@Api("swaggerDemoController相关的api")
public class SwaggerDemoController {
    @Autowired
    private StudentRepository studentRepository;

    private static final Logger logger= LoggerFactory.getLogger(SwaggerDemoController.class);


    @ApiOperation(value = "根据id查询学生信息", notes = "查询数据库中某个的学生信息")
    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable Long id) {
        logger.info("开始查询某个学生信息");
        return studentRepository.findOne(id);
    }

    @ApiOperation(value = "保存学生信息", notes = "保存某个的学生信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@RequestBody Student student) {
        logger.info("保存某个学生信息");
        studentRepository.save(student);
        return "保存成功";
    }


}
