package com.sky.site.info.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserTreasureVO implements Serializable {
    private static final long serialVersionUID = 2575668178823365482L;
    private Integer id;

    private Integer uid;

    private Float amount;

    private Float oldMoney;

    private Float newMoney;

    private String number;

    private String tType;

    private String type;

    private String addtime;

    private Integer platformType;

    private String sType;

    private String rmk;

    private Integer operatorId;

    private String cagent;

    private Integer isFirst;

    private String ip;
}