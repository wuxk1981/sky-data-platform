package com.sky.site.info.service;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName IBetRecordService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Wilson
 * @Date 2018年08月31日 19:03
 * @Version 1.0.0
 **/
public interface IBetRecordService {
    //分页获取注单记录
    List<Map<String,String>> selectBetList(Map<String,Object> params);

}
