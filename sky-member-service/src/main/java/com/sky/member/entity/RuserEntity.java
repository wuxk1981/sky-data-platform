package com.sky.member.entity;

//import com.baomidou.mybatisplus.annotations.TableId;
//import com.baomidou.mybatisplus.annotations.TableName;
//import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 用户
 *
 * @author Goldn
 * @email xxxx@gmail.com
 * @date 2018-08-29 09:06:15
 */

@Data
public class RuserEntity  implements Serializable{
    private static final long serialVersionUID = 1L;

    //主键id

    private Long id;
    //登陆名
    private String loginName;
    //用户名
    private String name;
    //密码
    private String password;
    //密码加密盐
    private String salt;
    //性别
    private Integer sex;
    //年龄
    private Integer age;
    //手机号
    private String phone;
    //用户类别
    private Integer userType;
    //用户状态
    private Integer status;
    //所属机构
    private Integer organizationId;
    //创建时间
    private Date createTime;
    //
    private Integer cid;
    //
    private Integer type;
    // 用户角色,关联查询时使用
    private transient Set<RoleEntity> roleSet = new HashSet<RoleEntity>();

}
