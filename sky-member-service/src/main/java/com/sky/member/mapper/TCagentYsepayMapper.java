package com.sky.member.mapper;

import com.sky.member.entity.TCagentYsepayEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Map;

/**
 * 在线支付配置
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
@Mapper
public interface TCagentYsepayMapper {

    void save(TCagentYsepayEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TCagentYsepayEntity> list);

    int update(TCagentYsepayEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TCagentYsepayEntity queryObject(@Param("id") Object id);

    List<TCagentYsepayEntity> queryList(Map<String, Object> map);

    //List<TCagentYsepayEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);


    List<Map<String, String>> getYsepayConfig(@Param("username") String username);


}
