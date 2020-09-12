package com.springboot.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.common.entity.SysUser;

@Mapper
public interface SysUserDAO {
	SysUser findByName(@Param(value="username") String username);

	List<SysUser> findUserInfoByUsername(@Param("username") String username);

	int addUser(@Param("sysUserpp")SysUser sysUser);

	void deleteUserByUsername(@Param("username")String username);

	List<SysUser> findUser(@Param("sysUser")SysUser sysUser);
	
	int updateByPrimaryKeySelective(@Param("sysUser")SysUser sysUser);

	

}
