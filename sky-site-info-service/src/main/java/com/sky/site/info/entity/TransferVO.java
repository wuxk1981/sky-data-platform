package com.sky.site.info.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述: 转账实体类
 *
 * @Author: TX
 * @Date: 2018/8/28 20:17 
 * @return:
 **/
@Data
public class TransferVO implements Serializable {
    private static final long serialVersionUID = 6611115781327126177L;
    private Integer id;

    private Integer uid;

    private String billno;

    private String username;

    private String tType;

    private Float tMoney;

    private Float oldMoney;

    private Float newMoney;

    private String type;

    private String tTime;

    private String ip;

    private String result;

    private String cagent;
}