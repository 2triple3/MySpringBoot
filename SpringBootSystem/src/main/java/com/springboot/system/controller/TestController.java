package com.springboot.system.controller;

import com.springboot.common.service.MQSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.common.service.RedisService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference
   private RedisService redisServiceImpl;

    @Reference
    MQSenderService mqsender;

    @RequestMapping(value="/test")
    public String test(){
        return "sss566116";
    }

    @RequestMapping("/testredis")
    public String hello() {
        redisServiceImpl.set("name667","haha66");
        return redisServiceImpl.get("name667").toString();
    }

    @RequestMapping(value="/testmq")
    public String testmq() throws InterruptedException {

        for(int i=1;i<1000;i++){
            mqsender.sendMessage("mq"+i);
            Thread.sleep(500);
        }
        return "mqqqq";
    }


}
