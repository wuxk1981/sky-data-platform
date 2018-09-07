/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.random.service.impl 
 *
 *    Filename:    StudentServiceImpl.java
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
 *    Create at:   2018年08月26日 22:01 
 *
 *    Revision: 
 *
 *    2018/8/26 22:01 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.random.service.impl;

import com.sky.random.entity.Student;
import com.sky.random.repository.PersonRepository;
import com.sky.random.repository.StudentRepository;
import com.sky.random.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  * @ClassName StudentServiceimpl
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 22:01
 *  * @Version 1.0.0
 *  
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student selectStudentById(Long id) {
        List<Student> students = studentRepository.findAll();
        return students.get(0);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }
}
