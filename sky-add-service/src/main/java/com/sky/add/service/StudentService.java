/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.quick.druid.service 
 *
 *    Filename:    StudentService.java 
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
 *    Create at:   2018年08月26日 10:25 
 *
 *    Revision: 
 *
 *    2018/8/26 10:25 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.add.service;


import com.sky.add.entity.Student;

/**
 *  * @ClassName StudentService
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 10:25
 *  * @Version 1.0.0
 *  
 **/
public interface StudentService {
    Student selectStudentById(int id);
}
