package com.zml.bootstrap;

import com.zml.command.BaseCommand;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.InetSocketAddress;

/**
 * Created by zhumeilu on 17/9/7.
 */
public class TankUdpClient {
    public void bind(int port) throws Exception{


        //配置服务端的NIO线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_BROADCAST, true)   //支持广播
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024)// 设置UDP读缓冲区为1M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)// 设置UDP写缓冲区为1M
                    .handler(new TankUdpClientHandler());
            //绑定端口，同步等待成功
            ChannelFuture f= b.bind(0).sync();
            Channel channel = f.channel();
            String data = "hello world";
            BaseCommand.HitCommand.Builder builder = BaseCommand.HitCommand.newBuilder();
            builder.setDamage(10);
            builder.setEnemyId(2);
            builder.setId(1);
            BaseCommand.HitCommand build = builder.build();
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(build.toByteArray()),new InetSocketAddress("127.0.0.1",port)));
            //等待服务器监听端口关闭
            channel.closeFuture().await();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //优雅退出，释放线程池资源
            workerGroup.shutdownGracefully();
        }


    }


    public static void main(String[] args) throws Exception {
        int port = 8081;
        if(args!=null&&args.length>0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }
        new TankUdpClient().bind(port);

    }
}
