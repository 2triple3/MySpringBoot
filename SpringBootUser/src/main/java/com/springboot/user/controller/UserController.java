package com.springboot.user.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.common.entity.user.UserEntity;
import com.springboot.common.service.RedisService;
import com.springboot.common.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userserviceImpl;
	
	@Reference
    private RedisService redisServiceImpl;
	
	@ResponseBody
	@RequestMapping("/finduser")
	public String getUserInfoByUserid() {
		List<UserEntity> userlist = userserviceImpl.findUserInfoByUserid("002");
		
		String s ="";
		Iterator it = userlist.iterator();
		while(it.hasNext()) {
		  s =s+  it.next().toString();
		}
		
		return s;
		
	} 
	
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
