package com.sky.add.service;


import com.sky.add.entity.City;

/**
 * @Author: wangxc
 * @GitHub: https://github.com/vector4wang
 * @CSDN: http://blog.csdn.net/qqhjqs?viewmode=contents
 * @BLOG: http://vector4wang.tk
 * @wxid: BMHJQS
 */
public interface CityService {

    City getCityById(int id);

    void saveTransaction();
}
