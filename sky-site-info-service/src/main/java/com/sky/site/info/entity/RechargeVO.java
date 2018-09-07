package com.sky.site.info.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
public class RechargeVO implements Serializable {
    private static final long serialVersionUID = 5991540407572201684L;
    private Integer rId;

    private Integer uid;

    private Byte payType;

    private String bankCode;

    private String orderNo;

    private Float orderAmount;

    private String orderTime;

    private String tradeStatus;

    private String tradeNo;

    private String ip;

    private String finishTime;

    private String merchant;

    private Integer upuid;

    private Integer payId;

    private String cagent;
}