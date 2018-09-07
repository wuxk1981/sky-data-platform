package com.sky.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sky.member.mapper.TRefererUrlMapper;
import com.sky.member.entity.TRefererUrlEntity;
import com.sky.member.service.TRefererUrlService;



@Service("tRefererUrlService")
public class TRefererUrlServiceImpl implements TRefererUrlService {
	@Autowired
	private TRefererUrlMapper tRefererUrlMapper;
	
	@Override
	public TRefererUrlEntity queryObject(Integer id){
		return tRefererUrlMapper.queryObject(id);
	}
	
	@Override
	public List<TRefererUrlEntity> queryList(Map<String, Object> map){
		return tRefererUrlMapper.queryList(map);
	}
	@Override
	public List<Map<String, String>> selectRefererUrl(String domain, String cagent) {

		return tRefererUrlMapper.selectRefererUrl(domain, cagent);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tRefererUrlMapper.queryTotal(map);
	}
	
	@Override
	public void save(TRefererUrlEntity tRefererUrl){
		tRefererUrlMapper.save(tRefererUrl);
	}
	
	@Override
	public void update(TRefererUrlEntity tRefererUrl){
		tRefererUrlMapper.update(tRefererUrl);
	}
	
	@Override
	public void delete(Integer id){
		tRefererUrlMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tRefererUrlMapper.deleteBatch(ids);
	}
	
}
