/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.data.process.controller 
 *
 *    Filename:    BGVideoRecordController.java 
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
 *    Create at:   2018年09月07日 23:22 
 *
 *    Revision: 
 *
 *    2018/9/7 23:22 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.data.process.controller;

import com.alibaba.fastjson.JSON;
import com.sky.data.process.entity.BGVideoRecord;
import com.sky.data.process.service.IBGVideoRecordService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *  * @ClassName BGVideoRecordController
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Tammy
 *  * @Date 2018年09月07日 23:22
 *  * @Version 1.0.0
 *  
 **/
@Api(description = "BGVideoRecordController")
@RestController
public class BGVideoRecordController {

    @Autowired
    private IBGVideoRecordService bgVideoRecordService;

    @GetMapping(value = "/getBGVideoRecordById/{id}")
    public String getBGVideoRecordById(@PathVariable String id) {
        BGVideoRecord bgVideoRecord = bgVideoRecordService.selectByPrimaryKey(Long.valueOf(id));
        return JSON.toJSONString(bgVideoRecord);
    }
}
