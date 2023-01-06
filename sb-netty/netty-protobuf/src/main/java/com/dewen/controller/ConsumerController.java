package com.dewen.controller;

import com.dewen.nettyclient.NettyClient;
import com.dewen.protobuf.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ConsumerController {
    @Autowired
    private NettyClient nettyClient;

    @GetMapping("/send")
    public String send() {
        MessageInfo.Message message = MessageInfo.Message.newBuilder()
                .setCmd(MessageInfo.Message.CommandType.NORMAL)
                .setContent("hello server")
                .setName("dewen")
                .setRequestId(UUID.randomUUID().toString()).build();
        nettyClient.sendMsg(message);
        return "send ok";
    }
}
