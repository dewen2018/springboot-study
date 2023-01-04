package com.dewen.client;

import com.dewen.bean.RpcMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class NettyClient {

    private Channel channel;
    /**
     * 存放请求编号与响应对象的映射关系
     */
    private final ConcurrentMap<Channel, RpcMessage> rpcMessageConcurrentMap = new ConcurrentHashMap<>();

    public RpcMessage send(int port, final RpcMessage rpcMessage) {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
                                    .addLast(new ObjectEncoder())
                                    .addLast(new ClientHandler(rpcMessageConcurrentMap));
                        }
                    });
            final ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", port).syncUninterruptibly();
            log.info("连接服务端成功: " + channelFuture.channel().remoteAddress());
            channel = channelFuture.channel();
            channel.writeAndFlush(rpcMessage);
            log.info("发送数据成功：{}", rpcMessage);
            channel.closeFuture().syncUninterruptibly();
            return rpcMessageConcurrentMap.get(channel);
        } catch (Exception e) {
            log.error("client exception", e);
            return null;
        } finally {
            group.shutdownGracefully();
            //移除请求编号和响应对象直接的映射关系
            rpcMessageConcurrentMap.remove(channel);
        }
    }

    public void stop() {
        channel.close();
    }
}