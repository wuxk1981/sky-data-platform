package com.sky.site.info.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
public class WithdrawVO implements Serializable {
    private static final long serialVersionUID = 5119503532935599431L;
    private Integer id;

    private Integer cid;

    private Integer uid;

    private String billno;

    private Float amount;

    private String status;

    private String addTime;

    private String username;

    private String bankname;

    private String cardno;

    private Integer totaltimes;

    private Integer todaytimes;

    private Float poundage;

    private Float administrativeFee;

    private Float amountPaid;

    private Integer vuid;

    private String vtime;

    private String rmk;

    private String remark;

    private Double markingQuantity;

    private Double userQuantity;

    private String issuedName;

    private String issuedTime;

    private String issuedPayerId;

    private Integer issuedStatus;

    private String payerResult;

    private String issuedIp;

    private String address;

    private String cagent;

    private String phoneno;

    private String zjno;
}