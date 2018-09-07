package com.sky.member.service;

import com.sky.member.entity.TProxyUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 一级代理商
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
public interface TProxyUserService {
	
	TProxyUserEntity queryObject(Integer id);
	
	List<TProxyUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TProxyUserEntity tProxyUser);
	
	void update(TProxyUserEntity tProxyUser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
