/****************************************************************** 
 *
 * Powered By tianxia-online. 
 *
 * Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 * http://www.d-telemedia.com/ 
 *
 * Package: com.sky.integral.mall.utils 
 *
 * Filename: MallReq.java 
 *
 * Description: 积分商城接口统一请求报文
 *
 * Copyright: Copyright (c) 2018-2020 
 *
 * Company: 天下网络科技 
 *
 * @author: Elephone
 *
 * @version: 1.0.0
 *
 * Create at: 2018年08月30日 15:43 
 *
 * Revision: 
 *
 * 2018/8/30 15:43 
 * - first revision 
 *
 *****************************************************************/
package com.sky.integral.mall.vo.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName MallReq
 * @Description 积分商城接口统一请求报文
 * @Author Elephone
 * @Date 2018年08月30日 15:43
 * @Version 1.0.0
 **/
public class MallReq {
    /**接口对应版本1.0*/
    private String version;
    /**接口路径*/
    private String apiPath;
    /**签名*/
    private String sign;
    /**接口请求的时间*/
    private String reqTime;

    public String getVersion() {
        return "1.0";
    }

    public String getReqTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = df.format(new Date());
        return reqTime;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
