/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.service.impl 
 *
 *    Filename:    BetRecordServiceImpl.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author: Wilson
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年08月31日 19:04 
 *
 *    Revision: 
 *
 *    2018/8/31 19:04 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.service.impl;

import com.sky.site.info.mapper.BetRecordVOMapper;
import com.sky.site.info.service.IBetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @ClassName BetRecordServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Wilson
 * @Date 2018年08月31日 19:04
 * @Version 1.0.0
 **/
@Service
public class BetRecordServiceImpl implements IBetRecordService {

    @Autowired
    private BetRecordVOMapper betRecordVOMapper;

    @Override
    public List<Map<String, String>> selectBetList(Map<String, Object> params) {
        //获取符合条件的记录数
        List<Map<String, String>> count = betRecordVOMapper.selectBetCount(params);
        //获取注单列表
        List<Map<String, String>> betRecordList = betRecordVOMapper.selectBetList(params);

        DecimalFormat doubleFormat=new DecimalFormat("0.00");
        //当前页面的投注小计
        String betAmount = doubleFormat.format(betRecordList.parallelStream().mapToDouble(bet -> {
            return Double.valueOf(bet.get("betAmount").toString());
        }).sum());

        //当前页面的有效投注小计
        String validBetAmount = doubleFormat.format(betRecordList.parallelStream().mapToDouble(bet -> {
            return Double.valueOf(bet.get("validBetAmount").toString());
        }).sum());

        //当前页面的派彩小计
        String payOut = doubleFormat.format(betRecordList.parallelStream().mapToDouble(bet -> {
            return Double.valueOf(bet.get("Payout").toString());
        }).sum());

        //当前页面的输赢小计
        String netAmount = doubleFormat.format(betRecordList.parallelStream().mapToDouble(bet -> {
            return Double.valueOf(bet.get("netAmount").toString());
        }).sum());

        Map<String,String> subTotal=new HashMap<>(16);
        subTotal.put("betAmount",betAmount);
        subTotal.put("validBetAmount",validBetAmount);
        subTotal.put("payOut",payOut);
        subTotal.put("netAmount",netAmount);

        Map<String, String> totalSum = betRecordVOMapper.selectBetSum(params);
        if (totalSum==null){
            totalSum=new HashMap<>(16);
            totalSum.put("payoutTotal", "0.00");
            totalSum.put("betamountTotal", "0.00");
            totalSum.put("netAmountTotal", "0.00");
            totalSum.put("validBetAmountTotal", "0.00");
        }

        betRecordList.add(0,count.get(0));
        betRecordList.add(1,subTotal);
        betRecordList.add(2,totalSum);
        return betRecordList;
    }
}