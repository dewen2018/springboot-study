package com.dewen.nettyserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettyServerChannelInitializer extends ChannelInitializer<Channel> {
    // webSocket协议名
    static final String WEBSOCKET_PROTOCOL = "WebSocket";
    // webSocket路径
    @Value("${server.path:/ws}")
    String webSocketPath;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        log.info("initChannel...");
        // 流水线管理通道中的处理程序（Handler），用来处理业务
        ch.pipeline()
                // webSocket协议本身是基于http协议的，所以这边也要使用http编解码器
                .addLast(new HttpServerCodec())
                // 编码器
                .addLast(new ObjectEncoder())
                // .addLast(new ProtobufVarint32FrameDecoder())
                // 指定对象解码
                // .addLast(new ProtobufDecoder(MessageBase.Message.getDefaultInstance()))
                // .addLast(new ProtobufVarint32LengthFieldPrepender())
                //以块的方式来写的处理器
                .addLast(new ChunkedWriteHandler())
                .addLast(new HttpObjectAggregator(8192))
                .addLast(new IdleStateHandler(8, 10, 12))
                .addLast(new WebSocketServerProtocolHandler(webSocketPath, WEBSOCKET_PROTOCOL, true, 65536 * 10))
                .addLast(new NettyServerHandler());
    }
}