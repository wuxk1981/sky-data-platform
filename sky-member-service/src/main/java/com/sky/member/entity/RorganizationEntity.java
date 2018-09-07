package com.sky.member.entity;

//import com.baomidou.mybatisplus.annotations.TableName;
//
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * 组织机构
 * 
 * @author golden
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@Data
public class RorganizationEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//组织名
	private String name;
	//地址
	private String address;
	//编号
	private String code;
	//图标
	private String icon;
	//父级主键
	private Long pid;
	//排序
	private Integer seq;
	//创建时间
	private Date createTime;
	//平台ID(t_cagent)
	private Integer cid;

}
