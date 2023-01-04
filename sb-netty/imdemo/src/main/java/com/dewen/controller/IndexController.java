package com.dewen.controller;

import com.dewen.service.PushMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private PushMsgService service;

    @GetMapping("/send/{uid}/{msg}")
    public String send(@PathVariable String uid, @PathVariable String msg) {
        service.pushMsgToOne(uid, msg);
        return "send ok";
    }
}
