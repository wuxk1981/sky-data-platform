/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.controller 
 *
 *    Filename:    NotifyController.java 
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
 *    Create at:   2018年09月02日 22:08 
 *
 *    Revision: 
 *
 *    2018/9/2 22:08 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.controller;

import com.sky.common.redis.RedisService;
import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.enums.ViewEnum;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.service.NotifyService;
import com.sky.pay.util.HttpServletUtil;
import com.sky.pay.util.ParamsUtil;
import com.sky.pay.vo.NotifyVO;
import com.xiaoleilu.hutool.util.MapUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *  * @ClassName NotifyController
 *  * @Description 异步通知接口
 *  * @Author Hardy
 *  * @Date 2018年09月02日 22:08
 *  * @Version 1.0.0
 *  
 **/
@RestController
@RequestMapping("/notify")
@Api(description = "支付回调接口")
public class NotifyController {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(NotifyController.class);

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private RedisService redisService;

    /**
     * 功能描述:
     * 万通支付回调
     * @Author: Hardy
     * @Date: 2018年09月02日 22:13:14
     * @param request
     * @param response
     * @return: java.lang.String
     **/
    @PostMapping(value="/WTNotify")
    @ApiOperation(value = "万通支付回调接口",notes = "万通支付回调接口")
    public String WTNotify(HttpServletRequest request, HttpServletResponse response){
        logger.info("{WTNotify}万通支付回调开始=================START=================");
        try {
            //获取参数
            Map<String,String> data = ParamsUtil.parseJsonParams(request);
            if(MapUtil.isEmpty(data)){
                logger.info("解析回调请求参数为空!");
                return ResponseConstant.FAILD_CODE;
            }
            //获取请求ID
            String orderNo = data.get("orderid");//订单号
            //防止重复调用
            if (redisService.hasKey(orderNo)){
                logger.error("支付回调订单号[orderNo:"+orderNo+"],重复被调用!");
                return ResponseConstant.FAILD_CODE;
            }
            redisService.set(orderNo,"1");//把订单号作为key存入缓存
            String tradeNo = data.get("transid")+"";//流水号
            String tradeStatus = data.get("status");// 订单支付状态 1 未支付 2 已支付;
            String successStatus = "2";//定义第三支付成功状态,交易结果, 1:未支付; 2: 已支付
            if(StrUtil.isBlank(orderNo) || StrUtil.isBlank(tradeStatus)){
                logger.info("请求参数[orderNo]、[tradeStatus]不能为空!");
                return ResponseConstant.FAILD_CODE;
            }
            String ip = HttpServletUtil.getIp(request);
            NotifyVO notifyVo = new NotifyVO();
            notifyVo.setOrderNo(orderNo);
            notifyVo.setTradeNo(tradeNo);
            notifyVo.setTradeStatus(tradeStatus);
            notifyVo.setClazzName("WTNotify");
            notifyVo.setIp(ip);
            notifyVo.setMap(data);
            notifyVo.setSuccessStatus(successStatus);
            String result = notifyService.payNotify(notifyVo);
            if(result.equals(ResponseConstant.SUCCESS_CODE)){
                return ResponseConstant.SUCCESS_CODE;//按照第三方要求返回成功参数
            }
            return ResponseConstant.FAILD_CODE;
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("{WTNotify}万通支付回调异常:");
            return ResponseConstant.FAILD_CODE;
        }
    }
}
