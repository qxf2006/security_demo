package com.base.dao;

import com.base.entity.CrmSysRoleMenu;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CrmSysRoleMenuMapper {
    int deleteByPrimaryKey(@Param("roleid") Integer roleid, @Param("menuid") Integer menuid);

    int insert(CrmSysRoleMenu record);

    List<CrmSysRoleMenu> selectAll();
    
    List<CrmSysRoleMenu> selectByRoleId(Integer roleid);
}