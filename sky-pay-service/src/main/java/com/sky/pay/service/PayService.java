/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.service 
 *
 *    Filename:    PayService.java 
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
 *    Create at:   2018年08月26日 11:33 
 *
 *    Revision: 
 *
 *    2018/8/26 11:33 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.service;

import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.ResultResponse;
import com.sky.pay.vo.PayRequest;

/**
 *  * @ClassName PayService
 *  * @Description 支付接口
 *  * @Author Hardy
 *  * @Date 2018年08月26日 11:33
 *  * @Version 1.0.0
 *  
 **/
public interface PayService {

    /**
     * 功能描述:
     * 网页支付
     * @Author: Hardy
     * @Date: 2018年08月27日 22:56:43
     * @param payRequest
     * @return: com.sky.pay.po.PayResultVO
     **/
    public ResultResponse wyPay(PayRequest payRequest) throws RPCPayException;

    /**
     * 功能描述:
     * 扫码支付
     * @Author: Hardy
     * @Date: 2018年08月27日 22:56:52
     * @param payRequest
     * @return: com.sky.pay.po.PayResultVO
     **/
    public ResultResponse scanPay(PayRequest payRequest)throws RPCPayException;
}
