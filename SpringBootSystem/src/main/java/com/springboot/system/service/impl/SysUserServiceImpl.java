package com.springboot.system.service.impl;

/**
 * 
 */
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.alibaba.dubbo.config.annotation.Service;
import com.springboot.common.entity.SysUser;
import com.springboot.common.service.RedisService;
import com.springboot.system.dao.SysUserDAO;

import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class SysUserServiceImpl implements com.springboot.system.service.SysUserService {
	
	@Autowired
	private SysUserDAO userdao;

	@Override
	public List<SysUser> findUserInfoByUsername(String username) {
		return userdao.findUserInfoByUsername(username);
		// TODO Auto-generated method stub
	}

	@Override
	public int addUser(SysUser user) {
		return userdao.addUser(user);
	}

	@Override
	public void deleteUserByUsername(String username) {
		// TODO Auto-generated method stub
		userdao.deleteUserByUsername(username);
	}

	@Override
	public List<SysUser> findUser(SysUser userInfo) {
		// TODO Auto-generated method stub
		return userdao.findUser(userInfo);
	}
	
	@Override
	public int updateUser(SysUser user) {
		// TODO Auto-generated method stub
		return userdao.updateByPrimaryKeySelective(user);
	}


	
	
}
