/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.auth.mapper 
 *
 *    Filename:    RoleResourceMapper.java 
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
 *    Create at:   2018年08月29日 17:50 
 *
 *    Revision: 
 *
 *    2018/8/29 17:50 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sky.member.entity.RoleResourceEntity;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleResourceMapper
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Golden
 * @Date 2018年08月29日 17:50
 * @Version 1.0.0
 **/
@Mapper
public interface RoleResourceMapper {
    void save(RoleResourceEntity t);

    void save(Map<String, Object> map);

    void saveBatch(List<RoleResourceEntity> list);

    void saveBatch(@Param("roleId") Long roleId, @Param("resourceIds") Long[] resourceIds);

    int update(RoleResourceEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    void deleteBatchByRolesIds(Object[] roleIds);

    void deleteBatchByResourceIds(Object[] resourceIds);

    RoleResourceEntity queryObject(@Param("id") Object id);

    List<RoleResourceEntity> queryList(Map<String, Object> map);

    List<RoleResourceEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);
}