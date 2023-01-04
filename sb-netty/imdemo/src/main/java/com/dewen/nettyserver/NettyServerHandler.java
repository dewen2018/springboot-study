package com.dewen.nettyserver;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 一旦连接，第一个被执行
     */
    // @Override
    // public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    //     log.info("有新的客户端链接：[{}]", ctx.channel().id().asLongText());
    //     // 添加到channelGroup 通道组
    //     MyChannelHandlerPool.channelGroup.add(ctx.channel());
    // }

    /**
     * 通道就绪事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端建立连接，通道开启！");

        //添加到channelGroup通道组
        ChannelHandlerPool.getChannelGroup().add(ctx.channel());
    }

    /**
     * 通道未就绪事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端断开连接，通道关闭！");
        //添加到channelGroup 通道组
        ChannelHandlerPool.getChannelGroup().remove(ctx.channel());
    }

    /**
     * 读就绪事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    // @Override
    // public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //     //首次连接是FullHttpRequest
    //     if (null != msg && msg instanceof FullHttpRequest) {
    //         FullHttpRequest request = (FullHttpRequest) msg;
    //         String uri = request.uri();
    //         Map paramMap = getUrlParams(uri);
    //         log.info("接收到的参数是：" + JSON.toJSONString(paramMap));
    //         //如果url包含参数，需要处理
    //         if (uri.contains("?")) {
    //             String newUri = uri.substring(0, uri.indexOf("?"));
    //             log.info(newUri);
    //             request.setUri(newUri);
    //         }
    //     } else if (msg instanceof TextWebSocketFrame) {
    //         //正常的TEXT消息类型
    //         TextWebSocketFrame frame = (TextWebSocketFrame) msg;
    //         log.info("客户端收到服务器数据：" + frame.text());
    //         sendAllMessage(frame.text());
    //     }
    //     super.channelRead(ctx, msg);
    // }

    /**
     * 读取数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // if (msg.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)) {
        //     log.info("收到客户端发来的心跳消息：{}", msg.toString());
        //     //回应pong
        //     ctx.writeAndFlush(new HeartbeatResponsePacket());
        // } else if (msg.getCmd().equals(MessageBase.Message.CommandType.NORMAL)) {
        //     log.info("收到客户端的业务消息：{}",msg.toString());
        // }
        log.info("服务器收到消息：{}", msg.text());
        // Channel channel = ctx.channel();
        // for (Channel channel1 : channelList) {
        //     if (channel != channel1) {
        //         channel1.writeAndFlush(new TextWebSocketFrame(msg));
        //     }
        // }
        // 获取用户ID,关联channel
        // JSONObject jsonObject = JSON.parseObject(msg.text());
        // String uid = jsonObject.getString("uid");
        // ChannelHandlerPool.getChannelMap().put(uid, ctx.channel());

        // // 将用户ID作为自定义属性加入到channel中，方便随时channel中获取用户ID
        // AttributeKey<String> key = AttributeKey.valueOf("userId");
        // ctx.channel().attr(key).setIfAbsent(uid);
        // 回复消息
        // ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器收到消息啦"));
        // ctx.channel().writeAndFlush(new TextWebSocketFrame(msg.text()));
        sendAllMessage(msg.text());
    }

    // @Override
    // public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    //     log.info("用户下线了:{}", ctx.channel().id().asLongText());
    //     // 删除通道
    //     ChannelHandlerPool.getChannelGroup().remove(ctx.channel());
    //     removeUserId(ctx);
    // }

    /**
     * 异常处理事件
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("异常：{}", cause.getMessage());
        //移除集合
        ChannelHandlerPool.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
        ctx.close();
    }

    /**
     * 群发
     *
     * @param message
     */
    private void sendAllMessage(String message) {
        //收到信息后，群发给所有channel
        ChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }

    private static Map<String, String> getUrlParams(String url) {
        Map<String, String> map = new HashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;
        } else {
            return map;
        }
    }

    /**
     * 删除用户与channel的对应关系
     */
    private void removeUserId(ChannelHandlerContext ctx) {
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        ChannelHandlerPool.getChannelMap().remove(userId);
    }
}