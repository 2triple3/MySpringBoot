package com.springboot.dataservice.service;


import com.springboot.common.service.MQReceiverService;
import com.springboot.dataservice.config.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


//@org.springframework.stereotype.Service
@com.alibaba.dubbo.config.annotation.Service
public class MQReceiverServiceImpl implements MQReceiverService {

    private static Logger log = LoggerFactory.getLogger(MQReceiverServiceImpl.class);

    @RabbitListener(queues= MQConfig.QUEUE)
    public void receive(String message){
        log.info(" default queue receive message:" + message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        log.info(" topic queue1 receive message:" + message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        log.info(" topic queue2 receive message:" + message);
    }
}
