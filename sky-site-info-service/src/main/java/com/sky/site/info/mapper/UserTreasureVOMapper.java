package com.sky.site.info.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserTreasureVOMapper {
    int selectUserTreasurePageCount(Map<String,Object> map);

    List<Map<String,Object>> selectUserTreasurePage(Map<String,Object> map);
}