/****************************************************************** 
 *
 * Powered By tianxia-online. 
 *
 * Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 * http://www.d-telemedia.com/ 
 *
 * Package: com 
 *
 * Filename: MallTest.java 
 *
 * Description: TODO(用一句话描述该文件做什么) 
 *
 * Copyright: Copyright (c) 2018-2020 
 *
 * Company: 天下网络科技 
 *
 * @author: Elephone
 *
 * @version: 1.0.0
 *
 * Create at: 2018年09月01日 15:19 
 *
 * Revision: 
 *
 * 2018/9/1 15:19 
 * - first revision 
 *
 *****************************************************************/
package com;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @ClassName MallTest
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Elephone
 * @Date 2018年09月01日 15:19
 * @Version 1.0.0
 **/
public class MallTest {

    @Test
    public void testSelectGoodsType(){
        HashMap<String,String> m =new HashMap<String, String>();
        m.put("cagentName","BL1");

        //HttpUtils.doPost("http://127.0.0.1:8800/mall/goodsType",m);
        String s = JSON.toJSONString(m);
        byte[] bytes = null;
        try {
            bytes = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpUtils.post("http://127.0.0.1:8800/mall/goodsType",bytes,"application/json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
