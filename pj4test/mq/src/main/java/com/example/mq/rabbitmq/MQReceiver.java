package com.example.mq.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MQReceiver {

    private  static Logger log = LoggerFactory.getLogger(MQReceiver.class);


    @RabbitListener(queues = MQConfig.QUEUE)
    public void receiveDirect(String message){
        log.info("收到mq_direct消息："+message);
    }



    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message){
        log.info("收到mq_topicqueue1消息："+message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message){
        log.info("收到mq_topicqueue2消息："+message);
    }



    @RabbitListener(queues = "#{autoDeleteQueue0.name}")
    public void receiver0(String str) {
        System.out.println("fanout receiver0++++++++++:" + str);
    }

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receiver1(String str) {
        System.out.println("fanout receiver1++++++++++:" + str);
    }


    @RabbitListener(queues = "confirm_test_queue")
    @RabbitHandler
    public void receiverUseConfirm(String msg, Channel channel, Message message) throws IOException {

        try {
            log.info("小富收到消息：{}", msg);

            //TODO 具体业务

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        }  catch (Exception e) {

            if (message.getMessageProperties().getRedelivered()) {

                log.error("消息已重复处理失败,拒绝再次接收...");

                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {

                log.error("消息即将再次返回队列处理...");

                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }



}
