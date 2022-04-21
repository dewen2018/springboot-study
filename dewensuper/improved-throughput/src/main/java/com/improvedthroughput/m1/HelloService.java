package com.improvedthroughput.m1;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello() throws InterruptedException {
        Thread.sleep(10 * 1000L);
        return "hello dewen...";
    }
}
