/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.tset.dao 
 *
 *    Filename:    RechageTest.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Hardy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年08月26日 21:08 
 *
 *    Revision: 
 *
 *    2018/8/26 21:08 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.tset.db;

import com.sky.pay.PayServiceApplication;
import com.sky.pay.entity.Recharge;
import com.sky.pay.mapper.RechargeMapper;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *  * @ClassName RechageTest
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Hardy
 *  * @Date 2018年08月26日 21:08
 *  * @Version 1.0.0
 *  
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PayServiceApplication.class)
public class RechageTest {

    @Autowired
    private RechargeMapper rechargeMapper;

//    @Test
    public void insertTest(){
        Recharge recharge = new Recharge();
        recharge.setUid(RandomUtil.randomInt(4));
        recharge.setPayType((byte)1);
        recharge.setBankCode("1012");
        recharge.setOrderNo(RandomUtil.randomString(41411122));
        recharge.setOrderAmount(10.00f);
        recharge.setOrderTime(DateUtil.date());
        recharge.setTradeStatus("1");
        recharge.setTradeNo(RandomUtil.randomNumbers(11));
        recharge.setIp("127.0.0.0:8900");
        recharge.setFinishTime(DateUtil.date());
        recharge.setMerchant("WT");
        recharge.setUpuid(RandomUtil.randomInt(4));
        recharge.setCagent(RandomUtil.randomString(4));
        try {
            int success = rechargeMapper.insertSelective(recharge);
            if(success>0){
                System.out.println("成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }

//    @Test
    public void selectTest(){
        Recharge recharge = rechargeMapper.selectByPrimaryKey(3280);
        if(recharge != null){
            System.out.println(JSONUtil.toJsonStr(recharge));
        }
    }
}
