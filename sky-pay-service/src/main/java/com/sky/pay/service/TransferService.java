/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.service 
 *
 *    Filename:    TransferService.java 
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
 *    Create at:   2018年08月26日 11:36 
 *
 *    Revision: 
 *
 *    2018/8/26 11:36 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.service;

import com.sky.pay.entity.Transfer;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.PageVO;

import java.util.List;

/**
 *  * @ClassName TransferService
 *  * @Description TODO(这里用一句话描述这个类的作用)
 *  * @Author Hardy
 *  * @Date 2018年08月26日 11:36
 *  * @Version 1.0.0
 *  
 **/
public interface TransferService {

    public PageVO findTransferByPage(Integer type, Integer currentPage, Integer pageCount) throws RPCPayException;
}
