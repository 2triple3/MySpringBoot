package com.example.mq.rabbitmq;

import com.example.mq.serviceimpl.ConfirmCallbackService;
import com.example.mq.serviceimpl.ReturnCallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MQSender {

    private  static Logger logger = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;
    //源码中会发现rabbitTemplate实现自amqpTemplate接口，使用起来并无区别，需引入spring-boot-starter-amqp依赖。
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ConfirmCallbackService confirmCallbackService;
    @Autowired
    private ReturnCallbackService returnCallbackService;

    @Autowired
    private FanoutExchange fanoutExchange;

    public void sendDirect(Object message){
        String msg = (String) message;
        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);//direct模式队列名字即routingkey,不需要绑定交换机，采用默认交换机名字为空
        logger.info("发送mq_direct消息："+message);
    }

    public void sendTopic(String msg,String routingKey) {
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,routingKey, msg);
        logger.info("发送mq_topic消息："+msg+"---routingKey:"+routingKey);
    }

    //FanoutExchange 消息广播的模式，不管路由键或者是路由模式，会把消息发给绑定给它的全部队列，如果配置了routing_key会被忽略。
    public void sendFanout(String msg) {
        amqpTemplate.convertAndSend(fanoutExchange.getName(), "", msg);//中间的routing_key参数会被忽略
        logger.info("发送mq_fanout消息："+msg);
    }



    //confirmTestExchange
    public void sendMessage(String exchange, String routingKey, Object msg) {
        /**
         * 确保消息发送失败后可以重新返回到队列中
         * 注意：yml需要配置 publisher-returns: true
         */
        //配置文件里面已设置
        //rabbitTemplate.setMandatory(true);

        /**
         * 消费者确认收到消息后，手动ack回执回调处理
         */
        rabbitTemplate.setConfirmCallback(confirmCallbackService);

        /**
         * 消息投递到队列失败回调处理
         */
        rabbitTemplate.setReturnCallback(returnCallbackService);

        /**
         * 发送消息
         */
        rabbitTemplate.convertAndSend(exchange, routingKey, msg,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));
    }

}
