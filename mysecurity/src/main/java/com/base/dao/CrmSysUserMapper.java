package com.base.dao;

import com.base.entity.CrmSysUser;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CrmSysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrmSysUser record);

    CrmSysUser selectByPrimaryKey(Integer id);

    List<CrmSysUser> selectAll();

    int updateByPrimaryKey(CrmSysUser record);
    
    CrmSysUser selectByUsername(String username);
    
}