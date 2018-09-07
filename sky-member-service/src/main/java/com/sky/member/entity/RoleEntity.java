package com.sky.member.entity;


//import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * 角色
 *
 * @author Golden
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */
@Data
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private Long id;
    //角色名
    private String name;
    //排序号
    private Integer seq;
    //简介
    private String description;
    //状态
    private Integer status;
    //
    private Integer cid;
    //角色 代码
    private String code;
    // 用户角色,关联查询时使用
    private transient Set<ResourceEntity> resourceSet = new HashSet<ResourceEntity>();

}
