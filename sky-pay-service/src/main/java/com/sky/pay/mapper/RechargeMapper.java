package com.sky.pay.mapper;

import com.sky.pay.entity.Recharge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述:
 * 订单dao
 * @Author: Hardy
 * @Date: 2018年08月26日 21:05:02
 **/
@Mapper
public interface RechargeMapper {

    int deleteByPrimaryKey(Integer rId);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Integer rId);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);

    Recharge selectByOrderNo(@Param("orderNo") String orderNo);

    int updateByOrderNo(Recharge record);
}