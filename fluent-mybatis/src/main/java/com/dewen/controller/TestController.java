//package com.dewen.controller;
//
//import com.dewen.entity.HelloWorldEntity;
//import com.dewen.mapper.HelloWorldMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class TestController {
//    @Autowired
//    private HelloWorldMapper helloWorldMapper;
//
//    @RequestMapping("insert")
//    public void insert() {
//        HelloWorldEntity helloWorldEntity = new HelloWorldEntity();
//        helloWorldEntity.setSayHello("hi dj");
//        helloWorldMapper.insert(helloWorldEntity);
//    }
//}
