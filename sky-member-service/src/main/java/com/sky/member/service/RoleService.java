package com.sky.member.service;

import java.util.Set;
import java.util.Map;
import java.util.List;
import com.sky.member.entity.*;
/**
 * 角色
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
public interface RoleService {
	Set<RoleEntity> queryRolesList(Long userid);

	RoleEntity queryObject(Long id);
	
	List<RoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RoleEntity role);
	
	void update(RoleEntity role);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
