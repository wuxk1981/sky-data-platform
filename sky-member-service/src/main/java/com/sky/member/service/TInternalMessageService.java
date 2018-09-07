package com.sky.member.service;

import com.sky.member.entity.TInternalMessageEntity;

import java.util.List;
import java.util.Map;

/**
 * 消息提示表
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-06 10:43:55
 */
public interface TInternalMessageService {
	
	TInternalMessageEntity queryObject(Long id);
	
	List<TInternalMessageEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TInternalMessageEntity tInternalMessage);
	
	void update(TInternalMessageEntity tInternalMessage);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<Map<String, Object>> selectMessageInfo(String uid, String id);


	void deleteMessage(String uid, String id);

	void updateMessageInfo(String uid, String id);

}
