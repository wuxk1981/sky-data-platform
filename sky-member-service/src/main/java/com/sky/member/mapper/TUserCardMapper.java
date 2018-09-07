package com.sky.member.mapper;

import com.sky.member.entity.TUserCardEntity;
import com.sky.member.entity.TUserCardEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 会员银行卡信息
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 21:34:09
 */
@Mapper
public interface TUserCardMapper {
    void save(TUserCardEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TUserCardEntity> list);

    int update(TUserCardEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TUserCardEntity queryObject(@Param("id") Object id);

    List<TUserCardEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);

    List<Map<String, String>> selectUserCard(Map<String, Object> map);

    void insertUserCard(Map<String, Object> map);


    void deleteUserCard(Map<String, Object> map);


}
