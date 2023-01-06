package com.dewen.nettyclient;

import com.dewen.protobuf.MessageInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class NettyClient {
    @Value("${netty.port}")
    private int port;
    @Value("${netty.host}")
    private String host;
    private EventLoopGroup group = new NioEventLoopGroup();

    private SocketChannel socketChannel;

    public void sendMsg(MessageInfo.Message message) {
        socketChannel.writeAndFlush(message);
    }

    @PostConstruct
    public void start() {
        new Thread(() -> {
            ChannelFuture future = null;

            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.SO_KEEPALIVE, true)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .remoteAddress(host, port)
                        .handler(new NettyClientHandlerInitilizer());
                // future = bootstrap.connect(host, port);
                future = bootstrap.connect()
                        //客户端断线重连逻辑：第一种情况实现
                        .addListener((ChannelFutureListener) futureListener -> {
                            if (futureListener.isSuccess()) {
                                log.info("连接Netty服务端成功");
                            } else {
                                log.info("连接失败，进行断线重连");
                                futureListener.channel().eventLoop().schedule(() -> start(), 20, TimeUnit.SECONDS);
                            }
                        });
                socketChannel = (SocketChannel) future.channel();
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
