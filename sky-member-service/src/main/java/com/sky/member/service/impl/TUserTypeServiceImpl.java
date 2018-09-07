package com.sky.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sky.member.mapper.TUserTypeMapper;
import com.sky.member.entity.TUserTypeEntity;
import com.sky.member.service.TUserTypeService;



@Service("tUserTypeService")
public class TUserTypeServiceImpl implements TUserTypeService {
	@Autowired
	private TUserTypeMapper tUserTypeMapper;
	
	@Override
	public TUserTypeEntity queryObject(Integer id){
		return tUserTypeMapper.queryObject(id);
	}
	
	@Override
	public List<TUserTypeEntity> queryList(Map<String, Object> map){
		return tUserTypeMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tUserTypeMapper.queryTotal(map);
	}
	
	@Override
	public void save(TUserTypeEntity tUserType){
		tUserTypeMapper.save(tUserType);
	}
	
	@Override
	public void update(TUserTypeEntity tUserType){
		tUserTypeMapper.update(tUserType);
	}
	
	@Override
	public void delete(Integer id){
		tUserTypeMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tUserTypeMapper.deleteBatch(ids);
	}
	
}
