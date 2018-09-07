package com.sky.member.service.impl;

import com.sky.member.mapper.TUserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.sky.member.entity.TUserLoginEntity;
import com.sky.member.service.TUserLoginService;



@Service("tUserLoginService")
public class TUserLoginServiceImpl implements TUserLoginService {
	@Autowired
	private TUserLoginMapper tUserLoginMapper;
	
	@Override
	public TUserLoginEntity queryObject(Integer id){
		return tUserLoginMapper.queryObject(id);
	}
	
	@Override
	public List<TUserLoginEntity> queryList(Map<String, Object> map){
		return tUserLoginMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tUserLoginMapper.queryTotal(map);
	}
	
	@Override
	public void save(TUserLoginEntity tUserLogin){
		tUserLoginMapper.save(tUserLogin);
	}
	
	@Override
	public void update(TUserLoginEntity tUserLogin){
		tUserLoginMapper.update(tUserLogin);
	}
	
	@Override
	public void delete(Integer id){
		tUserLoginMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tUserLoginMapper.deleteBatch(ids);
	}
	@Override
	public void insertLogin(Map<String, Object> map){
		tUserLoginMapper.insertLogin(map);
	}
}
