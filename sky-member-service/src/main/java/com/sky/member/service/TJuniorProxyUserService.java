package com.sky.member.service;

import com.sky.member.entity.TJuniorProxyUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 二级代理商
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
public interface TJuniorProxyUserService {
	
	TJuniorProxyUserEntity queryObject(Integer id);
	
	List<TJuniorProxyUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TJuniorProxyUserEntity tJuniorProxyUser);
	
	void update(TJuniorProxyUserEntity tJuniorProxyUser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
