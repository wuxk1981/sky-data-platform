package com.sky.site.info.service;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName IWebComConfigService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author TX
 * @Date 2018年08月30日 10:59
 * @Version 1.0.0
 **/
public interface IWebComConfigService {
    //获取轮播图
    List<Map<String,Object>> selectCarouselFigureByCagent(Map<String,Object> params);

    //获取优惠活动
    List<Map<String,Object>> selectDiscountInfoByCagent(Map<String,Object> params);

    //获取手机端图片
    List<Map<String,Object>> selectMobileWebComConfigByCagent(Map<String,Object> params);
}
