package com.sky.data.process.mapper;

import com.sky.data.process.entity.BGVideoRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BGVideoRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BGVideoRecord record);

    int insertSelective(BGVideoRecord record);

    BGVideoRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BGVideoRecord record);

    int updateByPrimaryKey(BGVideoRecord record);
}