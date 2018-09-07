package com.sky.member.service;

import java.util.List;
import java.util.Map;
import com.sky.member.entity.*;
/**
 * 角色资源
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
public interface RoleResourceService {
	
	RoleResourceEntity queryObject(Long id);
	
	List<RoleResourceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RoleResourceEntity roleResource);

	void saveBatch(Long roleId, Long[] resourceIds);
	
	void update(RoleResourceEntity roleResource);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	void deleteBatchByRolesIds(Long[] roleIds);
	void deleteBatchByResourceIds(Long[] resourceIds);
}
