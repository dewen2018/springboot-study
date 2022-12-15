package com.dewen.controlelr;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dewen.service.impl.ConsumerService;
import com.dewen.service.impl.ProducerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MessageController {
//    private Logger logger = Logger.getLogger(MessageController.class);
//
//    @Resource(name = "demoQueueDestination")
//    private Destination destination;
//
//
//    //队列消息生产者
//    @Resource(name = "producerService")
//    private ProducerService producer;
//
//
//    //队列消息消费者
//    @Resource(name = "consumerService")
//    private ConsumerService consumer;
//
//
//    @RequestMapping(value = "/SendMessage", method = RequestMethod.POST)
//    @ResponseBody
//    public void send(HttpServletRequest request, HttpServletResponse response,@RequestBody String msg) {
//        logger.info(Thread.currentThread().getName()+"------------send to jms Start");
//        producer.sendMessage(msg);
//        logger.info(Thread.currentThread().getName()+"------------send to jms End");
//    }
//
//
//    @RequestMapping(value= "/ReceiveMessage",method = RequestMethod.GET)
//    @ResponseBody
//    public Object receive(){
//        logger.info(Thread.currentThread().getName()+"------------receive from jms Start");
//        TextMessage tm = consumer.receive(destination);
//        logger.info(Thread.currentThread().getName()+"------------receive from jms End");
//        return tm;
//    }
}