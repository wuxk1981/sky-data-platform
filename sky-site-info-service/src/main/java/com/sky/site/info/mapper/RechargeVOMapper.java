package com.sky.site.info.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RechargeVOMapper {
    List<Map<String,Object>> selectByStatusPage(Map<String, Object> map);

    List<Map<String,Object>> selectReChargeCount(Map<String, Object> map);
}