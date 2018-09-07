package com.sky.member.mapper;

import com.sky.member.entity.TRefererUrlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 域名白名单
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 09:22:01
 */
@Mapper
public interface TRefererUrlMapper   {

    List<Map<String, String>> selectRefererUrl(@Param("domain")String domain,@Param("cagent") String cagent) ;

    void save(TRefererUrlEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<TRefererUrlEntity> list);

    int update(TRefererUrlEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TRefererUrlEntity queryObject(@Param("id") Object id);

    List<TRefererUrlEntity> queryList(Map<String, Object> map);

    //List<TUserEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);



}
