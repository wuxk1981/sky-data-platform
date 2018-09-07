package com.sky.site.info.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactConfigVO implements Serializable{
    private static final long serialVersionUID = -2226552143327190165L;
    private Integer id;

    private String cagent;

    private String qq;

    private String wechat;

    private String qqcode;

    private String wechatcode;

    private String customer;

    private String website;

}