package com.sky.member.mapper;

import com.sky.member.entity.TUserTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 会员分层
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
@Mapper
public interface TUserTypeMapper {
    void save(TUserTypeEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TUserTypeEntity> list);

    int update(TUserTypeEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TUserTypeEntity queryObject(@Param("id") Object id);

    List<TUserTypeEntity> queryList(Map<String, Object> map);

    List<TUserTypeEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);
	
}
