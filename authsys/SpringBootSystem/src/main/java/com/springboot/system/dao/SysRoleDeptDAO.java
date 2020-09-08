package com.springboot.system.dao;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.common.entity.SysRoleDept;

@Mapper
public interface SysRoleDeptDAO {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleDept record);

    int insertSelective(SysRoleDept record);

    SysRoleDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleDept record);

    int updateByPrimaryKey(SysRoleDept record);
}