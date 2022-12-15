package com.dewen.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

//@RestController
//public class Producer {
//    @Autowired
//    private JmsMessagingTemplate jmsTemplate;

//    @Autowired
//    private Queue queue;
//
//    @Autowired
//    private Topic topic;
//
//    //发送queue类型消息
//    @GetMapping("/queue")
//    public void sendQueueMsg(String msg){
//        jmsTemplate.convertAndSend(queue, msg);
//    }
//
//    //发送topic类型消息
//    @GetMapping("/topic")
//    public void sendTopicMsg(String msg){
//        jmsTemplate.convertAndSend(topic, msg);
//    }

//}