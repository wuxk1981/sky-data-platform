package com.sky.member.service.impl;

import com.sky.member.mapper.RoleResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.member.entity.*;
import java.util.List;
import java.util.Map;

import com.sky.member.service.RoleResourceService;



@Service
public class RoleResourceServiceImpl implements RoleResourceService {
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public void deleteBatchByResourceIds(Long[] resourceIds) {
		roleResourceMapper.deleteBatchByResourceIds(resourceIds);
	}

	@Override
	public void deleteBatchByRolesIds(Long[] roleIds) {
		 roleResourceMapper.deleteBatchByRolesIds(roleIds);
	}

	@Override
	public RoleResourceEntity queryObject(Long id){
		return roleResourceMapper.queryObject(id);
	}
	
	@Override
	public List<RoleResourceEntity> queryList(Map<String, Object> map){
		return roleResourceMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return roleResourceMapper.queryTotal(map);
	}
	
	@Override
	public void save(RoleResourceEntity roleResource){
		roleResourceMapper.save(roleResource);
	}
	@Override
	public void saveBatch(Long roleId,Long[] resourceIds){
		roleResourceMapper.saveBatch(roleId,resourceIds);
	}
	@Override
	public void update(RoleResourceEntity roleResource){
		roleResourceMapper.update(roleResource);
	}
	
	@Override
	public void delete(Long id){
		roleResourceMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		roleResourceMapper.deleteBatch(ids);
	}
	
}
