/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.service 
 *
 *    Filename:    BetCodeService.java 
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
 *    Create at:   2018年09月05日 16:32 
 *
 *    Revision: 
 *
 *    2018/9/5 16:32 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.service;

import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.BetCodeResponse;

/**
 *  * @ClassName BetCodeService
 *  * @Description 下注(打码量)
 *  * @Author Hardy
 *  * @Date 2018年09月05日 16:32
 *  * @Version 1.0.0
 *  
 **/
public interface BetCodeService {

    public BetCodeResponse findBetCodeByUid(Integer uid) throws RPCPayException;
}
