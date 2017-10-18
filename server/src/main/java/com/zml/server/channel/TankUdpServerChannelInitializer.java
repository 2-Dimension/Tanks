package com.zml.server.channel;

import com.zml.server.decoder.UdpMessageDecoder;
import com.zml.server.encoder.UdpMessageEncoder;
import com.zml.server.handler.UdpChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by zhumeilu on 17/9/7.
 */
public class TankUdpServerChannelInitializer extends ChannelInitializer<NioDatagramChannel>  {

    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
        ChannelPipeline pipeline = nioDatagramChannel.pipeline();
        pipeline.addLast("decoder",new UdpMessageDecoder());
        pipeline.addLast("encoder",new UdpMessageEncoder());
        pipeline.addLast("handler", new UdpChannelHandler());//消息处理器
//        pipeline.addLast("handler", new S);//消息处理器
        pipeline.addLast("logging",new LoggingHandler(LogLevel.INFO));
    }
}
