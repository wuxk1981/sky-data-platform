/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.tset.pay 
 *
 *    Filename:    MJFPayTest.java 
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
 *    Create at:   2018年08月30日 11:18 
 *
 *    Revision: 
 *
 *    2018/8/30 11:18 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.tset.pay;

import com.sky.pay.PayServiceApplication;
import com.xiaoleilu.hutool.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 *  * @ClassName MJFPayTest
 *  * @Description 明捷付支付测试类
 *  * @Author Hardy
 *  * @Date 2018年08月30日 11:18
 *  * @Version 1.0.0
 *  
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PayServiceApplication.class)
public class MJFPayTest {

//    @Test
    public void generatorConfig(){
        JSONObject json = new JSONObject();
        json.put("wx","http://wx.mjzfpay.com:90/api/pay");
        json.put("wx_wap","http://wxwap.mjzfpay.com:90/api/pay");
        json.put("zfb","http://zfb.mjzfpay.com:90/api/pay");
        json.put("zfb_wap","http://zfbwap.mjzfpay.com:90/api/pay");
        json.put("jd","http://jd.mjzfpay.com:90/api/pay");
        json.put("jd_wap","http://jdwap.mjzfpay.com:90/api/pay");
        json.put("qq","http://qq.mjzfpay.com:90/api/pay");
        json.put("qq_wap","http://qqwap.mjzfpay.com:90/api/pay");
        json.put("union_wallet","http://union.mjzfpay.com:90/api/pay");
        JSONObject data = new JSONObject();
        data.put("merchNo","MJF201804240270");
        data.put("key","6A57D426E25B971F14865AFD0B541EDD");
        data.put("reqUrl",json);
        data.put("callBackUrl","http://txw.tx8899.com/YHH/Notify/MJFNotify.do");
        data.put("priKey","MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANe/CoNYMOhcbA26GWiZvJuMQzWdBeYXB/S1S0NL6+e3OddItAp8v9xKJeT6+zY+ZWnXlGtQH8SMd2eYjlFTj/CIma9+pIZZvVeV4IynJBp1E31fnkpSOFXfMvO11M7ZnqzSDTXwzAkA6nOgYn1uoDubronwCyjtWzIgTQbKuSiZAgMBAAECgYEAzd314i8dPBw030RdSnqohqTo3RfgsRX4+YClOKoRiSJFhzBQ3FD9ou7+CemUHMadbsnPTjc+mQaQScJAy0m5ZkLjBc4iBPJjY1EaOpIEuuEHiIHPiljmJhEffd7oZ2Cjr3llYWm/KhW27GLN+kPyME9nVZ+SVRczsKsNvzsETCECQQD7qirQ3t1FXr8NEZhv2YgMczR+GVisJu2Y8mcDOhbvwlDBppYX/Z2jzK/tjtUvH1E5dhP342oLGFb8UIN61vpzAkEA23Z5gbFHJEBgtiStGaf8MpSFfnxYvb5cGD/mE51HpSxdhFh/fowelGzpF0qdNCd10q666SgFc7JmbYPB7N5RwwJAGRJV26MoRh3vSJ0FI7d3L05W320lAVmLeB5GFvM1j8M7Cl/POiqhBs2AhVkiceRb0i6qRJraWKqfF9npj1ZVcwJAbqhFSgyuV2wSl6TUl0wpkQNscEOYp0vVufB2PUuftXwyRyosVw2l2ac0vKbFr2d6NFogKrIgVZ8NoIi//oR26wJAco7s1fBZweP3EOV+kjHQeFTq4THJ7qWl4NBT8eJCjZvPbbaJt8h3hLbUaV3/H7l0Owf/jmVLGPbn2k9swWVhWw==");
        data.put("pubKey","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdHp8CuNTy6+aqM6hn65SA0yfYoF01OvFAWLUwI4Y2OBOZH0Y+Oe9DdIw1X3Pf6ROEHhkhnKMwjoyGF44Ktb9B0vz53sXOgvUIGGPVsLDa8yACwq+yHKdp49IY5SdB2/eetJZNuR1HxzKlWrGxMHuviDazS2O9dfdQnpXb/TCGkQIDAQAB");
        System.out.println(data.toString());

    }

//    @Test
    public void sortmapTest(){
//        Map<String,String> data = new HashMap<String,String>();
//        data.put("amount", "10");
//        data.put("callBackUrl", "http://www.1tx888.com/TXY/PlatformPay/bankingNotify.do");
//        data.put("callBackViewUrl", "http://localhost/");
//        data.put("charset", "UTF-8");
//        data.put("goodsName", "充值");
//        data.put("merchNo", "MJF201804240266");
//        data.put("netwayCode", "WX");
//        data.put("orderNum", "MJFbl1201808301601211601214444");
//        data.put("randomNum", "cvCo");
//        data.put("sign", "3DBE75D344B8FB83C1C5580D923C9CC2");
//        data.put("version", "V3.0.0.0");
//        String params = MapUtil.mapToJson(data);
//        System.out.println(params);
    }

    @Test
    public void orderByValueTest07() {
        // a Map with string keys and integer values
        Map<String, String> budget = new HashMap<String, String>();
        budget.put("amount", "10");
        budget.put("callBackUrl", "http://www.1tx888.com/TXY/PlatformPay/bankingNotify.do");
        budget.put("callBackViewUrl", "http://localhost/");
        budget.put("charset", "UTF-8");
        budget.put("goodsName", "充值");
        budget.put("merchNo", "MJF201804240266");
        budget.put("netwayCode", "WX");
        budget.put("orderNum", "MJFbl1201808301601211601214444");
        budget.put("randomNum", "cvCo");
        budget.put("sign", "3DBE75D344B8FB83C1C5580D923C9CC2");
        budget.put("version", "V3.0.0.0");
        System.out.println("map before sorting: " + budget);
        // let's sort this map by values first
//        Map<String, String> sorted = budget
//                .entrySet()
//                .stream()
//                .sorted(comparingByKey())
//                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        Map<String,String> sorted =
                budget.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                        collect(toMap(e -> e.getKey(),e -> e.getValue(),(e1,e2) -> e2, LinkedHashMap::new));
        System.out.println("map after sorting by keys: " + sorted);
        // above code can be cleaned a bit by using method reference
        sorted = budget
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        // now let's sort the map in decreasing order of value
        sorted = budget
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        System.out.println("map after sorting by values in descending order: " + sorted);
    }
}
