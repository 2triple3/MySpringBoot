package com.springboot.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.common.service.RedisService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference
   private RedisService redisServiceImpl;

    @RequestMapping("/hello")
    public String hello() {
        redisServiceImpl.set("name667","haha66");
        return redisServiceImpl.get("name667").toString();

    }

    @RequestMapping(value="/test")
    public String test(){
        return "sss566116";
    }
}
