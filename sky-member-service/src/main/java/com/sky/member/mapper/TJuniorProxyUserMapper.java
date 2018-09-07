package com.sky.member.mapper;


import com.sky.member.entity.TJuniorProxyUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 二级代理商
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
@Mapper
public interface TJuniorProxyUserMapper  {
    void save(TJuniorProxyUserEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TJuniorProxyUserEntity> list);

    int update(TJuniorProxyUserEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TJuniorProxyUserEntity queryObject(@Param("id") Object id);

    List<TJuniorProxyUserEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);
}
