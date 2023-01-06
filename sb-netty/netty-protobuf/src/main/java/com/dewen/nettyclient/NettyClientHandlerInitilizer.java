package com.dewen.nettyclient;

import com.dewen.protobuf.MessageInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyClientHandlerInitilizer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast(new IdleStateHandler(0, 10, 0))
                .addLast(new ProtobufVarint32FrameDecoder())
                // 解码
                .addLast(new ProtobufDecoder(MessageInfo.Message.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                // 编码
                .addLast(new ProtobufEncoder())
                // 心跳
                .addLast(new HeartbeatHandler())
                // handler
                .addLast(new NettyClientHandler());
    }
}
