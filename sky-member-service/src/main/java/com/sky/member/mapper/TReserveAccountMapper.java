package com.sky.member.mapper;

import com.sky.member.entity.TReserveAccountEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统保留账号
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
@Mapper
public interface TReserveAccountMapper  {
    void save(TReserveAccountEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TReserveAccountEntity> list);

    int update(TReserveAccountEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TReserveAccountEntity queryObject(@Param("id") Object id);

    List<TReserveAccountEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);
}
