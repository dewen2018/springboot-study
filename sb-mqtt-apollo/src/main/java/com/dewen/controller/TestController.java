package com.dewen.controller;

import com.dewen.config.MqttPushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private MqttPushClient mqttPushClient;

    @GetMapping(value = "/publishTopic")
    public String publishTopic() {
        String topicString = "home/garden/fountain";
        mqttPushClient.publish(0, false, topicString, "测试一下发布消息");
        return "ok";
    }

    // 发送自定义消息内容（使用默认主题）
    @RequestMapping("/publishTopic/{data}")
    public String test1(@PathVariable("data") String data) {
        String topicString = "home/garden/fountain";
        mqttPushClient.publish(0, false, topicString, data);
        return "ok";
    }

    // 发送自定义消息内容，且指定主题
    @RequestMapping("/publishTopic/{topic}/{data}")
    public String test2(@PathVariable("topic") String topic, @PathVariable("data") String data) {
        mqttPushClient.publish(0, false, topic, data);
        return "ok";
    }
}