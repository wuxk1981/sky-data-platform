package com.sky.site.info.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TransferVOMapper {
    List<Map<String,Object>> selectTransferCount(Map<String,Object> map);

    List<Map<String,Object>> selectTransferPage(Map<String,Object> map);
}