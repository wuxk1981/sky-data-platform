/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay 
 *
 *    Filename:    Swagger2.java 
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
 *    Create at:   2018年08月26日 11:59 
 *
 *    Revision: 
 *
 *    2018/8/26 11:59 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 *  * @ClassName Swagger2
 *  * @Description Swagger设置类
 *  * @Author Hardy
 *  * @Date 2018年08月26日 11:59
 *  * @Version 1.0.0
 *  
 **/
@Configuration
@ComponentScan(basePackages = { "com.sky.pay.controller" })//配置controller路径
@EnableSwagger2
@SuppressWarnings({"unchecked","deprecation"})
public class SwaggerApplication {
    @Bean
    public Docket createRestApi() {
//        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
//            @Override
//            public boolean apply(RequestHandler input) {
//                Class<?> declaringClass = input.declaringClass();
//                if (declaringClass == BasicErrorController.class)// 排除
//                    return false;
//                if(declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
//                    return true;
//                if(input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
//                    return true;
//                return false;
//            }
//        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(predicate)
                .paths(Predicates.or(
                        //这里添加你需要展示的接口
                        PathSelectors.ant("/pay/*/**"),
                        PathSelectors.ant("/notify/*/**")
                        )

                )
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("支付服务类相关接口")//大标题
                .description("Sky-Pay-Service Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
                .version("1.0")//版本
//                .termsOfServiceUrl("NO terms of service")
                .contact("hardy18723@hotmail.com")//作者
//                .license("The Apache License, Version 2.0")
//                .licenseUrl("http://192.168.0.155:8900/swagger-ui.html")
                .build();
    }
}
