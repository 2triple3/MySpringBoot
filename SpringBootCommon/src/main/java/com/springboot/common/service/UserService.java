package com.springboot.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.springboot.common.entity.user.UserEntity;



/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 下午3:33
 * @description Redis服务接口
 */
public interface UserService {
	public List<UserEntity> findUserInfoByUserid(String userid);
}
