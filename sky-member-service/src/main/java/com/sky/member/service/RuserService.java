package com.sky.member.service;

import java.util.List;
import java.util.Map;
import com.sky.member.entity.*;
/**
 * 用户
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
public interface RuserService {
	RuserEntity queryRuserEntity(String userName, String Password);
	
	RuserEntity queryObject(Long id);
	
	RuserEntity queryByUsername(String username);
	
	List<RuserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RuserEntity ruser);
	
	void update(RuserEntity ruser);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
