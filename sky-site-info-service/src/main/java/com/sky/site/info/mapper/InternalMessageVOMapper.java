package com.sky.site.info.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InternalMessageVOMapper {

    //分页获取站内信
    List<Map<String,Object>> selectMessagePageByStatus(Map<String,Object> params);

    //获取站内信数量
    List<Map<String,Object>> selectMessageReadCount(Map<String,Object> params);
}