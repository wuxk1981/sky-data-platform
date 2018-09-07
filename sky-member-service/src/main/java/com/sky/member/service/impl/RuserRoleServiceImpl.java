package com.sky.member.service.impl;

import com.sky.member.mapper.RuserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.member.entity.*;
import java.util.List;
import java.util.Map;

import com.sky.member.service.RuserRoleService;



@Service("ruserRoleService")
public class RuserRoleServiceImpl implements RuserRoleService {
	@Autowired
	private RuserRoleMapper ruserRoleMapper;



	@Override
	public RuserRoleEntity queryObject(Long id){
		return ruserRoleMapper.queryObject(id);
	}

    @Override
    public void deleteBatchByUserIds(Long[] ids) {
         ruserRoleMapper.deleteBatchByUserIds(ids);
    }
	@Override
	public void deleteBatchByRolesIds(Long[] rolesIds) {
		ruserRoleMapper.deleteBatchByRolesIds(rolesIds);
	}

    @Override
	public List<RuserRoleEntity> queryList(Map<String, Object> map){
		return ruserRoleMapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return ruserRoleMapper.queryTotal(map);
	}

	@Override
	public void save(RuserRoleEntity ruserRole){
		ruserRoleMapper.save(ruserRole);
	}

	@Override
	public void saveBatch(Long userId, Long[] rolesIds) {
		ruserRoleMapper.saveBatch(userId,rolesIds);
	}
	@Override
	public void update(RuserRoleEntity ruserRole){
		ruserRoleMapper.update(ruserRole);
	}

	@Override
	public void delete(Long id){
		ruserRoleMapper.delete(id);
	}

	@Override
	public void deleteBatch(Long[] ids){
		ruserRoleMapper.deleteBatch(ids);
	}

}
