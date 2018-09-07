/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.enums 
 *
 *    Filename:    EnterChannelEnum.java 
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
 *    Create at:   2018年08月27日 14:40 
 *
 *    Revision: 
 *
 *    2018/8/27 14:40 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.enums;

/**
 *  * @ClassName EnterChannelEnum
 *  * @Description 进入渠道
 *  * @Author Hardy
 *  * @Date 2018年08月27日 14:40
 *  * @Version 1.0.0
 *  
 **/
public enum EnterChannelEnum {

    PC(1,"pc","PC客户端"),MOBILE(2,"mobile","移动客户端"),APP(3,"app","app客户端");

    private Integer type;//类型

    private String code;//编码

    private String name;//名称

    EnterChannelEnum(Integer type, String code, String name) {
        this.type = type;
        this.code = code;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(Integer type) {
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
