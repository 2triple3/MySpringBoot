package com.springboot.common.service;

public interface MQSenderService {
    public void sendTopic(Object message) ;
    public void sendMessage(Object message);
}
