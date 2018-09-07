package com.sky.member.entity;
import lombok.Data;

import java.io.Serializable;


/**
 * 角色资源
 * 
 * @author Golden
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@Data
public class RoleResourceEntity implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//角色id
	private Long roleId;
	//资源id
	private Long resourceId;

}
