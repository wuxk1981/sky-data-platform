package com.sky.site.info.service;

import com.sky.site.info.entity.ContactConfigVO;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:IContactConfigService
 * @Auther: Wilson
 * @Date: 2018/8/28 19:32
 * @Version:1.0.0
 */
public interface IContactConfigService {
    ContactConfigVO selectContactByCagent(Map<String,Object> map);
}
