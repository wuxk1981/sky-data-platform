package com.sky.pay.mapper;

import com.sky.pay.entity.CagentYsepay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CagentYsepayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CagentYsepay record);

    int insertSelective(CagentYsepay record);

    CagentYsepay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CagentYsepay record);

    int updateByPrimaryKey(CagentYsepay record);
}