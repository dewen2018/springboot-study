package com.dewen.nettyclient;

import com.dewen.protobuf.MessageInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<MessageInfo.Message> {


    /**
     * 如果服务端发生消息给客户端，下面方法进行接收消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageInfo.Message msg) throws Exception {
        log.info("客户端收到消息：{}", msg.toString());
        // 如果不是protobuf类型的数据
        if (!(msg instanceof MessageInfo.Message)) {
            System.out.println("未知数据!" + msg);
            return;
        }
        try {
            // 得到protobuf的数据，进行相应的业务处理。。。
            MessageInfo.Message.Builder message = MessageInfo.Message.newBuilder()
                    .setCmd(MessageInfo.Message.CommandType.HEARTBEAT_REQUEST);
            ctx.writeAndFlush(message);
            System.out.println("成功发送给服务端!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
