package com.dewen.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class NettyServer {
    /**
     * bossGroup 线程组用于处理连接工作
     */
    private static EventLoopGroup bossGroup = new NioEventLoopGroup();
    /**
     * workGroup 线程组用于数据处理
     */
    private static EventLoopGroup workGroup = new NioEventLoopGroup();
    private static ServerBootstrap bootstrap = new ServerBootstrap();
    @Value("${netty.port}")
    private Integer port;

    /**
     * 启动Netty Server
     *
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() {
        new Thread(() -> {
            try {
                bootstrap.group(bossGroup, workGroup)
                        // 指定Channel
                        .channel(NioServerSocketChannel.class)
                        //使用指定的端口设置套接字地址
                        // .localAddress(new InetSocketAddress(port))
                        //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                        .option(ChannelOption.SO_BACKLOG, 1024)
                        //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                        .childOption(ChannelOption.SO_KEEPALIVE, true)
                        //将小的数据包包装成更大的帧进行传送，提高网络的负载
                        .childOption(ChannelOption.TCP_NODELAY, true)
                        .childHandler(new NettyServerHandlerInitializer());
                final ChannelFuture future = bootstrap.bind(port).sync();
                if (future.isSuccess()) {
                    log.info("启动 Netty Server:{}", port);
                }
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
                workGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        }).start();
    }

    @PreDestroy
    public void destory() throws InterruptedException {
        bossGroup.shutdownGracefully().sync();
        workGroup.shutdownGracefully().sync();
        log.info("关闭Netty");
    }
}