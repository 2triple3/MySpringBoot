package com.springboot.dataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.dataservice.service.RedisServiceImpl;

@Controller
public class MyController {
	
	@Autowired
    private RedisServiceImpl redisServiceImpl;

	@ResponseBody
	@RequestMapping("hello")
	public String hello() {
		redisServiceImpl.set("name667","haha");
		return redisServiceImpl.get("name667").toString();
		
	} 
}
