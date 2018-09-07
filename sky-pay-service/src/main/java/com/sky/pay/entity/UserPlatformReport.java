package com.sky.pay.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="打码量实体类")
public class UserPlatformReport implements Serializable {
    private static final long serialVersionUID = 1696446936031170932L;

    @ApiModelProperty(value="打码量主键ID",required = true)
    private Integer id;

    @ApiModelProperty(value="用户ID",required = true)
    private Integer uid;

    @ApiModelProperty(value="游戏平台类型",required = true)
    private String platformType;

    @ApiModelProperty(value="金额类型",required = true)
    private String currency;

    @ApiModelProperty(value="总注单数",required = true)
    private Integer noteNum;

    @ApiModelProperty(value="总下注额",required = true)
    private Double betAmount;

    @ApiModelProperty(value="总有效下注额",required = true)
    private Double validBetAmount;

    @ApiModelProperty(value="总输赢额",required = true)
    private Double netAmount;

    @ApiModelProperty(value="最后一单的时间",required = true)
    private Date betTime;

    @ApiModelProperty(value="代理号",required = true)
    private String cagent;

    @ApiModelProperty(value="游戏平台名称",required = true)
    private String platName;

    @ApiModelProperty(value="游戏平台ID",required = true)
    private Integer platId;

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

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType == null ? null : platformType.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Integer getNoteNum() {
        return noteNum;
    }

    public void setNoteNum(Integer noteNum) {
        this.noteNum = noteNum;
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

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Date getBetTime() {
        return betTime;
    }

    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }

    public String getCagent() {
        return cagent;
    }

    public void setCagent(String cagent) {
        this.cagent = cagent == null ? null : cagent.trim();
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public Integer getPlatId() {
        return platId;
    }

    public void setPlatId(Integer platId) {
        this.platId = platId;
    }
}