package com.sky.site.info.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WebComConfigVO implements Serializable {
    private static final long serialVersionUID = -4666089390624034767L;
    private Integer id;

    private String type;

    private String name;

    private String title;

    private Integer weight;

    private String status;

    private String img1;

    private String src1;

    private String img2;

    private String rmk;

    private String updatetime;

    private String userid;

    private String img3;

}