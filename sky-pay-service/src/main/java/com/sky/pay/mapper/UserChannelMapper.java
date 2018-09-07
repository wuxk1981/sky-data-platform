package com.sky.pay.mapper;

import com.sky.pay.entity.UserChannel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserChannel record);

    int insertSelective(UserChannel record);

    UserChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserChannel record);

    int updateByPrimaryKey(UserChannel record);

    /**
     * 功能描述:
     * 通过支付商ID和用户分层ID获取用户渠道信息
     * @Author: Hardy
     * @Date: 2018年08月27日 17:49:15
     * @param typeId 分层ID
     * @param paymentId 支付商ID
     * @return: com.sky.pay.entity.UserChannel
     **/
    UserChannel selectByTypeIdAndPaymentId(@Param("typeId") Integer typeId,@Param("paymentId") Integer paymentId);
}