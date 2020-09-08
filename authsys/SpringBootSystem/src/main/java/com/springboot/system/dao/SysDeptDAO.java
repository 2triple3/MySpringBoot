package com.springboot.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.common.entity.SysDept;


@Mapper
public interface SysDeptDAO {
    int deleteByPrimaryKey(Long id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);
    
    List<SysDept> findPage();
    
    List<SysDept> findAll();
}