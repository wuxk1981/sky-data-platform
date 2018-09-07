package com.sky.member.service.impl;

import com.sky.member.mapper.TJuniorProxyUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sky.member.entity.TJuniorProxyUserEntity;
import com.sky.member.service.TJuniorProxyUserService;



@Service("tJuniorProxyUserService")
public class TJuniorProxyUserServiceImpl implements TJuniorProxyUserService {
	@Autowired
	private TJuniorProxyUserMapper tJuniorProxyUserMapper;
	
	@Override
	public TJuniorProxyUserEntity queryObject(Integer id){
		return tJuniorProxyUserMapper.queryObject(id);
	}
	
	@Override
	public List<TJuniorProxyUserEntity> queryList(Map<String, Object> map){
		return tJuniorProxyUserMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tJuniorProxyUserMapper.queryTotal(map);
	}
	
	@Override
	public void save(TJuniorProxyUserEntity tJuniorProxyUser){
		tJuniorProxyUserMapper.save(tJuniorProxyUser);
	}
	
	@Override
	public void update(TJuniorProxyUserEntity tJuniorProxyUser){
		tJuniorProxyUserMapper.update(tJuniorProxyUser);
	}
	
	@Override
	public void delete(Integer id){
		tJuniorProxyUserMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tJuniorProxyUserMapper.deleteBatch(ids);
	}
	
}
