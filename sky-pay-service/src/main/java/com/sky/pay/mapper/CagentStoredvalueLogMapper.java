package com.sky.pay.mapper;

import com.sky.pay.entity.CagentStoredvalueLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CagentStoredvalueLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CagentStoredvalueLog record);

    int insertSelective(CagentStoredvalueLog record);

    CagentStoredvalueLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CagentStoredvalueLog record);

    int updateByPrimaryKey(CagentStoredvalueLog record);
}