package com.springboot.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.common.entity.SysMenu;


@Mapper
public interface SysMenuDAO {

	int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
	List<SysMenu> findPage();

	List<SysMenu> findPageByName(@Param(value="name") String name);
	
	List<SysMenu> findAll();

	List<SysMenu> findByUserName(@Param(value="userName") String userName);

	List<SysMenu> findRoleMenus(@Param(value="roleId") Long roleId);

}
