package com.springboot.common.service;

public interface MQReceiverService {

    public void receive(String message);

    public void receiveTopic1(String message) ;

    public void receiveTopic2(String message) ;
}
