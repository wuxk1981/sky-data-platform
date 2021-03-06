package com.sky.member.service;

import com.sky.member.entity.TReserveAccountEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统保留账号
 * 
 * @author tx
 * @email xxxx@gmail.com
 * @date 2018-09-05 13:52:19
 */
public interface TReserveAccountService {
	
	TReserveAccountEntity queryObject(Integer id);
	
	List<TReserveAccountEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TReserveAccountEntity tReserveAccount);
	
	void update(TReserveAccountEntity tReserveAccount);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
