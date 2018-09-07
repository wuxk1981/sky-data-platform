package com.sky.integral.mall.entity;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsCat {
    private Integer id;

    private Integer cid;

    private Integer pid;

    private String catename;

    private String path;

    private Date uptime;

    private Integer upuid;

    private String rmk;

}