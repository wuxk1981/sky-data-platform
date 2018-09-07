/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.vo 
 *
 *    Filename:    NotifyVO.java 
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
 *    Create at:   2018年09月02日 22:48 
 *
 *    Revision: 
 *
 *    2018/9/2 22:48 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 *  * @ClassName NotifyVO
 *  * @Description 通知回调VO类
 *  * @Author Hardy
 *  * @Date 2018年09月02日 22:48
 *  * @Version 1.0.0
 *  
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifyVO implements Serializable {

    private static final long serialVersionUID = 7993994377623615482L;

    private Integer uid;//用户ID

    private Double amount;//订单金额

    private Double dividendRate;//彩金倍率

    private Double codingRate;//打码量倍率

    private String orderNo;//订单号

    private String tradeNo;//流水号

    private String tradeStatus;//第三方订单状态

    private String successStatus;//定义第三支付成功状态

    private String ip;//请求ID

    private String clazzName;//支付商回调类名

    private Map<String,String> map;//第三方传递过来的回调参数
}
