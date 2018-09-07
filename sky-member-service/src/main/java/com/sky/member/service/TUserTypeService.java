package com.sky.member.service;

import com.sky.member.entity.TUserTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员分层
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-04 16:26:37
 */
public interface TUserTypeService {
	
	TUserTypeEntity queryObject(Integer id);
	
	List<TUserTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserTypeEntity tUserType);
	
	void update(TUserTypeEntity tUserType);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
