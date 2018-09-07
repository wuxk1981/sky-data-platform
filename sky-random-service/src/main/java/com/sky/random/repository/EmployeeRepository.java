/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.random.repository 
 *
 *    Filename:    EmployeeRepository.java 
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
package com.sky.random.repository;

import com.sky.random.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  * @ClassName EmployeeRepository
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月26日 23:03
 *  * @Version 1.0.0
 *  
 **/
public interface EmployeeRepository extends JpaRepository<Employee,String> {
}
