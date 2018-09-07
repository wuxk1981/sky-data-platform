/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.service 
 *
 *    Filename:    BankService.java 
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
 *    Create at:   2018年08月25日 22:13 
 *
 *    Revision: 
 *
 *    2018/8/25 22:13 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.service;

import com.sky.pay.entity.UserCard;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.PageVO;
import com.xiaoleilu.hutool.json.JSONObject;

import java.util.List;

/**
 *  * @ClassName BankService
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Hardy
 *  * @Date 2018年08月25日 22:13
 *  * @Version 1.0.0
 *  
 **/
public interface BankService {

    public PageVO findByPage(int currentPage, int pageCount)throws RPCPayException;

    public List<UserCard> getBankCardsByUid(Integer uid) throws RPCPayException;
}
