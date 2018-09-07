/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.entity 
 *
 *    Filename:    BetRecordVO.java 
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
 *    Create at:   2018年08月30日 16:20 
 *
 *    Revision: 
 *
 *    2018/8/30 16:20 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BetRecordVO
 * @Description 映射投注记录表
 * @Author Wilson
 * @Date 2018年08月30日 16:20
 * @Version 1.0.0
 **/
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BetRecordVO implements Serializable {
    private static final long serialVersionUID = -5883228260236031700L;

    private String betTime;//投注时间

    private String type;//游戏缩写

    private Double betAmount;//投注

    private Double validBetAmount;//有效投注

    private Double payOut;//派彩

    private Double netAmount;//输赢

    public String getBetTime() {
        return betTime;
    }

    public void setBetTime(String betTime) {
        this.betTime = betTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(Double betAmount) {
        this.betAmount = betAmount;
    }

    public Double getValidBetAmount() {
        return validBetAmount;
    }

    public void setValidBetAmount(Double validBetAmount) {
        this.validBetAmount = validBetAmount;
    }

    public Double getPayOut() {
        return payOut;
    }

    public void setPayOut(Double payOut) {
        this.payOut = payOut;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }
}