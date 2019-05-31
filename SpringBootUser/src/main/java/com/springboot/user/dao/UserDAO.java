package com.springboot.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.common.entity.user.UserEntity;

@Mapper
public interface UserDAO {
	List<UserEntity> findUserInfoByUserid(@Param("userid") String userid);

	
	
}
