package com.dewen.activemq.controller;

import com.dewen.activemq.service.Producer;
import com.dewen.activemq.service.bsk2.IMessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    private static final String MQ_KQXX="mq_kqxx";

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @RequestMapping("/test")
    public void sendMessage(){
        for (int i = 0; i < 10; i++) {
            jmsTemplate.convertAndSend(MQ_KQXX,MQ_KQXX+i);
        }
    }
//    @Resource
//    private IMessageProducerService messageProducer;
//
//    @RequestMapping(value="/chufabaojing",method= RequestMethod.GET)
//    public String chufabaojing(String devicename){
//
//        List<String> alarmStrList = new ArrayList<>();
//        alarmStrList.add(devicename+"out fence01");
//        alarmStrList.add(devicename+"out fence02");
//        alarmStrList.add(devicename+"in fence01");
//        alarmStrList.add(devicename+"in fence02");
//
//        System.out.println("设备"+devicename+"出围栏报警");
//        // 报警信息写入数据库
//        System.out.println("报警数据写入数据库。。。");
//
//        // 写入消息队列
//        for (String alarmStr : alarmStrList) {
//            this.messageProducer.sendMessage("study - " + alarmStr);
//        }
//
//        // 消息写进消息队列里就不管了
//
//        // 下面两步骤移到activemq消费者里
//        // 发送邮件
//        // 发送短信
//
//        return "success";
//    }
}