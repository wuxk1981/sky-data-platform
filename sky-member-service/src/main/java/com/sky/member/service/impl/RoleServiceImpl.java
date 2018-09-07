package com.sky.member.service.impl;

import com.sky.member.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.member.entity.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sky.member.service.RoleService;



@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Set<RoleEntity> queryRolesList(Long userid) {
		return roleMapper.queryRolesList(userid);
	}


	
	@Override
	public RoleEntity queryObject(Long id){
		return roleMapper.queryObject(id);
	}
	
	@Override
	public List<RoleEntity> queryList(Map<String, Object> map){
		return roleMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return roleMapper.queryTotal(map);
	}
	
	@Override
	public void save(RoleEntity role){
		roleMapper.save(role);
	}
	
	@Override
	public void update(RoleEntity role){
		roleMapper.update(role);
	}
	
	@Override
	public void delete(Long id){
		roleMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		roleMapper.deleteBatch(ids);
	}
	
}
