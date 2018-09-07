package com.sky.member.service.impl;

import com.sky.member.mapper.TProxyUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sky.member.entity.TProxyUserEntity;
import com.sky.member.service.TProxyUserService;



@Service("tProxyUserService")
public class TProxyUserServiceImpl implements TProxyUserService {
	@Autowired
	private TProxyUserMapper tProxyUserMapper;
	
	@Override
	public TProxyUserEntity queryObject(Integer id){
		return tProxyUserMapper.queryObject(id);
	}
	
	@Override
	public List<TProxyUserEntity> queryList(Map<String, Object> map){
		return tProxyUserMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tProxyUserMapper.queryTotal(map);
	}
	
	@Override
	public void save(TProxyUserEntity tProxyUser){
		tProxyUserMapper.save(tProxyUser);
	}
	
	@Override
	public void update(TProxyUserEntity tProxyUser){
		tProxyUserMapper.update(tProxyUser);
	}
	
	@Override
	public void delete(Integer id){
		tProxyUserMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tProxyUserMapper.deleteBatch(ids);
	}
	
}
