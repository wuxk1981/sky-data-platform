/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.service 
 *
 *    Filename:    NotifyService.java 
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
 *    Create at:   2018年09月02日 22:14 
 *
 *    Revision: 
 *
 *    2018/9/2 22:14 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.service;

import com.sky.pay.exception.RPCPayException;
import com.sky.pay.vo.NotifyVO;

/**
 *  * @ClassName NotifyService
 *  * @Description 回调接口
 *  * @Author Hardy
 *  * @Date 2018年09月02日 22:14
 *  * @Version 1.0.0
 *  
 **/
public interface NotifyService {

    /**
     * 功能描述:
     * 支付回调
     * @Author: Hardy
     * @Date: 2018年09月02日 22:15:28
     * @param
     * @return: void
     **/
    public String payNotify(NotifyVO notifyVO)throws RPCPayException;

}
