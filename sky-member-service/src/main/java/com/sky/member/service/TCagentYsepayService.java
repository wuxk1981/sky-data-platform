package com.sky.member.service;

import com.sky.member.entity.TCagentYsepayEntity;

import java.util.List;
import java.util.Map;

/**
 * 在线支付配置
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
public interface TCagentYsepayService {
	
	TCagentYsepayEntity queryObject(Integer id);
	
	List<TCagentYsepayEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TCagentYsepayEntity tCagentYsepay);
	
	void update(TCagentYsepayEntity tCagentYsepay);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<Map<String, String>> getYsepayConfig( String username);
}
