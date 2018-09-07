package com.sky.pay.mapper;

import com.sky.pay.entity.UserPlatformReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPlatformReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPlatformReport record);

    int insertSelective(UserPlatformReport record);

    UserPlatformReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPlatformReport record);

    int updateByPrimaryKey(UserPlatformReport record);

    List<UserPlatformReport> findBetCodeByUid(@Param("uid") Integer uid);

    UserPlatformReport sumBetCodeByUid(@Param("uid") Integer uid);

}