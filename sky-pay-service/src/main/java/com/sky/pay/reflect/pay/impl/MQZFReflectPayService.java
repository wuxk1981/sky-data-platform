/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay.impl 
 *
 *    Filename:    MQZFReflectPayService.java 
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
 *    Create at:   2018年09月02日 21:54 
 *
 *    Revision: 
 *
 *    2018/9/2 21:54 
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
 *  * @ClassName MQZFReflectPayService
 *  * @Description 免签支付
 *  * @Author Hardy
 *  * @Date 2018年09月02日 21:54
 *  * @Version 1.0.0
 *  
 **/
public class MQZFReflectPayService implements ReflectPayService {

    /**
     * 功能描述:
     * 网银支付
     * @Author: Hardy
     * @Date: 2018年09月02日 21:54:34
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
     * @Date: 2018年09月02日 21:54:41
     * @param config
     * @return: com.sky.pay.po.PayResponse
     **/
    @Override
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException {
        return null;
    }

    @Override
    public boolean notify(Map<String, String> data) {
        return false;
    }
}
