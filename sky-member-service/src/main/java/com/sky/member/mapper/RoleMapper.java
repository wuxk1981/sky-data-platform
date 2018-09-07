package com.sky.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sky.member.entity.RoleEntity;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Mapper
public interface RoleMapper {

    Set<RoleEntity> queryRolesList(@Param("userid") Long userid);

    void save(RoleEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<RoleEntity> list);

    int update(RoleEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    RoleEntity queryObject(@Param("id") Object id);

    List<RoleEntity> queryList(Map<String, Object> map);

    List<RoleEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);
}
