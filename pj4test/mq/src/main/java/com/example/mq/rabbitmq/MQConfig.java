package com.example.mq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    //direct模式队列名字即routingkey,不需要绑定交换机，采用默认交换机名字为空
    public static final String QUEUE = "queue";

    //topic
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topicExchage";

    //fanout
    public static final String FANOUT_QUEUE1 = "fanout.queue1";//未使用，用了自动删除匿名队列
    public static final String FANOUT_QUEUE2 = "fanout.queue2";//未使用，用了自动删除匿名队列
    //AnonymousQueue，它是匿名的、不耐用的、独占的、自动删除的队列
    public static final String FANOUT_EXCHANGE = "fanoutExchage";



    /**
     * direct模式队列名字即routingkey,不需要绑定交换机，采用默认交换机名字为空
     * 发送者先发送到交换机上，然后交换机作为路由再将信息发到队列，
     * */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }


    /**
     * Topic模式 交换机Exchange
     * */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE1, true);
    }
    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE2, true);
    }
    //绑定
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.key1");
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
    }


    //定义扇出（广播）交换器
    @Bean
    public FanoutExchange fanoutExchange() {return new FanoutExchange(FANOUT_EXCHANGE); }
    //定义自动删除匿名队列
    @Bean
    public Queue autoDeleteQueue0() {return new AnonymousQueue(); }
    //定义自动删除匿名队列
    @Bean
    public Queue autoDeleteQueue1() { return new AnonymousQueue(); }
    // @apiNote 把队列绑定到扇出（广播）交换器
    @Bean
    public Binding binding0(FanoutExchange fanoutExchange, Queue autoDeleteQueue0) {
        return BindingBuilder.bind(autoDeleteQueue0).to(fanoutExchange);
    }
    // @apiNote 把队列绑定到扇出（广播）交换器
    @Bean
    public Binding binding1(FanoutExchange fanoutExchange, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanoutExchange);
    }


    //confirm
    @Bean(name = "confirmTestQueue")
    public Queue confirmTestQueue() {
        return new Queue("confirm_test_queue", true, false, false);
    }
    @Bean(name = "confirmTestExchange")
    public FanoutExchange confirmTestExchange() {
        return new FanoutExchange("confirmTestExchange");
    }
    @Bean
    public Binding confirmTestFanoutExchangeAndQueue(
            @Qualifier("confirmTestExchange") FanoutExchange confirmTestExchange,
            @Qualifier("confirmTestQueue") Queue confirmTestQueue) {
        return BindingBuilder.bind(confirmTestQueue).to(confirmTestExchange);
    }


}
