package com.sky.member.service;

import java.util.List;
import java.util.Map;
import com.sky.member.entity.*;
/**
 * 组织机构
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
public interface RorganizationService {
	
	RorganizationEntity queryObject(Long id);
	
	List<RorganizationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RorganizationEntity rorganization);
	
	void update(RorganizationEntity rorganization);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
