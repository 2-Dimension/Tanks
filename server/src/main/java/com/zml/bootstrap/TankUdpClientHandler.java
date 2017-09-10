package com.zml.bootstrap;

import com.zml.command.BaseCommand;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;


/**
 * Created by zhumeilu on 17/9/10.
 */
public class TankUdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        ByteBuf data = datagramPacket.content();
        BaseCommand.HitCommand hitCommand = BaseCommand.HitCommand.parseFrom(data.array());
        System.out.println("接收到客户端的消息：damage-"+hitCommand.getDamage());

        System.out.printf("sender"+datagramPacket.sender());

    }
}
