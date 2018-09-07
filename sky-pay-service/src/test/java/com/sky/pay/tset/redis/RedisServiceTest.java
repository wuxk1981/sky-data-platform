///******************************************************************
// *
// *    Powered By tianxia-online.
// *
// *    Copyright (c) 2018-2020 Digital Telemedia 天下网络
// *    http://www.d-telemedia.com/
// *
// *    Package:     com.sky.pay.tset.redis
// *
// *    Filename:    RedisServiceTest.java
// *
// *    Description: TODO(用一句话描述该文件做什么)
// *
// *    Copyright:   Copyright (c) 2018-2020
// *
// *    Company:     天下网络科技
// *
// *    @author:     Tammy
// *
// *    @version:    1.0.0
// *
// *    Create at:   2018年09月03日 20:48
// *
// *    Revision:
// *
// *    2018/9/3 20:48
// *        - first revision
// *
// *****************************************************************/
//package com.sky.pay.tset.redis;
//
//import com.sky.common.redis.RedisService;
//import com.sky.pay.tset.BaseTest;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//
///**
// *  * @ClassName RedisServiceTest
// *  * @Description TODO(这里用一句话描述这个类的作用)
// *  * @Author Tammy
// *  * @Date 2018年09月03日 20:48
// *  * @Version 1.0.0
// *  
// **/
//
//public class RedisServiceTest extends BaseTest {
//
//    @Autowired
//    private RedisService redisService;
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    @Test
//    public void contextLoads() {
//        long id = redisService.incr("id", 1);
//        redisService.set("name","xiaohei"+id);
//        System.out.println("---id-------"+id);
//        String name = (String)redisService.get("name");
//        System.out.println("----------"+name);
//    }
//
//    @Test
//    public void contextLoads22() {
//        Cache cache = cacheManager.getCache("name");
//        System.out.println("------------"+cache.get("name").get());
//    }
//
//}
