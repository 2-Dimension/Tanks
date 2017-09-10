package com.zml.bootstrap;

import com.zml.command.BaseCommand;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.net.DatagramPacket;

/**
 * Created by zhumeilu on 17/9/10.
 */
public class TankUdpServerInitializer extends ChannelInitializer<NioDatagramChannel> {

    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
        ChannelPipeline pipeline = nioDatagramChannel.pipeline();

//         添加UDP解码器
//         pipeline.addLast("datagramPacketDecoder", new DatagramPacketDecoder(
//         new ProtobufDecoder(BaseCommand.ServerCommand.getDefaultInstance())));
        // 添加UDP编码器
//         pipeline.addLast("datagramPacketEncoder",
//         new DatagramPacketEncoder<BaseCommand>(new ProtobufEncoder()));

        pipeline.addLast("handler", new TankUdpServerHandler());//消息处理器
//        pipeline.addLast("ackHandler", new UdpAckServerHandler());//ack处理器

//        pipeline.addLast("timeout", new IdleStateHandler(180, 0, 0,
//                TimeUnit.SECONDS));// //此两项为添加心跳机制,60秒查看一次在线的客户端channel是否空闲
//        pipeline.addLast(new UdpHeartBeatServerHandler());// 心跳处理handler
    }
}
