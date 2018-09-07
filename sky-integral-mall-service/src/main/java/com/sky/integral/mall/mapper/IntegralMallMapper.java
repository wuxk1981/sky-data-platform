package com.sky.integral.mall.mapper;

import com.sky.integral.mall.entity.GoodsCat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IntegralMallMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCat record);

    int insertSelective(GoodsCat record);

    GoodsCat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsCat record);

    int updateByPrimaryKey(GoodsCat record);



    List<Map<String,Object>> selectTypeByCagentName(String cagentName);

    int countGoodsByCondition(Map<String,Object> paramMap);

    List<Map<String,Object>> selectGoodsByCondition(Map<String,Object> paramMap);

    List<Map<String,Object>> selectTypeByCondition(Map<String,Object> paramMap);

    Map<String,Object> goodsDetails(Map<String,Object> paramMap);

    Map<String,Object> selectUserById(Map<String,Object> queryMap);

    Map<String,Object> selectUserWallet(Map<String,Object> queryMap);

    String generatorOrder(Map<String,Object> paramMap);

    List<Map<String,Object>> orderHistory(Map<String,Object> paramMap);

    int countOrderHistory(Map<String,Object> paramMap);

    List<Map<String,Object>> rankingList(Map<String,Object> paramMap);
}