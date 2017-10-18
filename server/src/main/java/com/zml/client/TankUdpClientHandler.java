package com.zml.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;


/**
 * Created by zhumeilu on 17/9/10.
 */
public class TankUdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        ByteBuf data = datagramPacket.content();

        System.out.printf("sender"+datagramPacket.sender());

    }
}
