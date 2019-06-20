package com.springboot.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.common.entity.user.UserEntity;

@Mapper
public interface UserDAO {
	List<UserEntity> findUserInfoByUsername(@Param("username") String username);

	void addUser(@Param("userEntitypp")UserEntity userEntity);

}
