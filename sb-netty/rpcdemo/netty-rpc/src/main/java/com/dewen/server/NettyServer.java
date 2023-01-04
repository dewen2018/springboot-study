package com.dewen.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class NettyServer {

    /**
     * server端处理器
     */
    private final ServerHandler serverHandler;
    /**
     * 服务端通道
     */
    private Channel channel;

    /**
     * 构造器
     *
     * @param serverHandler server处理器
     */
    public NettyServer(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    /**
     * 启动
     *
     * @param port 启动端口
     */
    public void start(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
                                    .addLast(new ObjectEncoder())
                                    .addLast(serverHandler);
                        }
                    })
                    //设置队列大小
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 两小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            final ChannelFuture channelFuture = serverBootstrap.bind(port).syncUninterruptibly();
            log.info("服务端启动-端口: {}", port);
            channel = channelFuture.channel();
            channel.closeFuture().syncUninterruptibly();
        } catch (Exception e) {
            log.error("服务器开启失败", e);
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        } finally {
            //关闭主线程组
            bossGroup.shutdownGracefully();
            //关闭工作线程组
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 关闭当前通道
     */
    public void stop() {
        channel.close();
    }
}