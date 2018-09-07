package com.sky.member.service;

import com.sky.member.entity.TPlatformConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
public interface TPlatformConfigService {
	
	TPlatformConfigEntity queryObject(Integer id);
	
	List<TPlatformConfigEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TPlatformConfigEntity tPlatformConfig);
	
	void update(TPlatformConfigEntity tPlatformConfig);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<Map<String, String>> selectPlatFromInfo(String KEY);
}
