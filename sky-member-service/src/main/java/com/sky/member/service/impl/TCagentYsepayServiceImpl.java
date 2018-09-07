package com.sky.member.service.impl;

import com.sky.member.mapper.TCagentYsepayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.sky.member.entity.TCagentYsepayEntity;
import com.sky.member.service.TCagentYsepayService;



@Service("tCagentYsepayService")
public class TCagentYsepayServiceImpl implements TCagentYsepayService {
	@Autowired
	private TCagentYsepayMapper tCagentYsepayMapper;
	
	@Override
	public TCagentYsepayEntity queryObject(Integer id){
		return tCagentYsepayMapper.queryObject(id);
	}
	
	@Override
	public List<TCagentYsepayEntity> queryList(Map<String, Object> map){
		return tCagentYsepayMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCagentYsepayMapper.queryTotal(map);
	}
	
	@Override
	public void save(TCagentYsepayEntity tCagentYsepay){
		tCagentYsepayMapper.save(tCagentYsepay);
	}
	
	@Override
	public void update(TCagentYsepayEntity tCagentYsepay){
		tCagentYsepayMapper.update(tCagentYsepay);
	}
	
	@Override
	public void delete(Integer id){
		tCagentYsepayMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tCagentYsepayMapper.deleteBatch(ids);
	}

	@Override
	public List<Map<String, String>> getYsepayConfig(String username) {
		return tCagentYsepayMapper.getYsepayConfig(username);
	}


}
