/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay 
 *
 *    Filename:    SwaggerWebMvcConfig.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Hardy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年08月26日 12:01 
 *
 *    Revision: 
 *
 *    2018/8/26 12:01 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *  * @ClassName SwaggerWebMvcConfig
 *  * @Description Swagger配置路径重定向
 *  * @Author Hardy
 *  * @Date 2018年08月26日 12:01
 *  * @Version 1.0.0
 *  
 **/
@Configuration
public class SwaggerConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
                }
                }
