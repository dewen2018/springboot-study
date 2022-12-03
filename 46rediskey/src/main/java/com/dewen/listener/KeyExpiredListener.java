package com.dewen.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * key 过期监听
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //  过期的key
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        String[] split = body.split(":");
        System.out.println("Redis key 过期监听 => " + body);
        System.out.println("key 所属功能 => " + split[0]);
        System.out.println("key 所属分类 => " + split[1]);
        System.out.println("key 值 => " + split[2]);
    }
}
