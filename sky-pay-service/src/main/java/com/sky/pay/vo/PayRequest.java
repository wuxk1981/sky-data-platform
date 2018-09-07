/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.vo 
 *
 *    Filename:    PayRequest.java 
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
 *    Create at:   2018年08月27日 16:56 
 *
 *    Revision: 
 *
 *    2018/8/27 16:56 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 *  * @ClassName PayRequest
 *  * @Description 支付请求参数
 *  * @Author Hardy
 *  * @Date 2018年08月27日 16:56
 *  * @Version 1.0.0
 *  
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRequest implements Serializable {

    private static final long serialVersionUID = 1284307568981667880L;

    private Integer uid;//用户ID

    @NotNull(message="支付金额不能为空!")
    private Double amount;//支付金额

    @NotNull(message="支付渠道不能为空")
    private String payChannel;//支付渠道 网银 bank 微信 wx 支付宝 ali 财付通(QQ钱包) cft 京东 jd 银联 yl 快捷 kj 等等

    @NotNull(message="进入渠道不能为空")
    private Integer enterChannel;//进入渠道，1 PC端 2 移动端 3 APP

    @NotNull(message="支付类型不能为空")
    private String payCode;//支付类型

    @NotNull(message="支付商ID不能为空!")
    private Integer payId;//支付商ID

    private String ip;//ip地址

    private String url;//请求URL
}
