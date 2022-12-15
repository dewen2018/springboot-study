package com.dewen.controller;

import com.dewen.utils.redis.RedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    RedisClientTemplate redisClientTemplate;

    @GetMapping(value = "/testSet")
    public Object testSet(){
        redisClientTemplate.setToRedis("Frank","Frank测试redis");
        System.out.println(redisClientTemplate.getRedis("Frank"));
        return redisClientTemplate.getRedis("Frank");
    }

}