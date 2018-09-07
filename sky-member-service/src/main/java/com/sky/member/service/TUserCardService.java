package com.sky.member.service;

import com.sky.member.entity.TUserCardEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员银行卡信息
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 21:34:09
 */
public interface TUserCardService {
	
	TUserCardEntity queryObject(Long id);
	
	List<TUserCardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserCardEntity tUserCard);
	
	void update(TUserCardEntity tUserCard);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<Map<String, String>> selectUserCard(Map<String, Object> map);
	void insertUserCard(Map<String, Object> map);
	void deleteUserCard(Map<String, Object> map);


}
