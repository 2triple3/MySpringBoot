package com.springboot.dataservice.service;


import com.springboot.common.service.MQSenderService;
import com.springboot.dataservice.config.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@org.springframework.stereotype.Service
@com.alibaba.dubbo.config.annotation.Service
public class MQSenderServiceImpl implements MQSenderService{

    private static Logger log = LoggerFactory.getLogger(MQSenderServiceImpl.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendTopic(Object message) {
		//String msg = RedisService.beanToString(message);
        String msg = (String) message;
        log.info("send topic mq message:"+msg);
		amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg+"1");
		amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg+"2");
	}

	public void sendMessage(Object message){
        //String msg = RedisService.beanToString(message);
        String msg = (String) message;
        log.info("send default mq message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);

    }
}
