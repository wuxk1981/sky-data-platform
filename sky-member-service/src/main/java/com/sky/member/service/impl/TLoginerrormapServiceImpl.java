package com.sky.member.service.impl;

import com.sky.member.mapper.TLoginerrormapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import com.sky.member.entity.TLoginerrormapEntity;
import com.sky.member.service.TLoginerrormapService;



@Service("tLoginerrormapService")
public class TLoginerrormapServiceImpl implements TLoginerrormapService {
	@Autowired
	private TLoginerrormapMapper tLoginerrormapMapper;
	
	@Override
	public TLoginerrormapEntity queryObject(Integer id){
		return tLoginerrormapMapper.queryObject(id);
	}

	@Override
	public List<TLoginerrormapEntity> queryList(Map<String, Object> map){
		return tLoginerrormapMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tLoginerrormapMapper.queryTotal(map);
	}
	
	@Override
	public void save(TLoginerrormapEntity tLoginerrormap){
		tLoginerrormapMapper.save(tLoginerrormap);
	}
	
	@Override
	public void update(TLoginerrormapEntity tLoginerrormap){
		tLoginerrormapMapper.update(tLoginerrormap);
	}
	
	@Override
	public void delete(Integer id){
		tLoginerrormapMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tLoginerrormapMapper.deleteBatch(ids);
	}

	@Override
	public List<Map<String, Object>> selectLoginErrorMap(String username) {
		return tLoginerrormapMapper.selectLoginErrorMap(username);
	}

	@Override
	public void deleteLoginErrorMap(String username) {
		tLoginerrormapMapper.deleteLoginErrorMap(username);
	}

	@Override
	public void insertLoginErrorMap(String username) {
		tLoginerrormapMapper.insertLoginErrorMap(username);
	}
}
