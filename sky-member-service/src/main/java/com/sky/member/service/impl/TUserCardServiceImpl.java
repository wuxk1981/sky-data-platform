package com.sky.member.service.impl;

import com.sky.member.mapper.TUserCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import com.sky.member.entity.TUserCardEntity;
import com.sky.member.service.TUserCardService;



@Service("tUserCardService")
public class TUserCardServiceImpl implements TUserCardService {
	@Autowired
	private TUserCardMapper tUserCardMapper;
	
	@Override
	public TUserCardEntity queryObject(Long id){
		return tUserCardMapper.queryObject(id);
	}
	
	@Override
	public List<TUserCardEntity> queryList(Map<String, Object> map){
		return tUserCardMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tUserCardMapper.queryTotal(map);
	}
	
	@Override
	public void save(TUserCardEntity tUserCard){
		tUserCardMapper.save(tUserCard);
	}
	
	@Override
	public void update(TUserCardEntity tUserCard){
		tUserCardMapper.update(tUserCard);
	}
	
	@Override
	public void delete(Long id){
		tUserCardMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tUserCardMapper.deleteBatch(ids);
	}


	@Override
	public List<Map<String, String>> selectUserCard(Map<String, Object> map) {
		return tUserCardMapper.selectUserCard(map);
	}
	@Override
	public void insertUserCard(Map<String, Object> map) {
		tUserCardMapper.insertUserCard(map);
	}



	@Override
	public void deleteUserCard(Map<String, Object> map) {
		tUserCardMapper.deleteUserCard(map);
	}
}
