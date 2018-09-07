package com.sky.pay.entity;

public class UserChannel {
    private Integer id;

    private Integer paymentId;

    private Integer cid;

    private Integer status;

    private String type;

    private Integer typeid;

    private String channel;

    private Float dividendRate;

    private Float codingRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Float getDividendRate() {
        return dividendRate;
    }

    public void setDividendRate(Float dividendRate) {
        this.dividendRate = dividendRate;
    }

    public Float getCodingRate() {
        return codingRate;
    }

    public void setCodingRate(Float codingRate) {
        this.codingRate = codingRate;
    }
}