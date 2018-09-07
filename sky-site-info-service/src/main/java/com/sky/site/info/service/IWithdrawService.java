package com.sky.site.info.service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:IWithdrawService
 * @Auther: Wilson
 * @Date: 2018/8/28 10:20
 * @Version:1.0.0
 */
public interface IWithdrawService {
    List<Map<String,Object>> selectWithDrawPage(Map<String,Object> map);
}
