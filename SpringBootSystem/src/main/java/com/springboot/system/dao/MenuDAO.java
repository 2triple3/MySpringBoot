package com.springboot.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.common.entity.MenuEntity;
import com.springboot.common.entity.UserEntity;

@Mapper
public interface MenuDAO {

	int deleteByPrimaryKey(Long id);

    int insert(MenuEntity record);

    int insertSelective(MenuEntity record);

    MenuEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuEntity record);

    int updateByPrimaryKey(MenuEntity record);
    
	List<MenuEntity> findPage();

	List<MenuEntity> findPageByName(@Param(value="name") String name);
	
	List<MenuEntity> findAll();

	List<MenuEntity> findByUserName(@Param(value="userName") String userName);

	List<MenuEntity> findRoleMenus(@Param(value="roleId") Long roleId);

}
