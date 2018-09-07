package com.sky.member.mapper;

import com.sky.member.entity.TProxyUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 一级代理商
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
@Mapper
public interface TProxyUserMapper {
    void save(TProxyUserEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TProxyUserEntity> list);

    int update(TProxyUserEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TProxyUserEntity queryObject(@Param("id") Object id);

    List<TProxyUserEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);
}
