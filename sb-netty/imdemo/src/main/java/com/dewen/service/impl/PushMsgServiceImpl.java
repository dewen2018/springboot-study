package com.dewen.service.impl;

import com.dewen.nettyserver.ChannelHandlerPool;
import com.dewen.service.PushMsgService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PushMsgServiceImpl implements PushMsgService {

    @Override
    public void pushMsgToOne(String userId, String msg) {
        Channel channel = ChannelHandlerPool.getChannel(userId);
        if (Objects.isNull(channel)) {
            throw new RuntimeException("未连接socket服务器");
        }

        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    @Override
    public void pushMsgToAll(String msg) {
        ChannelHandlerPool.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}