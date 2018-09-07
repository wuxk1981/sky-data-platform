package com.sky.pay.mapper;

import com.sky.pay.entity.UserCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCard record);

    int insertSelective(UserCard record);

    UserCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCard record);

    int updateByPrimaryKey(UserCard record);

    List<UserCard> findByPage(@Param("currentPage") Integer currentPage,@Param("pageCount") Integer pageCount);

    int sumTotalCount();

    List<UserCard> getBankCardsByUid(@Param("uid") Integer uid);
}