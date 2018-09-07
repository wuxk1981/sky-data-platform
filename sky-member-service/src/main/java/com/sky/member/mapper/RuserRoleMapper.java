/****************************************************************** 
 *
 *    Powered By tianxia-online. 
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络 
 *    http://www.d-telemedia.com/ 
 *
 *    Package:     com.sky.auth.mapper 
 *
 *    Filename:    RuserRoleMapper.java 
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
 *    Create at:   2018年08月29日 17:51 
 *
 *    Revision: 
 *
 *    2018/8/29 17:51 
 *        - first revision 
 *
 *****************************************************************/
package com.sky.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sky.member.entity.RuserRoleEntity;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RuserRoleMapper
 * @Description TODO(这里用一句话描述这个类的作用)
 * @Author Golden
 * @Date 2018年08月29日 17:51
 * @Version 1.0.0
 **/
@Mapper
public interface RuserRoleMapper {

    void deleteBatchByUserIds(Long[] ids);
    void deleteBatchByRolesIds(Long[] rolesIds);

    void save(RuserRoleEntity t);

    void save(Map<String, Object> map);

    void saveBatch(@Param("userId") Long userId, @Param("rolesIds") Long[] rolesIds);

   // void saveBatch(List<RuserRoleEntity> list);

    int update(RuserRoleEntity t);

    int update(Map<String, Object> map);

    int delete(@Param("id") Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(@Param("id") Object[] id);

    RuserRoleEntity queryObject(@Param("id") Object id);

    List<RuserRoleEntity> queryList(Map<String, Object> map);

    List<RuserRoleEntity> queryList(@Param("id") Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();

    int delData(Map<String, Object> map);

}