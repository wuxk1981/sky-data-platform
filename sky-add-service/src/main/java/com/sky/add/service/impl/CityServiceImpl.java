package com.sky.add.service.impl;

import com.sky.add.entity.City;
import com.sky.add.entity.Country;
import com.sky.add.mapper.CityMapper;
import com.sky.add.mapper.CountryMapper;
import com.sky.add.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Author: wangxc
 * @GitHub: https://github.com/vector4wang
 * @CSDN: http://blog.csdn.net/qqhjqs?viewmode=contents
 * @BLOG: http://vector4wang.tk
 * @wxid: BMHJQS
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public City getCityById(int id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class) // 需要回滚的地方加入注解
    @Override
    public void saveTransaction() {
        City city = new City();
        city.setCountrycode("HRV");
        city.setDistrict("20");
        city.setName("this is test");
        cityMapper.insertSelective(city);

        Country country = new Country();
        country.setName("this is test");


        UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        // checking UUID value
        System.out.println("UUID value is: "+uid);

        country.setCode(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d").toString());

        countryMapper.insertSelective(country);
    }
}
