package com.sky.pay.mapper;

import com.sky.pay.entity.UserWallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserWalletMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWallet record);

    int insertSelective(UserWallet record);

    UserWallet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWallet record);

    int updateByPrimaryKey(UserWallet record);

    //查询用户余额
    double selectBalanceByUid(@Param("uid") Integer uid);

    //通过用户ID和类型查询用户钱包
    UserWallet selectByUidAndType(@Param("uid") Integer uid,@Param("type") String type);
}