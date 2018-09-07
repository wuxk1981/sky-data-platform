package com.sky.site.info.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName BetRecordVOMapper
 * @Description 投注记录
 * @Author Wilson
 * @Date 2018年08月30日 16:42
 * @Version 1.0.0
 **/
@Mapper
public interface BetRecordVOMapper {

    //分页获取注单记录
    List<Map<String,String>> selectBetList(Map<String,Object> params);

    //获取注单记录数
    List<Map<String,String>> selectBetCount(Map<String,Object> params);

    //获取当前注单总和
    Map<String,String> selectBetSum(Map<String,Object> params);
}
