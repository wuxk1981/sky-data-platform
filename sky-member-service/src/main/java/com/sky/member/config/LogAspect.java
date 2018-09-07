package com.sky.member.config;///******************************************************************
// *
// *    Powered By tianxia-online.
// *
// *    Copyright (c) 2018-2020 Digital Telemedia 天下网络
// *    http://www.d-telemedia.com/
// *
// *    Package:     com.sky.auth.config
// *
// *    Filename:    LogAspect.java
// *
// *    Description: TODO(用一句话描述该文件做什么)
// *
// *    Copyright:   Copyright (c) 2018-2020
// *
// *    Company:     天下网络科技
// *
// *    @author: Golden
// *
// *    @version: 1.0.0
// *
// *    Create at:   2018年08月31日 19:47
// *
// *    Revision:
// *
// *    2018/8/31 19:47
// *        - first revision
// *
// *****************************************************************/
//package com.sky.auth.config;
//
///**
// * @ClassName LogAspect
// * @Description TODO(进行controller层进行filter，看一下是否请求中带有token值)
// * @Author Golden
// * @Date 2018年08月31日 19:47
// * @Version 1.0.0
// **/
//
//import com.alibaba.druid.util.StringUtils;
//
//import com.xiaoleilu.hutool.json.JSONObject;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//import com.sky.auth.utils.token.TokenUtils;
//import com.sky.auth.entity.RuserEntity;
//
///**
// * Created by wuwf on 17/4/27.
// * 日志切面
// */
//@Aspect
//@Component
//public class LogAspect {
//    private static final Logger logger = LoggerFactory.getLogger("addLogger");
//
//    @Autowired
//    private GlobalExceptionHandler exceptionHandle;
//
//    @Pointcut("execution(public * com.sky.auth.controller.*.*(..))")
//    public void webLog() {
//    }
//
//    @Before("webLog()")
//    public void deBefore(JoinPoint joinPoint) throws Throwable {
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容 request.requestDispatcherpath  headers  x-access-token
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("token : " + request.getHeader("x-access-token"));
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//        String url = request.getRequestURL().toString();
//        String token = request.getHeader("x-access-token");
//
//        //放行登录请求
//        if (url.indexOf("/user/login") > 0) {
//            return;
//        } else {
//            //存在token值 token只保证一个小时有效期。
//            if (!StringUtils.isEmpty(token)) {
//                //从Session中取以前的user对象
//                RuserEntity user = (RuserEntity) request.getSession().getAttribute("user");
//                //user对象是空值，表明从来没有进行登录过
//                if (null == user) {
//                    request.getRequestDispatcher("/user/login").forward(request, attributes.getResponse());
//                    return;
//                }
//                //验证成功，直接退出
//                if (TokenUtils.validToken(token, user.getId().toString())) {
//                    return;
//                } else {
//                    //有token,也有Session，验证不通过
//                    request.getRequestDispatcher("/user/login").forward(request, attributes.getResponse());
//                    return;
//                }
//            }else{
//                //不存在token信息
//                request.getRequestDispatcher("/user/login").forward(request, attributes.getResponse());
//                return;
//            }
//        }
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        logger.info("方法的返回值 : " + ret);
//    }
//
//    //后置异常通知
//    @AfterThrowing("webLog()")
//    public void throwss(JoinPoint jp) {
//        logger.info("方法异常时执行.....");
//    }
//
//    @Around("webLog()")
//    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
////        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
////        HttpServletRequest request = attributes.getRequest();
//        JSONObject result = null;
//        try {
//
//        } catch (Exception e) {
//            return exceptionHandle.defaultErrorHandler(e);
//        }
//        if (result == null) {
//            return proceedingJoinPoint.proceed();
//        } else {
//            return result;
//        }
//    }
//
//    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
//    @After("webLog()")
//    public void after(JoinPoint jp) {
//        logger.info("方法最后执行.....");
//    }
//
//    //环绕通知,环绕增强，相当于MethodInterceptor
//    @Around("webLog()")
//    public Object arround(ProceedingJoinPoint pjp) {
//        logger.info("方法环绕start.....");
//        try {
//            Object o = pjp.proceed();
//            logger.info("方法环绕proceed，结果是 :" + o);
//            return o;
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}