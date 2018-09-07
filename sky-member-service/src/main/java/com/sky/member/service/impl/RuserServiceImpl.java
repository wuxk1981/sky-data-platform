package com.sky.member.service.impl;

import com.sky.member.mapper.RuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.member.entity.*;
import java.util.List;
import java.util.Map;

import com.sky.member.service.RuserService;



@Service("ruserService")
public class RuserServiceImpl implements RuserService {


	@Autowired
	private RuserMapper ruserMapper;

	@Override
	public RuserEntity queryRuserEntity(String userName,String Password){
		RuserEntity ruserEntity=ruserMapper.queryRuserEntity(userName,Password);
		return ruserEntity;
	}

	@Override
	public RuserEntity queryObject(Long id){
		return ruserMapper.queryObject(id);
	}

	@Override
	public RuserEntity queryByUsername(String username) {
		return ruserMapper.queryByUsername(username);
	}

	@Override
	public List<RuserEntity> queryList(Map<String, Object> map){
		return ruserMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ruserMapper.queryTotal(map);
	}
	
	@Override
	public void save(RuserEntity ruser){
		ruserMapper.save(ruser);
	}
	
	@Override
	public void update(RuserEntity ruser){
		ruserMapper.update(ruser);
	}
	
	@Override
	public void delete(Long id){
		ruserMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		ruserMapper.deleteBatch(ids);
	}
	
}
