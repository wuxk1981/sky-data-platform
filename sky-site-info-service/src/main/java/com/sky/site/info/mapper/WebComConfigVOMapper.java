package com.sky.site.info.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WebComConfigVOMapper {

    //获取轮播图
    List<Map<String,Object>> selectCarouselFigureByCagent(Map<String,Object> params);

    //获取优惠活动
    List<Map<String,Object>> selectDiscountInfoByCagent(Map<String,Object> params);

    //获取手机端图片
    List<Map<String,Object>> selectMobileWebComConfigByCagent(Map<String,Object> params);

}