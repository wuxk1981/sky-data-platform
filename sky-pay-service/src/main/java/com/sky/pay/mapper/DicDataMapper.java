package com.sky.pay.mapper;

import com.sky.pay.entity.DicData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DicDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicData record);

    int insertSelective(DicData record);

    DicData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicData record);

    int updateByPrimaryKey(DicData record);
}