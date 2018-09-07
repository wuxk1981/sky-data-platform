package com.sky.site.info.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InternalMessageVO implements Serializable {
    private static final long serialVersionUID = -2033118871911927524L;
    private Integer id;

    private Integer uid;

    private String message;

    private String status;

    private Date addtime;

    private Integer adduserid;
}