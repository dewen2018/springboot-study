package com.dewen.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// @Component
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyRpcProperties {
    private int serverPort;
}