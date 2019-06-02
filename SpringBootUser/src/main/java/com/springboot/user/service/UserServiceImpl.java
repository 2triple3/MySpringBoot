package com.springboot.user.service;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 下午3:15
 * @description
 */
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.alibaba.dubbo.config.annotation.Service;
import com.springboot.common.entity.user.UserEntity;
import com.springboot.common.service.RedisService;
import com.springboot.common.service.UserService;
import com.springboot.user.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userdao;

	@Override
	public List<UserEntity> findUserInfoByUserid(String userid) {
		return userdao.findUserInfoByUserid(userid);
		// TODO Auto-generated method stub
	}

	@Override
	public void addUser(UserEntity user) {
		// TODO Auto-generated method stub
		userdao.addUser(user);
	}
	
	
}
