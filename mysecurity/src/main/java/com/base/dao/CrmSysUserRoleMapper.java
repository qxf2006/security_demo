package com.base.dao;

import com.base.entity.CrmSysUserRole;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CrmSysUserRoleMapper {
    int deleteByPrimaryKey(@Param("userid") Integer userid, @Param("roleid") Integer roleid);

    int insert(CrmSysUserRole record);

    List<CrmSysUserRole> selectAll();
    
    List<CrmSysUserRole> selectByUserId(Integer userId);
}