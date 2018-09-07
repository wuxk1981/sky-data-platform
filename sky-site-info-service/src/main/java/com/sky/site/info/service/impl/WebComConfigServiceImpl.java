/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.site.info.service.impl 
 *
 *    Filename:    WebComConfigServiceImpl.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author: Wilson
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年08月30日 10:59 
 *
 *    Revision: 
 *
 *    2018/8/30 10:59 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.site.info.service.impl;

import com.sky.site.info.mapper.WebComConfigVOMapper;
import com.sky.site.info.service.IWebComConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName:WebComConfigServiceImpl
 * @Auther: Wilson
 * @Date: 2018/8/28 10:44
 * @Version:1.0.0
 */
@Service
public class WebComConfigServiceImpl implements IWebComConfigService {

    @Autowired
    private WebComConfigVOMapper webComConfigVOMapper;

    @Override
    public List<Map<String, Object>> selectCarouselFigureByCagent(Map<String, Object> params) {
        return webComConfigVOMapper.selectCarouselFigureByCagent(params);
    }

    @Override
    public List<Map<String, Object>> selectDiscountInfoByCagent(Map<String, Object> params) {
        return webComConfigVOMapper.selectDiscountInfoByCagent(params);
    }

    @Override
    public List<Map<String, Object>> selectMobileWebComConfigByCagent(Map<String, Object> params) {
        return webComConfigVOMapper.selectMobileWebComConfigByCagent(params);
    }
}