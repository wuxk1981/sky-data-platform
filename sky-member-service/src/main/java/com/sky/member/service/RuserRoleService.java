package com.sky.member.service;

import java.util.List;
import java.util.Map;
import com.sky.member.entity.*;
/**
 * 用户角色
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
public interface RuserRoleService {
	
	RuserRoleEntity queryObject(Long id);
	
	List<RuserRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RuserRoleEntity ruserRole);

	void saveBatch(Long userId, Long[] rolesIds);
	
	void update(RuserRoleEntity ruserRole);

	void deleteBatchByUserIds(Long[] ids);

	void deleteBatchByRolesIds(Long[] rolesIds);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
