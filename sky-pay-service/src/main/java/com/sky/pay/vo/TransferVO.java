/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.vo 
 *
 *    Filename:    TransferVO.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author:     Hardy 
 *
 *    @version:    1.0.0 
 *
 *    Create at:   2018年09月05日 20:44 
 *
 *    Revision: 
 *
 *    2018/9/5 20:44 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *  * @ClassName TransferVO
 *  * @Description 转账VO实体类
 *  * @Author Hardy
 *  * @Date 2018年09月05日 20:44
 *  * @Version 1.0.0
 *  
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="转账VO类")
public class TransferVO implements Serializable {

    private static final long serialVersionUID = 2419408096521986468L;

    @ApiModelProperty(value="主键ID",required = true)
    private Integer id;

    @ApiModelProperty(value="用户ID",required = true)
    private Integer uid;

    @ApiModelProperty(value="订单号",required = true)
    private String billno;

    @ApiModelProperty(value="用户名称",required = true)
    private String username;

    @ApiModelProperty(value="转入转出",required = true)
    private String tType;

    @ApiModelProperty(value="转账金额",required = true)
    private Float tMoney;

    @ApiModelProperty(value="转账前金额",required = true)
    private Float oldMoney;

    @ApiModelProperty(value="转账后金额",required = true)
    private Float newMoney;

    @ApiModelProperty(value="状态1:待处理",required = true)
    private String status;

    @ApiModelProperty(value="转账平台",required = true)
    private String type;

    @ApiModelProperty(value="转账时间",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tTime;

    @ApiModelProperty(value="转账IP",required = true)
    private String ip;

    @ApiModelProperty(value="转账结果",required = true)
    private String result;

    @ApiModelProperty(value="主键ID",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uptime;
}
