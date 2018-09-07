package com.sky.site.info.service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:IUserTreasureService
 * @Auther: Wilson
 * @Date: 2018/8/28 10:44
 * @Version:1.0.0
 */
public interface IUserTreasureService {

    int selectUserTreasurePageCount(Map<String,Object> map);

    List<Map<String,Object>> selectUserTreasurePage(Map<String,Object> map);
}
