//package com.sky.add.config.redis;
//
//
//import com.sky.common.redis.RedisService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringbootRedisDemo003ApplicationTests {
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
