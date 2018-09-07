package com.sky.member.service;

import com.sky.member.entity.TUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员信息
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
public interface TUserService {

	Map<String, Object> selectUserById(Map<String, Object> map);
	
	TUserEntity queryObject(Integer uid);
	
	List<TUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserEntity tUser);
	
	void update(TUserEntity tUser);
	
	void delete(Integer uid);
	
	void deleteBatch(Integer[] uids);

	List<Map<String, Object>> queryUserByUserName(String userName,String password);

	List<Map<String, Object>> queryReserveAccount(String userName, String cagent);

	List<Map<String, String>> queryProxyByName(Map<String, Object> map);

	List<Map<String, Object>> getProxyUser(String proxyname, String cagent);

	List<Map<String, Object>> getJuniorProxyUser(String proxyname, String cagent);

	List<Map<String, Object>> selectQkpwdCheck(String uid);

	void insertUser(Map<String, Object> map);

	// 手机号登录
	List<Map<String, Object>> UserLoginByMobile(String cagent, String mobileNo, String password);

	List<Map<String, Object>> UserLogin(String userName, String passWord);

	void updateGame(Map<String, Object> map);

	List<Map<String, String>> checkkpwd(Map<String, Object> map);

	Map<String, Object> selectUserInfo(Map<String, Object> map);


}
