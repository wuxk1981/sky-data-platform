/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.example.bean 
 *
 *    Filename:    Student.java 
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
 *    Create at:   2018年08月25日 11:51 
 *
 *    Revision: 
 *
 *    2018/8/25 11:51 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.random.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *  * @ClassName Student
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月25日 11:51
 *  * @Version 1.0.0
 *  
 **/
@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    private String address;
}
