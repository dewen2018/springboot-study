package com.dewen.client.config;

import com.dewen.client.NettyClient;
import com.dewen.client.NettyClientBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientBeanConfig {

    @Bean
    public NettyClient nettyClient() {
        return new NettyClient();
    }

    @Bean
    public NettyClientBeanPostProcessor nettyClientBeanPostProcessor(NettyClient nettyClient) {
        return new NettyClientBeanPostProcessor(nettyClient);
    }
}