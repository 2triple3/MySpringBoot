package com.springboot.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.common.entity.user.UserEntity;

@Mapper
public interface UserDAO {
	List<UserEntity> findUserInfoByUsername(@Param("username") String username);

	int addUser(@Param("userEntitypp")UserEntity userEntity);

	void deleteUserByUsername(@Param("username")String username);

	List<UserEntity> findUser(@Param("userEntity")UserEntity userEntity);
	
	int updateByPrimaryKeySelective(@Param("userEntity")UserEntity userEntity);

	

}
