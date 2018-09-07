package com.sky.member.service;

import com.sky.member.entity.TLoginerrormapEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户登录失败次数
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 16:15:19
 */
public interface TLoginerrormapService {
	
	TLoginerrormapEntity queryObject(Integer id);
	
	List<TLoginerrormapEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TLoginerrormapEntity tLoginerrormap);
	
	void update(TLoginerrormapEntity tLoginerrormap);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<Map<String, Object>> selectLoginErrorMap(String username);

	void deleteLoginErrorMap(String username);

	void insertLoginErrorMap(String username);

}
