package com.sky.member.entity;

//import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * 资源
 * 
 * @author Golden
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@Data
public class ResourceEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//资源名称
	private String name;
	//资源路径
	private String url;
	//打开方式 ajax,iframe
	private String openMode;
	//资源介绍
	private String description;
	//资源图标
	private String icon;
	//父级资源id
	private Long pid;
	//排序
	private Integer seq;
	//状态
	private Integer status;
	//打开状态
	private Integer opened;
	//资源类别
	private Integer resourceType;
	//创建时间
	private Date createTime;


}
