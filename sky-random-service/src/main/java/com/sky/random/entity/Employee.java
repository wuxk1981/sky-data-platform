/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.random.entity 
 *
 *    Filename:    Employee.java 
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
 *    Create at:   2018年08月26日 23:01 
 *
 *    Revision: 
 *
 *    2018/8/26 23:01 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.random.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *  * @ClassName Employee
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 23:01
 *  * @Version 1.0.0
 *  
 **/
@Entity
@Table(name = "employee")
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 4657327639913519242L;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;
}
