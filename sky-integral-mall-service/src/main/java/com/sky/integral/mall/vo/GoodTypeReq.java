/****************************************************************** 
 *
 * Powered By tianxia-online. 
 *
 * Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 * http://www.d-telemedia.com/ 
 *
 * Package: com.sky.integral.mall.vo 
 *
 * Filename: GoodTypeReq.java 
 *
 * Description: TODO(用一句话描述该文件做什么) 
 *
 * Copyright: Copyright (c) 2018-2020 
 *
 * Company: 天下网络科技 
 *
 * @author: Elephone
 *
 * @version: 1.0.0
 *
 * Create at: 2018年09月01日 16:18 
 *
 * Revision: 
 *
 * 2018/9/1 16:18 
 * - first revision 
 *
 *****************************************************************/
package com.sky.integral.mall.vo;

import com.sky.integral.mall.vo.base.MallReq;
import lombok.Data;

/**
 * @ClassName GoodTypeReq
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Elephone
 * @Date 2018年09月01日 16:18
 * @Version 1.0.0
 **/
@Data
public class GoodTypeReq extends MallReq {
    /**平台代理号*/
    private String cagentName;
}
