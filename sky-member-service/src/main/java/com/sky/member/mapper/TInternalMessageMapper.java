package com.sky.member.mapper;


import com.sky.member.entity.TInternalMessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 消息提示表
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-06 10:43:55
 */
@Mapper
public interface TInternalMessageMapper {
    void save(TInternalMessageEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TInternalMessageEntity> list);

    int update(TInternalMessageEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TInternalMessageEntity queryObject(@Param("id") Object id);

    List<TInternalMessageEntity> queryList(Map<String, Object> map);

    //List<TInternalMessageEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);

    void updateMessageInfo(@Param("uid") String uid,@Param("id") String id);

    void deleteMessage(@Param("uid") String uid,@Param("id") String id);

    List<Map<String, Object>> selectMessageInfo(@Param("uid") String uid, @Param("id") String id);

}
