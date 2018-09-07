/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.po 
 *
 *    Filename:    BetCodeResponse.java 
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
 *    Create at:   2018年09月05日 17:29 
 *
 *    Revision: 
 *
 *    2018/9/5 17:29 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.po;

import com.sky.pay.entity.UserPlatformReport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *  * @ClassName BetCodeResponse
 *  * @Description 打码量实体
 *  * @Author Hardy
 *  * @Date 2018年09月05日 17:29
 *  * @Version 1.0.0
 *  
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="用户打码量")
public class BetCodeResponse implements Serializable {

    private static final long serialVersionUID = 7415796580008500669L;

    @ApiModelProperty(value="总注单数",required = true)
    private Integer totalNoteNum;//总注单数

    @ApiModelProperty(value="总下注金额",required = true)
    private Double totalBetAmount;//总下注金额

    @ApiModelProperty(value="总有效下注额",required = true)
    private Double totalValidBetAmount;//总有效下注额

    @ApiModelProperty(value="总输赢额",required = true)
    private Double totalNetAmount;//总输赢额

    @ApiModelProperty(value="打码量列表明细",required = true)
    private List<UserPlatformReport> betcodes;//打码量列表明细
}
