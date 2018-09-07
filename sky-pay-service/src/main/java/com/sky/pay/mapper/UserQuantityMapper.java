package com.sky.pay.mapper;

import com.sky.pay.entity.UserQuantity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserQuantityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserQuantity record);

    int insertSelective(UserQuantity record);

    UserQuantity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserQuantity record);

    int updateByPrimaryKey(UserQuantity record);

    int insertSelectiveByUpdateKey(UserQuantity record);
}