package com.dewen.nettyclient;

import com.dewen.protobuf.MessageInfo;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 客户端如何实现心跳机制
 */
@Component
@Slf4j
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {
    // private final NettyClient nettyClient;
    // @Autowired
    // public HeartbeatHandler(NettyClient nettyClient) {
    //     this.nettyClient = nettyClient;
    // }
    @Autowired
    private NettyClient nettyClient;

    /**
     * 心跳请求处理 每4秒发送一次心跳请求;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleState.WRITER_IDLE.equals(event.state())) {
                // 如果写通道处于空闲状态,就发送心跳命令
                MessageInfo.Message heartbeat = MessageInfo.Message.newBuilder()
                        .setCmd(MessageInfo.Message.CommandType.HEARTBEAT_REQUEST)
                        .setRequestId(UUID.randomUUID().toString())
                        .setContent("heartbeat").build();
                //发送心跳消息，并在发送失败时关闭该连接
                ctx.channel().writeAndFlush(heartbeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 建立连接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("建立连接时：" + new Date());
        ctx.fireChannelActive();
    }

    /**
     * 关闭连接时
     * 客户端断线重连逻辑：第二种情况是运行过程中 服务端突然挂掉了
     * 实现心跳机制的 Handler中重写channelInactive方法，然后在该方法中执行重试逻辑，
     * 这里注入了 NettyClient类，目的是方便调用 NettyClient的start()方法重新连接服务端
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //如果运行过程中服务端挂了,执行重连机制
        EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> nettyClient.start(), 10L, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    /**
     * 处理异常, 一般将实现异常处理逻辑的Handler放在ChannelPipeline的最后
     * 这样确保所有入站消息都总是被处理，无论它们发生在什么位置，下面只是简单的关闭Channel并打印异常信息
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("捕获的异常：{}", cause.getMessage());
        ctx.channel().close();
    }
}
