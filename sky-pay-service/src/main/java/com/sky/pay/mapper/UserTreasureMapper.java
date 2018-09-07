package com.sky.pay.mapper;

import com.sky.pay.entity.UserTreasure;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTreasureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTreasure record);

    int insertSelective(UserTreasure record);

    UserTreasure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTreasure record);

    int updateByPrimaryKey(UserTreasure record);
}