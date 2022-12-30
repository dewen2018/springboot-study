package com.dewen.service;

import org.springframework.stereotype.Service;

@Service
public class GroovyTestService {

    public void test() {
        System.out.println("我是SpringBoot框架的成员类，但该方法由Groovy脚本调用");
    }

}