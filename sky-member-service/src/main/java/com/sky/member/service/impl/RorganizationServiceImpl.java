package com.sky.member.service.impl;

import com.sky.member.mapper.RorganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.member.entity.*;
import java.util.List;
import java.util.Map;

import com.sky.member.service.RorganizationService;



@Service
public class RorganizationServiceImpl implements RorganizationService {
	@Autowired
	private RorganizationMapper rorganizationMapper;
	
	@Override
	public RorganizationEntity queryObject(Long id){
		return rorganizationMapper.queryObject(id);
	}
	
	@Override
	public List<RorganizationEntity> queryList(Map<String, Object> map){
		return rorganizationMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return rorganizationMapper.queryTotal(map);
	}
	
	@Override
	public void save(RorganizationEntity rorganization){
		rorganizationMapper.save(rorganization);
	}
	
	@Override
	public void update(RorganizationEntity rorganization){
		rorganizationMapper.update(rorganization);
	}
	
	@Override
	public void delete(Long id){
		rorganizationMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		rorganizationMapper.deleteBatch(ids);
	}
	
}
