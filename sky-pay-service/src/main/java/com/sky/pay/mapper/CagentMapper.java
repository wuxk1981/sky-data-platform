package com.sky.pay.mapper;

import com.sky.pay.entity.Cagent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface CagentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cagent record);

    int insertSelective(Cagent record);

    Cagent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cagent record);

    int updateByPrimaryKey(Cagent record);

    //通过代理号查询平台商信息
    Cagent selectByCagent(@Param("cagent") String cagent);
}