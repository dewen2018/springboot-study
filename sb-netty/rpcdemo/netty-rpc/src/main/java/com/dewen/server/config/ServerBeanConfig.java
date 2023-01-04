package com.dewen.server.config;

import com.dewen.server.NettyServer;
import com.dewen.server.ServerHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * NettyServer服务端配置类
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(NettyRpcProperties.class)
public class ServerBeanConfig {

    private final NettyRpcProperties nettyRpcProperties;

    @Autowired
    public ServerBeanConfig(NettyRpcProperties nettyRpcProperties) {
        this.nettyRpcProperties = nettyRpcProperties;
    }

    /**
     * 配置ServerHandler
     *
     * @return ServerHandler处理类
     */
    @Bean
    public ServerHandler serverHandler() {
        return new ServerHandler();
    }

    /**
     * 配置NettyServer
     *
     * @param handle ServerHandle处理类
     * @return NettyServer
     */
    @Bean
    public NettyServer nettyServer(ServerHandler handle) {
        NettyServer nettyServer = new NettyServer(handle);
//        nettyServer.start(nettyRpcProperties.getServerPort());
        return nettyServer;
    }

    /**
     * 解决SpringBoot端口无法监听问题
     */
    @Component
    static class NettyServerStart implements ApplicationRunner {
        private final NettyServer nettyServer;
        private final NettyRpcProperties properties;

        @Autowired
        NettyServerStart(NettyServer nettyServer, NettyRpcProperties properties) {
            this.nettyServer = nettyServer;
            this.properties = properties;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            log.info("===============ApplicationRunner");
            if (nettyServer != null) {
                nettyServer.start(properties.getServerPort());
            }
        }
    }
}
