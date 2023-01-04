package com.dewen.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class NettyServer {
    /**
     * bossGroup 线程组用于处理连接工作
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    /**
     * workGroup 线程组用于数据处理
     */
    private EventLoopGroup workGroup = new NioEventLoopGroup();
    @Value("${netty.port}")
    private Integer port;

    @Autowired
    private NettyServerChannelInitializer channelInitializer;

    /**
     * 启动Netty Server
     *
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        new Thread(() -> {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    // 设置NIO类型的channel
                    .channel(NioServerSocketChannel.class)
                    //使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //将小的数据包包装成更大的帧进行传送，提高网络的负载
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 设置管道
                    .childHandler(channelInitializer);
            // 服务器异步创建绑定，通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture future = null;
            try {
                future = bootstrap.bind().sync();
                if (future.isSuccess())
                    log.info(NettyServer.class + " 启动正在监听： " + future.channel().localAddress());
                // 对关闭通道进行监听
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @PreDestroy
    public void destory() throws InterruptedException {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully().sync();
        }
        if (workGroup != null) {
            workGroup.shutdownGracefully().sync();
        }
        bossGroup.shutdownGracefully().sync();
        workGroup.shutdownGracefully().sync();
        log.info("关闭Netty");
    }
}