package com.dewen.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    public static String CHANNEL = "dewen-1";

    private RedisTemplate<String, String> redisTemplate;

    public RedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 发送消息
     *
     * @param message
     */
    @GetMapping("/publish")
    public void publish(@RequestParam String message) {
        // 发送消息
        redisTemplate.convertAndSend(CHANNEL, message);
    }
}