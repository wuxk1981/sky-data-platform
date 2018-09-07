package com.sky.add.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private Integer age;

    private Date ctm;

    private String dictum;


}