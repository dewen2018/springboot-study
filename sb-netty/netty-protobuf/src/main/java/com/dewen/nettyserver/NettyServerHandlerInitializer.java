package com.dewen.nettyserver;

import com.dewen.protobuf.MessageInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class NettyServerHandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                // 默认空闲检测，入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
                // .addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))
                // 空闲检测
                .addLast(new ServerIdleStateHandler())
                // 解码和编码，应和客户端一致
                // 传输的协议 Protobuf
                .addLast(new ProtobufVarint32FrameDecoder())
                // 指定对象解码
                .addLast(new ProtobufDecoder(MessageInfo.Message.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                // protobuf 编码器
                .addLast(new ProtobufEncoder())
                //业务逻辑实现类
                .addLast(new NettyServerHandler());
    }
}