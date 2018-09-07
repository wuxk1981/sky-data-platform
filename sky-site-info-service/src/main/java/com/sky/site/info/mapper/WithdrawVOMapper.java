package com.sky.site.info.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WithdrawVOMapper {
    List<Map<String,Object>> selectWithDrawCount(Map<String,Object> map);

    List<Map<String,Object>> selectWithDrawPage(Map<String,Object> map);
}