package com.base.dao;

import com.base.entity.CrmSysRole;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CrmSysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrmSysRole record);

    CrmSysRole selectByPrimaryKey(Integer id);

    List<CrmSysRole> selectAll();

    int updateByPrimaryKey(CrmSysRole record);
}