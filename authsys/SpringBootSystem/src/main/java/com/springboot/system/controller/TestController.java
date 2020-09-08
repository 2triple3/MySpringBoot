package com.springboot.system.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.springboot.common.service.MQSenderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.common.service.RedisService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @Autowired
    private RedissonClient redissonClient;

    @Reference
    private RedisService redisServiceImpl;

    @Reference
    MQSenderService mqsender;

    @RequestMapping(value = "/test")
    public String test() {
        return "sss566116";
    }

    @RequestMapping("/testredis")
    public String hello() {
        redisServiceImpl.set("name667", "haha66");
        return redisServiceImpl.get("name667").toString();
    }

    @RequestMapping("/testredisson")
    public String testRedisson() {
        String lockKey = "A redisson lockKey";
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean res = lock.tryLock(10, TimeUnit.SECONDS);
            if (res) {
                System.out.println("已获得redisson锁，执行业务操作...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return  "test redisson";
    }

    @RequestMapping(value = "/testmq")
    public String testmq() throws InterruptedException {
//        for(int i=1;i<1000;i++){
//            mqsender.sendMessage("rabbitmq"+i);
//            Thread.sleep(500);
//        }
        mqsender.sendMessage("rabbitmq");
        return "rabbitmq test";
    }

    @RequestMapping(value = "/testmqtopic")
    public String testmqtopic() throws InterruptedException {

        mqsender.sendTopic("mqtopic");
        return "rabbitmq topic test";
    }

    @RequestMapping(value = "/test1")
    public void testmqtopic(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0\">\n" +
                "    <title>myvue</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"app\"></div>\n" +
                "6666666\n"+
                "  </body>\n" +
                "<script>\n"+
               "alert('haha')\n;"+
                "</script>\n"+
                "</html>\n";

        response.setContentType("text/html;charset=GBK");
        out.write(s);
        out.flush();
    }

}
