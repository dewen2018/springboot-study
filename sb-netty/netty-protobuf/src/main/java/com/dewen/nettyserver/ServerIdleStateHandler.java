package com.dewen.nettyserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 服务端空闲检测
 * 空闲检测是每隔一段时间，检测这段时间内是否有数据读写。比如，
 * 服务端检测一段时间内，是否收到客户端发送来的数据，如果没有，就及时释放资源，关闭连接。
 */
@Slf4j
public class ServerIdleStateHandler extends IdleStateHandler {
    /**
     * 设置空闲检测时间为 30s
     */
    private static final int READER_IDLE_TIME = 30;

    public ServerIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        log.info("{} 秒内没有读取到数据,关闭连接", READER_IDLE_TIME);
        ctx.channel().close();
    }
}