/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.common.entity 
 *
 *    Filename:    TokenEntity.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author: Golden
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年09月04日 14:04 
 *
 *    Revision: 
 *
 *    2018/9/4 14:04 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.member.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TokenEntity
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Golden
 * @Date 2018年09月04日 14:04
 * @Version 1.0.0
 **/
@Data
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private  String access_token;
    private String token_type;
    private String refresh_token;
    private Long expires_in;
    private String scope;
    private String jti;
}