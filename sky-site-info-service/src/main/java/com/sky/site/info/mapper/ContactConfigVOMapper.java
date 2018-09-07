package com.sky.site.info.mapper;

import com.sky.site.info.entity.ContactConfigVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContactConfigVOMapper {
    ContactConfigVO selectContactByCagent(Map<String,Object> map);
}