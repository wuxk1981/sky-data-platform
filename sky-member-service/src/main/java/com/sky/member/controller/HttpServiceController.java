/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.member.controller 
 *
 *    Filename:    HttpServiceController.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author: Golden
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年09月04日 11:24 
 *
 *    Revision: 
 *
 *    2018/9/4 11:24 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.member.controller;

import com.sky.member.config.GlobalExceptionHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.xiaoleilu.hutool.json.JSONObject;
import com.sky.member.httpclient.HttpClientTest;

import java.util.HashMap;
import java.util.Map;

import com.sky.member.entity.TokenEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName HttpServiceController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Golden
 * @Date 2018年09月04日 11:24
 * @Version 1.0.0
 **/

@RestController
@RefreshScope
@Api(description = "httpClient请求")
@RequestMapping("/httpService")
public class HttpServiceController {

    @Autowired
    private GlobalExceptionHandler exceptionHandle;

    @ApiOperation(value = "Token接口", notes = "得到用户的token接口")
    @RequestMapping(value = "/GetToken", method = RequestMethod.POST)
    public JSONObject sendPostDataByMapGetToken(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        try {
            TokenEntity to=HttpClientTest.sendPostDataByMap( null, params, "UTF-8");
            //将当前的token值存放 在session中。
            request.getSession().setAttribute("token_"+params.get("name"), to);
            return jo.put("code", "0").put("TokenEntity", to);
        } catch (Exception e) {
            jo = exceptionHandle.defaultErrorHandler(e);
        }
        return jo;
    }
}