package com.sky.member.controller;

import com.sky.member.utils.HttpConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xiaoleilu.hutool.json.JSONObject;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Controller基类
 */
public class BaseController { 

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    protected final static String DATE_FORMATE = "yyyy-MM-dd";
    
    //在线用户
    public static ConcurrentHashMap<String,Map<String, String>> loginmaps=new ConcurrentHashMap<String,Map<String, String>>();



    //支付回调
    public static ConcurrentHashMap<String,String> payMap=new ConcurrentHashMap<String,String>();
    //短信发送
    public static ConcurrentHashMap<String,String> msgMap=new ConcurrentHashMap<String,String>();
    //短信登录,累计密码错误次数记录
    public static ConcurrentHashMap<String,Integer> errorMap=new ConcurrentHashMap<String,Integer>();
    //游戏转账锁
    public static ConcurrentHashMap<String,String> TransferMap=new ConcurrentHashMap<String,String>();
    
    public static String addUserCard="0";
    //支付
    public static ConcurrentHashMap<String,String> userPayMap=new ConcurrentHashMap<String,String>();
    //抽奖
    public static ConcurrentHashMap<String,String> luckDrawMap=new ConcurrentHashMap<String,String>();

}
