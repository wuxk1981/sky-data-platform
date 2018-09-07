/******************************************************************
 *
 *    Powered By tianxia-online.
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络
 *    http://www.d-telemedia.com/
 *
 *    Package:     PACKAGE_NAME
 *
 *    Filename:    com.sky.data.process.mapper.BGRecordMapperTest.java
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
 *    Create at:   2018年09月07日 19:54
 *
 *    Revision:
 *
 *    2018/9/7 19:54
 *        - first revision
 *
 *****************************************************************/
package com.sky.data.process.mapper;


import base.BaseTest;
import com.alibaba.fastjson.JSON;
import com.sky.data.process.entity.BGVideoRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  * @ClassName com.sky.data.process.mapper.BGRecordMapperTest
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年09月07日 19:54
 *  * @Version 1.0.0
 *  
 **/
public class BGRecordMapperTest extends BaseTest {

    @Autowired
    private BGVideoRecordMapper bgVideoRecordMapper;

    @Test
    public void demo001() {
        BGVideoRecord bgVideoRecord = bgVideoRecordMapper.selectByPrimaryKey(1L);
        System.out.println("---------------------" + JSON.toJSONString(bgVideoRecord));
    }
}
