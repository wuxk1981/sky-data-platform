/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.reflect.pay 
 *
 *    Filename:    ReflectPayService.java 
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
 *    Create at:   2018年08月26日 16:46 
 *
 *    Revision: 
 *
 *    2018/8/26 16:46 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.reflect.pay;

import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.PayResponse;
import com.sky.pay.vo.PayConfigVO;

import java.util.Map;

/**
 *  * @ClassName ReflectPayService
 *  * @Description 支付反射接口
 *  * @Author Hardy
 *  * @Date 2018年08月26日 16:46
 *  * @Version 1.0.0
 *  
 **/
public interface ReflectPayService {

    /**
     * 功能描述:
     * 网页支付
     * @Author: Hardy
     * @Date: 2018年08月26日 16:55:23
     * @param config
     * @return: java.lang.String
     **/
    public PayResponse wyPay(PayConfigVO config) throws RPCPayException;


    /**
     * 功能描述:
     * 扫码支付
     * @Author: Hardy
     * @Date: 2018年08月26日 16:55:34
     * @param config
     * @return: java.lang.String
     **/
    public PayResponse scanPay(PayConfigVO config) throws RPCPayException;

    /**
     * 功能描述:
     * 回调通知
     * @Author: Hardy
     * @Date: 2018年09月02日 22:03:39
     * @param data
     * @return: boolean
     **/
    public boolean notify(Map<String,String> data);

}
