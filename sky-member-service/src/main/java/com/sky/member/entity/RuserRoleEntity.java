package com.sky.member.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 用户角色
 * 
 * @author Golden
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@Data
public class RuserRoleEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//用户id
	private Long userId;
	//角色id
	private Long roleId;

}
