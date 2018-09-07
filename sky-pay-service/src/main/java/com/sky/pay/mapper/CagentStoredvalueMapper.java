package com.sky.pay.mapper;

import com.sky.pay.entity.CagentStoredvalue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CagentStoredvalueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CagentStoredvalue record);

    int insertSelective(CagentStoredvalue record);

    CagentStoredvalue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CagentStoredvalue record);

    int updateByPrimaryKey(CagentStoredvalue record);

    CagentStoredvalue selectByCid(@Param("cid") Integer cid);
}