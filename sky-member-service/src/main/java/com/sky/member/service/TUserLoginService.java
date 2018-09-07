package com.sky.member.service;

import com.sky.member.entity.TUserLoginEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
public interface TUserLoginService {
	
	TUserLoginEntity queryObject(Integer id);
	
	List<TUserLoginEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserLoginEntity tUserLogin);
	
	void update(TUserLoginEntity tUserLogin);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void insertLogin(Map<String, Object> map);


}
