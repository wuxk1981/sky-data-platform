package com.sky.pay.mapper;

import com.sky.pay.entity.OnlinepayCallbackLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OnlinepayCallbackLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OnlinepayCallbackLog record);

    int insertSelective(OnlinepayCallbackLog record);

    OnlinepayCallbackLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OnlinepayCallbackLog record);

    int updateByPrimaryKey(OnlinepayCallbackLog record);
}