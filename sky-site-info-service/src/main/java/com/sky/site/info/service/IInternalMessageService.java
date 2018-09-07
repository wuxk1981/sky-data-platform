package com.sky.site.info.service;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName IInternalMessageService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Wilson
 * @Date 2018年08月30日 13:59
 * @Version 1.0.0
 **/
public interface IInternalMessageService {

    //获取站内信列表
    List<Map<String,Object>> selectMessagePageByStatus(Map<String,Object> params);

    //获取站内信数量
    List<Map<String,Object>> selectMessageReadCount(Map<String,Object> params);
}
