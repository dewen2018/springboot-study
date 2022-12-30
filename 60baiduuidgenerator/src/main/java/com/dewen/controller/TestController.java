package com.dewen.controller;

import com.dewen.service.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class TestController {

    @Autowired
    private UidGenerator uidGenerator;

    @GetMapping("/test")
    public long insert(){
        long uid = uidGenerator.getUID();
        System.out.println("我是生成的uid:"+uid);
        System.out.println("我是parseUid:"+uidGenerator.parseUID(uid));
        return uid;
    }
}