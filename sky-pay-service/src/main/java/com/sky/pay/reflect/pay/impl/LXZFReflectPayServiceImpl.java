/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    LXZFReflectPayServiceImpl.java 
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
 *    Create at:   2018年08月26日 18:44 
 *
 *    Revision: 
 *
 *    2018/8/26 18:44 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.reflect.pay.impl;

import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.PayResponse;
import com.sky.pay.reflect.pay.ReflectPayService;
import com.sky.pay.vo.PayConfigVO;

import java.util.Map;

/**
 *  * @ClassName LXZFReflectPayServiceImpl
 *  * @Description 联行支付反射支付实现类
 *  * @Author Hardy
 *  * @Date 2018年08月26日 18:44
 *  * @Version 1.0.0
 *  
 **/
public class LXZFReflectPayServiceImpl implements ReflectPayService {

    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        return null;
    }

    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        return null;
    }

    @Override
    public boolean notify(Map<String, String> data){
        return false;
    }
}
