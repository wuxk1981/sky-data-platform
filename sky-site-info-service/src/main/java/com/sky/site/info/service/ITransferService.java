package com.sky.site.info.service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:ITransferService
 * @Auther: Wilson
 * @Date: 2018/8/28 10:36
 * @Version:1.0.0
 */
public interface ITransferService {
    List<Map<String,Object>> selectTransferPage(Map<String,Object> map);
}
