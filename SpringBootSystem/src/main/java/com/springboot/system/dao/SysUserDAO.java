package com.springboot.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.common.entity.SysUser;

@Mapper
public interface SysUserDAO {
	List<SysUser> findUserInfoByUsername(@Param("username") String username);

	int addUser(@Param("userEntitypp")SysUser sysUserEntity);

	void deleteUserByUsername(@Param("username")String username);

	List<SysUser> findUser(@Param("userEntity")SysUser sysUserEntity);
	
	int updateByPrimaryKeySelective(@Param("userEntity")SysUser sysUserEntity);

	

}
