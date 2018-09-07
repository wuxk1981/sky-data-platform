package com.sky.member.mapper;

import com.sky.member.entity.TLoginerrormapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户登录失败次数
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 16:15:19
 */
@Mapper
public interface TLoginerrormapMapper {
    void save(TLoginerrormapEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TLoginerrormapEntity> list);

    int update(TLoginerrormapEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TLoginerrormapEntity queryObject(@Param("id") Object id);

    List<TLoginerrormapEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);

    List<Map<String, Object>> selectLoginErrorMap(@Param("username") String username);

    void deleteLoginErrorMap(@Param("username")String username);

    void insertLoginErrorMap(@Param("username")String username);
	
}
