package com.sky.pay.mapper;

import com.sky.pay.entity.Transfer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransferMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transfer record);

    int insertSelective(Transfer record);

    Transfer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transfer record);

    int updateByPrimaryKey(Transfer record);

    List<Transfer> findByPage(@Param("currentPage") int currentPage, @Param("pageCount") int pageCount);

    int sumTotalCount();
}