package com.sky.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sky.member.mapper.TInternalMessageMapper;
import com.sky.member.entity.TInternalMessageEntity;
import com.sky.member.service.TInternalMessageService;



@Service("tInternalMessageService")
public class TInternalMessageServiceImpl implements TInternalMessageService {
	@Autowired
	private TInternalMessageMapper tInternalMessageMapper;
	
	@Override
	public TInternalMessageEntity queryObject(Long id){
		return tInternalMessageMapper.queryObject(id);
	}
	
	@Override
	public List<TInternalMessageEntity> queryList(Map<String, Object> map){
		return tInternalMessageMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tInternalMessageMapper.queryTotal(map);
	}
	
	@Override
	public void save(TInternalMessageEntity tInternalMessage){
		tInternalMessageMapper.save(tInternalMessage);
	}
	
	@Override
	public void update(TInternalMessageEntity tInternalMessage){
		tInternalMessageMapper.update(tInternalMessage);
	}
	
	@Override
	public void delete(Long id){
		tInternalMessageMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tInternalMessageMapper.deleteBatch(ids);
	}

	@Override
	public List<Map<String, Object>> selectMessageInfo(String uid, String id) {
		return tInternalMessageMapper.selectMessageInfo(uid, id);
	}

	@Override
	public void updateMessageInfo(String uid, String id) {
		tInternalMessageMapper.updateMessageInfo(uid, id);
	}

	@Override
	public void deleteMessage(String uid, String id) {
		tInternalMessageMapper.deleteMessage(uid, id);
	}

}
