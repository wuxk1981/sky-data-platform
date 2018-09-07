package com.sky.integral.mall.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface IntegralMallService {
    List<Map<String,Object>> selectTypeByCagentName(String cagentName);

    int countGoodsByCondition(Map<String,Object> paramMap);

    List<Map<String, Object>> selectGoodsByCondition(Map<String,Object> paramMap);

    List<Map<String, Object>> selectTypeByCondition(Map<String,Object> paramMap);

    Map<String,Object> goodsDetails(Map<String,Object> paramMap);

    JSONObject generateOrder(Integer uid,Map<String,Object> paramMap);

    JSONArray getTypeByCagentName(String cagentName,List<Map<String,Object>> resultList);

    List<Map<String,Object>> orderHistory(Map<String,Object> paramMap);

    int countOrderHistory(Map<String,Object> paramMap);

    //查询兑换排行榜
    List<Map<String,Object>> rankingList(Map<String,Object> paramMap);
}
