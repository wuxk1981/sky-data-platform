package com.sky.member.service;
import com.sky.member.entity.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 资源
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
public interface ResourceService {
	
	ResourceEntity queryObject(Long id);

	Set<ResourceEntity> queryResourcesByRoleId(Long roleId);

	Set<ResourceEntity> queryResourceByUserId(Long userId);

	Set<ResourceEntity>  queryResourceByPreResId(Long pId);
	
	List<ResourceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ResourceEntity resource);
	
	void update(ResourceEntity resource);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
