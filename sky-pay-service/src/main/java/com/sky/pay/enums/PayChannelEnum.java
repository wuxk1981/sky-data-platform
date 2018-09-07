/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.pay.enums 
 *
 *    Filename:    PayChannelEnum.java 
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
 *    Create at:   2018年08月27日 15:44 
 *
 *    Revision: 
 *
 *    2018/8/27 15:44 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.pay.enums;

/**
 *  * @ClassName PayChannelEnum
 *  * @Description 支付渠道枚举类,index是配置文件种的下标位置，type是保存数据库中的支付类型
 *  * @Author Hardy
 *  * @Date 2018年08月27日 15:44
 *  * @Version 1.0.0
 *  
 **/
public enum PayChannelEnum {
    BANK(-1,1,"bank","网银"),WX(0,2,"wx","微信"),ALI(1,3,"ali","支付宝"),CFT(2,4,"cft","财付通"),YL(3,5,"yl","银联"),
    JD(4,6,"jd","京东"),KY(5,7,"kj","快捷"),WXTM(6,8,"wxtm","微信条码"),ALITM(7,9,"alitm","支付宝条码");

    private Integer index;//索引位置

    private Integer type;//类型

    private String code;//编码

    private String name;//名称

    PayChannelEnum(Integer index, Integer type, String code, String name) {
        this.index = index;
        this.type = type;
        this.code = code;
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getType() {
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
