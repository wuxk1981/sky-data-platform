package com.sky.pay.controller;

import com.sky.pay.constant.ResponseConstant;
import com.sky.pay.enums.PayChannelEnum;
import com.sky.pay.exception.RPCPayException;
import com.sky.pay.po.ResultResponse;
import com.sky.pay.service.PayService;
import com.sky.pay.util.HttpServletUtil;
import com.sky.pay.vo.PayRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pay")
@Api(description = "支付相关类接口")
public class PayController {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    //注入支付依赖
    @Autowired
    private PayService payService;

    @PostMapping("/pay")
    @ApiOperation(value = "支付接口",notes = "适用于各种支付方式,如:支付宝、微信、财付通、京东、快捷、银联、网关等等")
    public ResultResponse pay(HttpServletRequest request, @RequestBody PayRequest payRequest){
        try {
            //验证数据
            Validator validator = new Validator();
            List<ConstraintViolation> valid = validator.validate(payRequest);
            if(valid != null && valid.size() > 0){
                logger.info("支付请求数据异常:"+valid.get(0).getMessage());
                return ResultResponse.error(ResponseConstant.ERROR_STATUS,ResponseConstant.ERROR_CODE,"支付请求参数异常:"+valid.get(0).getMessage());
            }
            //获取来源域名
            String url = HttpServletUtil.getUrl(request);
            //获取请求IP
            String ip = HttpServletUtil.getIp(request);
            //从缓存中获取用户信息,比如uid....
            payRequest.setUid(payRequest.getUid());
            payRequest.setIp(ip);
            payRequest.setUrl(url);
            //进入渠道 网银或者京东、微信、支付宝、H5、快捷、扫码等等
            String payChannel = payRequest.getPayChannel();
            ResultResponse response = null;
            if(PayChannelEnum.BANK.getCode().equals(payChannel)){
                //网银
                response = payService.wyPay(payRequest);
            }else{
                //微信、支付宝、京东、财付通、H5、快捷、扫码等等
                response = payService.scanPay(payRequest);
            }
            return response;
        } catch (RPCPayException e) {
            e.printStackTrace();
            logger.info("支付请求异常:"+e.getMessage());
            return ResultResponse.error(ResponseConstant.ERROR_STATUS,ResponseConstant.ERROR_CODE,e.getErrorMsg());
        }
    }
}
