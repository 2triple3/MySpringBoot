package com.example.mq.controller;

import com.example.mq.rabbitmq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("mq")
public class MQTestController {

    @Autowired
    MQSender sender;

    @ResponseBody
    @RequestMapping("/direct/{msg}")
    public String testMqDirect(@PathVariable("msg") String msg){
        sender.sendDirect(msg);
        return "mq_direct已发送：" + msg;
    }

    @ResponseBody
    @RequestMapping("/topic/{msg}")
    public String testMqTopic(@PathVariable("msg") String msg){
        sender.sendTopic(msg,"topic.rqrqw");
        return "mq_topic已发送：" + msg;
    }

    @ResponseBody
    @RequestMapping("/fanout/{msg}")
    public String testMqFanout(@PathVariable("msg") String msg){
        sender.sendFanout(msg);
        return "mq_fanout已发送：" + msg;
    }


    @ResponseBody
    @RequestMapping("/confirm/{msg}")
    public String testMqConfirm(@PathVariable("msg") String msg){
        sender.sendMessage("confirmTestExchange", "", msg);
        return "mq_confirm测试消息已发送：" + msg;
    }


}
