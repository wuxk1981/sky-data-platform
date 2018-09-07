package com.sky.site.info.service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 获取记录接口
 * @ClassName:IRechargeInfoService
 * @Auther: Wilson
 * @Date: 2018/8/27 14:36
 * @Version:1.0.0
 */
public interface IRechargeInfoService {
    List<Map<String,Object>> selectByStatusPage(Map<String, Object> map);
}
