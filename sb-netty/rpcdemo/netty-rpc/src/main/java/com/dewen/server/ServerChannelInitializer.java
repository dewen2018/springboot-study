// package com.dewen.server;
// import io.netty.channel.ChannelInitializer;
// import io.netty.channel.socket.SocketChannel;
// import io.netty.handler.codec.serialization.ClassResolvers;
// import io.netty.handler.codec.serialization.ObjectDecoder;
// import io.netty.handler.codec.serialization.ObjectEncoder;
//
// /**
// * netty服务初始化器
// **/
// public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
//     @Override
//     protected void initChannel(SocketChannel socketChannel) throws Exception {
//         //添加编解码
//         socketChannel.pipeline()
//                 .addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
//                 .addLast(new ObjectEncoder())
//                 .addLast(serverHandler);
//     }
// }