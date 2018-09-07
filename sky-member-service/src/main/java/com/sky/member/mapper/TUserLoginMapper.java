package com.sky.member.mapper;

import com.sky.member.entity.TUserLoginEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
@Mapper
public interface TUserLoginMapper  {
    void save(TUserLoginEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TUserLoginEntity> list);

    int update(TUserLoginEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TUserLoginEntity queryObject(@Param("id") Object id);

    List<TUserLoginEntity> queryList(Map<String, Object> map);


    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);


    void insertLogin(Map<String, Object> map);
}
