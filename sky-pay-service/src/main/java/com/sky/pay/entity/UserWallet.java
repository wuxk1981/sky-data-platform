package com.sky.pay.entity;

import java.util.Date;

public class UserWallet {
    private Integer id;

    private Integer uid;

    private Double balance;

    private Double frozenBalance;

    private String type;

    private Date uptime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(Double frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
}