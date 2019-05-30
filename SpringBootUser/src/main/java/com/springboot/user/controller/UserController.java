package com.springboot.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.common.entity.user.UserEntity;
import com.springboot.common.service.RedisService;


@Controller
public class UserController {
	
	@Reference
    private RedisService redisServiceImpl;
	
	@ResponseBody
	@RequestMapping("/user")
	public String getUserInfo() {
		UserEntity user = new UserEntity();
		user.setUserid("001");
		user.setUsername("zhangsan");
		
		redisServiceImpl.set("user",user);
		return redisServiceImpl.get("user").toString();
		
	} 

}
