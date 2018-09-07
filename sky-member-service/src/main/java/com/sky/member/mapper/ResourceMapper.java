/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.auth.mapper 
 *
 *    Filename:    ResourceMapper.java 
 *
 *    Description: TODO(用一句话描述该文件做什么) 
 *
 *    Copyright:   Copyright (c) 2018-2020 
 *
 *    Company:     天下网络科技 
 *
 *    @author: Golden
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年08月29日 17:48 
 *
 *    Revision: 
 *
 *    2018/8/29 17:48 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sky.member.entity.ResourceEntity;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName ResourceMapper
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Golden
 * @Date 2018年08月29日 17:48
 * @Version 1.0.0
 **/
@Mapper
public interface ResourceMapper {
    void save(ResourceEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<ResourceEntity> list);

    int update(ResourceEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    ResourceEntity queryObject(@Param("id") Object id);

    Set<ResourceEntity> queryResourceByUserId(@Param("userId")Long userId);

    Set<ResourceEntity> queryResourcesByRoleId(@Param("roleId") Long roleId);

    Set<ResourceEntity> queryResourceByPreResId(@Param("pId")Long pId);

    List<ResourceEntity> queryList(Map<String, Object> map);

    List<ResourceEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);
}