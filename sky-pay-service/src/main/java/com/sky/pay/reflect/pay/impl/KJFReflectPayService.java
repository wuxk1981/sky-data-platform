/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    KJFReflectPayService.java 
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
 *    Create at:   2018年09月02日 21:49 
 *
 *    Revision: 
 *
 *    2018/9/2 21:49 
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
 *  * @ClassName KJFReflectPayService
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Hardy
 *  * @Date 2018年09月02日 21:49
 *  * @Version 1.0.0
 *  
 **/
public class KJFReflectPayService implements ReflectPayService {

    /**
     * 功能描述:
     * 网银支付
     * @Author: Hardy
     * @Date: 2018年09月02日 21:50:00
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException {
        return null;
    }

    /**
     * 功能描述:
     * 扫码支付
     * @Author: Hardy
     * @Date: 2018年09月02日 21:50:07
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        return null;
    }

    @Override
    public boolean notify(Map<String, String> data){
        return false;
    }
}
