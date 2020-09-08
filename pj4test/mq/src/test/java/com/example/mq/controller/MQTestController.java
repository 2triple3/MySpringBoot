package com.example.mq.controller;

import com.example.mq.rabbitmq.MQSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.example.mq.controller.MQTestController.class)
public class MQTestController {

//    @Autowired
////    MQSender sender;


    @Test
    public void testMqDirect(){
        System.out.println("dadasdad");
    }
}
