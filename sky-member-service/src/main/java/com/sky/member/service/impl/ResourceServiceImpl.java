package com.sky.member.service.impl;

import com.sky.member.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.member.entity.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sky.member.service.ResourceService;



@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;


	@Override
	public ResourceEntity queryObject(Long id){
		return resourceMapper.queryObject(id);
	}
	@Override
	public Set<ResourceEntity> queryResourcesByRoleId(Long roleId) {
		return resourceMapper.queryResourcesByRoleId(roleId);
	}
	@Override
	public Set<ResourceEntity> queryResourceByUserId(Long userId) {
		return resourceMapper.queryResourceByUserId(userId);
	}
	@Override
	public Set<ResourceEntity> queryResourceByPreResId(Long pId) {
		return resourceMapper.queryResourceByPreResId(pId);
	}

	@Override
	public List<ResourceEntity> queryList(Map<String, Object> map){
		return resourceMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return resourceMapper.queryTotal(map);
	}
	
	@Override
	public void save(ResourceEntity resource){
		resourceMapper.save(resource);
	}
	
	@Override
	public void update(ResourceEntity resource){
		resourceMapper.update(resource);
	}
	
	@Override
	public void delete(Long id){
		resourceMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		resourceMapper.deleteBatch(ids);
	}
	
}
