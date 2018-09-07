/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.enums 
 *
 *    Filename:    ViewEnum.java 
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
 *    Create at:   2018年08月29日 14:46 
 *
 *    Revision: 
 *
 *    2018/8/29 14:46 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.enums;

/**
 *  * @ClassName ViewEnum
 *  * @Description 视图枚举类，支付成功后根据不同的渠道显示不同的视图效果
 *  * @Author Hardy
 *  * @Date 2018年08月29日 14:46
 *  * @Version 1.0.0
 *  
 **/
public enum ViewEnum {
    FORM(1,"form","页面表单"),QRCODE(2,"qrcode","二维码生成图片"),QRURL(3,"qrurl","二维码跳转链接"),LINK(4,"link","浏览器跳转链接");

    private int type;

    private String code;

    private String name;

    ViewEnum(int type, String code, String name) {
        this.type = type;
        this.code = code;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
