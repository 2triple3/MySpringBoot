package com.springboot.dataservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.dataservice.service.RedisServiceImpl;


@Controller
public class MyController1 {
	@Autowired
    private RedisServiceImpl redisServiceImpl;

	@ResponseBody
	@RequestMapping("hello1")
	public String hello() {
		return "this is mysontroller1";
		
	} 
}
