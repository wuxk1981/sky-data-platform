/****************************************************************** 
 *
 * Powered By tianxia-online. 
 *
 * Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 * http://www.d-telemedia.com/ 
 *
 * Package: com.sky.integral.mall.utils 
 *
 * Filename: MallResp.java 
 *
 * Description: 积分商城接口统一返回响应报文
 *
 * Copyright: Copyright (c) 2018-2020 
 *
 * Company: 天下网络科技 
 *
 * @author: Elephone
 *
 * @version: 1.0.0
 *
 * Create at: 2018年08月30日 14:45 
 *
 * Revision: 
 *
 * 2018/8/30 14:45 
 * - first revision 
 *
 *****************************************************************/
package com.sky.integral.mall.vo.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName MallResp
 * @Description 积分商城接口统一返回响应报文
 * @Author Elephone
 * @Date 2018年08月30日 14:45
 * @Version 1.0.0
 **/
public class MallResp implements Serializable{
    public static String RETCODE_SUCCESS = "0";
    public static String RETCODE_ERROR = "1";
    /**接口对应版本1.0*/
    private String version;
    /**接口路径*/
    private String apiPath;
    /**接口返回的时间*/
    private String respTime;
    /**响应码，0:成功 1：失败*/
    private String retCode;
    /**响应码文字说明*/
    private String retMsg;
    /**签名*/
    private String sign;

    public String getRespTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String respTime = df.format(new Date());
        return respTime;
    }

    public String getVersion() {
        return "1.0";
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

    public void setRespTime(String respTime) {
        this.respTime = respTime;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
