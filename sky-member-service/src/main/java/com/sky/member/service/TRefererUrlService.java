package com.sky.member.service;

import com.sky.member.entity.TRefererUrlEntity;

import java.util.List;
import java.util.Map;

/**
 * 域名白名单
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 09:22:01
 */
public interface TRefererUrlService {
	
	TRefererUrlEntity queryObject(Integer id);
	
	List<TRefererUrlEntity> queryList(Map<String, Object> map);

	List<Map<String, String>> selectRefererUrl(String domain, String cagent);

	
	int queryTotal(Map<String, Object> map);
	
	void save(TRefererUrlEntity tRefererUrl);
	
	void update(TRefererUrlEntity tRefererUrl);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
