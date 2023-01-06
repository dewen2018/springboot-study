package com.dewen.nettyserver;

import com.dewen.protobuf.MessageInfo;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<MessageInfo.Message> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接的客户端地址:{}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    /**
     * 超时处理 如果5秒没有接受客户端的心跳，就触发; 如果超过两次，则直接关闭;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            // 如果读通道处于空闲状态，说明没有接收到心跳命令
            if (IdleState.READER_IDLE.equals(event.state())) {
                System.out.println("已经5秒没有接收到客户端的信息了");
                // if (idle_count > 1) {
                //     System.out.println("关闭这个不活跃的channel");
                //     ctx.channel().close();
                // }
                // idle_count++;
            }
        } else {
            super.userEventTriggered(ctx, obj);
        }
    }

    /**
     * 业务逻辑处理
     */
    // @Override
    // public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //     log.info("收到客户端发来的心跳消息：{}", msg.toString());
    // }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageInfo.Message msg) throws Exception {
        if (msg.getCmd().equals(MessageInfo.Message.CommandType.HEARTBEAT_REQUEST)) {
            log.info("收到客户端发来的心跳消息：{}", msg.toString());
            //回应pong
            // ctx.writeAndFlush(new HeartbeatResponsePacket());
        } else if (msg.getCmd().equals(MessageInfo.Message.CommandType.NORMAL)) {
            log.info("收到客户端的业务消息：{}", msg.toString());
        }
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}