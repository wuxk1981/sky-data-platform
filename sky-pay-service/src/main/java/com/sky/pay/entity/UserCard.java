package com.sky.pay.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="用户银行卡实体类")
public class UserCard implements Serializable {
    private static final long serialVersionUID = -421395170116778153L;

    @ApiModelProperty(value = "主键ID",required = true)
    private Integer id;

    @ApiModelProperty(value = "用户ID",required = true)
    private Integer uid;

    @ApiModelProperty(value = "开卡人姓名",required = true)
    private String cardUsername;

    @ApiModelProperty(value = "银行名称",required = true)
    private Integer bankId;

    @ApiModelProperty(value = "卡号",required = true)
    private String cardNum;

    @ApiModelProperty(value = "开户行",required = true)
    private String cardAddress;

    @ApiModelProperty(value = "绑卡时间",required = true)
    private Date addTime;

    @ApiModelProperty(value = "",required = true)
    private String isDelete;

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

    public String getCardUsername() {
        return cardUsername;
    }

    public void setCardUsername(String cardUsername) {
        this.cardUsername = cardUsername == null ? null : cardUsername.trim();
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getCardAddress() {
        return cardAddress;
    }

    public void setCardAddress(String cardAddress) {
        this.cardAddress = cardAddress == null ? null : cardAddress.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}