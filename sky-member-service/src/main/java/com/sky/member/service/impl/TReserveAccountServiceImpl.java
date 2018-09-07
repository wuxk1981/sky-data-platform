package com.sky.member.service.impl;

import com.sky.member.mapper.TReserveAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sky.member.entity.TReserveAccountEntity;
import com.sky.member.service.TReserveAccountService;



@Service("tReserveAccountService")
public class TReserveAccountServiceImpl implements TReserveAccountService {
	@Autowired
	private TReserveAccountMapper tReserveAccountMapper;
	
	@Override
	public TReserveAccountEntity queryObject(Integer id){
		return tReserveAccountMapper.queryObject(id);
	}
	
	@Override
	public List<TReserveAccountEntity> queryList(Map<String, Object> map){
		return tReserveAccountMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tReserveAccountMapper.queryTotal(map);
	}
	
	@Override
	public void save(TReserveAccountEntity tReserveAccount){
		tReserveAccountMapper.save(tReserveAccount);
	}
	
	@Override
	public void update(TReserveAccountEntity tReserveAccount){
		tReserveAccountMapper.update(tReserveAccount);
	}
	
	@Override
	public void delete(Integer id){
		tReserveAccountMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tReserveAccountMapper.deleteBatch(ids);
	}
	
}
