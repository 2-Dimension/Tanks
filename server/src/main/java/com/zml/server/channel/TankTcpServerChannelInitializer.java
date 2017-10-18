package com.zml.server.channel;

import com.zml.server.decoder.TcpMessageDecoder;
import com.zml.server.encoder.TcpMessageEncoder;
import com.zml.server.handler.TcpChannelrHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhumeilu on 17/9/7.
 */
public class TankTcpServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        ChannelPipeline pipeline = nioSocketChannel.pipeline();
        int maxLength = Integer.MAX_VALUE;
        pipeline.addLast("frame", new LengthFieldBasedFrameDecoder(maxLength, 2, 4, 3, 0));
        pipeline.addLast(new IdleStateHandler(5,0,0, TimeUnit.SECONDS));    //2次读超时5秒，自动关闭channel
        nioSocketChannel.pipeline().addLast(new TcpMessageDecoder());
        nioSocketChannel.pipeline().addLast(new TcpMessageEncoder());
        nioSocketChannel.pipeline().addLast(new TcpChannelrHandler());
    }

}
