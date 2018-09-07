/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.random.controller 
 *
 *    Filename:    EmployeeController.java 
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
 *    Create at:   2018年08月26日 23:03 
 *
 *    Revision: 
 *
 *    2018/8/26 23:03 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.random.controller;

import com.sky.random.entity.Employee;
import com.sky.random.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *  * @ClassName EmployeeController
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 23:03
 *  * @Version 1.0.0
 *  
 **/
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public List<Employee> queryAll() {
        List<Employee> list = new ArrayList<Employee>();
        list = employeeRepository.findAll();
        return list;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID().toString().replace("-", ""));
        employeeRepository.save(employee);
        return "ok";
    }

    public static void main(String[] args) {
        String replace = UUID.randomUUID().toString().replace("-", "");
        System.out.println("---------"+replace);
    }
}
