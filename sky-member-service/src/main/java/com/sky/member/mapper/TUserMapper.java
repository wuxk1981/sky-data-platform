package com.sky.member.mapper;

import com.sky.member.entity.TUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Map;

/**
 * 会员信息
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
@Mapper
public interface TUserMapper {
    void save(TUserEntity t);

    void save(Map<String, Object> map);


    void saveBatch(List<TUserEntity> list);

    int update(TUserEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    TUserEntity queryObject(@Param("id") Object id);

    List<TUserEntity> queryList(Map<String, Object> map);

    //List<TUserEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);

    List<Map<String, Object>> queryUserByUserName(@Param("userName")String userName,@Param("passWord")String passWord);

    List<Map<String, Object>> selectReserveAccount(@Param("userName")String userName,@Param("cagent") String cagent);


    List<Map<String, Object>> getProxyUser(@Param("proxyname")String proxyname,@Param("cagent") String cagent);

    List<Map<String, String>> queryProxyByName(Map<String, Object> map);

    List<Map<String, Object>> getJuniorProxyUser(@Param("proxyname")String proxyname,@Param("cagent") String cagent);

    List<Map<String, Object>> selectQkpwdCheck(@Param("uid") String uid);
    List<Map<String, String>> checkkpwd(Map<String, Object> map);
    void insertUser(Map<String, Object> map);

    Map<String, Object> selectUserById(Map<String, Object> map);


    List<Map<String, Object>> selectUserByMobileNo(@Param("cagent")String cagent,@Param("mobileNo") String mobileNo, @Param("password") String password);

    List<Map<String, Object>> selectUserByUserName(@Param("userName")String userName, @Param("passWord")String passWord);

    Map<String, Object> selectUserInfo(Map<String, Object> map);

    void updateGame(Map<String, Object> map);



}
