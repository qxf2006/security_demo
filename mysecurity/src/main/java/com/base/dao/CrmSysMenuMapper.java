package com.base.dao;

import com.base.entity.CrmSysMenu;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CrmSysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrmSysMenu record);

    CrmSysMenu selectByPrimaryKey(Integer id);

    List<CrmSysMenu> selectAll();

    int updateByPrimaryKey(CrmSysMenu record);
}