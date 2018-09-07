package com.sky.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sky.member.mapper.TPlatformConfigMapper;
import com.sky.member.entity.TPlatformConfigEntity;
import com.sky.member.service.TPlatformConfigService;



@Service("tPlatformConfigService")
public class TPlatformConfigServiceImpl implements TPlatformConfigService {
	@Autowired
	private TPlatformConfigMapper tPlatformConfigMapper;
	
	@Override
	public TPlatformConfigEntity queryObject(Integer id){
		return tPlatformConfigMapper.queryObject(id);
	}
	
	@Override
	public List<TPlatformConfigEntity> queryList(Map<String, Object> map){
		return tPlatformConfigMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tPlatformConfigMapper.queryTotal(map);
	}
	
	@Override
	public void save(TPlatformConfigEntity tPlatformConfig){
		tPlatformConfigMapper.save(tPlatformConfig);
	}
	
	@Override
	public void update(TPlatformConfigEntity tPlatformConfig){
		tPlatformConfigMapper.update(tPlatformConfig);
	}
	
	@Override
	public void delete(Integer id){
		tPlatformConfigMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tPlatformConfigMapper.deleteBatch(ids);
	}

	@Override
	public List<Map<String, String>> selectPlatFromInfo(String KEY) {
		return tPlatformConfigMapper.selectPlatFromInfo(KEY);
	}
}
