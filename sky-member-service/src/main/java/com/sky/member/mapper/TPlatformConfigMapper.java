package com.sky.member.mapper;

import com.sky.member.entity.TPlatformConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
@Mapper
public interface TPlatformConfigMapper  {
    void save(TPlatformConfigEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TPlatformConfigEntity> list);

    int update(TPlatformConfigEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TPlatformConfigEntity queryObject(@Param("id") Object id);

    List<TPlatformConfigEntity> queryList(Map<String, Object> map);

    //List<TPlatformConfigEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);

    List<Map<String, String>> selectPlatFromInfo(@Param("KEY") String KEY);
}
