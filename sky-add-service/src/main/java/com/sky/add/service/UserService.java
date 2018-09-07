/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.add.service 
 *
 *    Filename:    UserService.java 
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
 *    Create at:   2018年08月31日 20:42 
 *
 *    Revision: 
 *
 *    2018/8/31 20:42 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.add.service;

import com.sky.add.entity.User;

/**
 *  * @ClassName UserService
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年08月31日 20:42
 *  * @Version 1.0.0
 *  
 **/
public interface UserService {
    void saveUser(User user);
}
