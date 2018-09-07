package com.sky.pay.mapper;

import com.sky.pay.entity.TransferFaild;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransferFaildMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TransferFaild record);

    int insertSelective(TransferFaild record);

    TransferFaild selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransferFaild record);

    int updateByPrimaryKey(TransferFaild record);

    List<TransferFaild> findByPage(@Param("currentPage") int currentPage, @Param("pageCount") int pageCount);

    int sumTotalCount();
}