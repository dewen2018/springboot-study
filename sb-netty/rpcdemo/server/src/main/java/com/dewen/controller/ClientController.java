package com.dewen.controller;

import com.dewen.anno.RpcServer;
import com.dewen.service.Test1Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @RpcServer
    private Test1Api testServiceImpl;

    @GetMapping("/test1")
    public void test() {
        testServiceImpl.test();
    }

    @GetMapping("/test2")
    public void test(int id, String name) {
        testServiceImpl.test(id, name);
    }

    @GetMapping("/test3")
    public String testStr(int id) {
        return testServiceImpl.testStr(id);
    }

    @GetMapping("/test4")
    public Object testObj() {
        return testServiceImpl.testObj();
    }
}