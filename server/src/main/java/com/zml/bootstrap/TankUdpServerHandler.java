package com.zml.bootstrap;

import com.zml.command.TankCommand;
import com.zml.common.SystemManager;
import com.zml.common.TankMessage;
import com.zml.service.HandlerService;
import com.zml.util.ConvertUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;


/**
 * Created by zhumeilu on 17/9/10.
 */
public class TankUdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private HandlerService handlerService;

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {

        ByteBuf data = datagramPacket.content();
        //获取命令
        byte[] orderByte = new byte[2];
        data.readBytes(orderByte);
        short order = ConvertUtil.getShort(orderByte);
        String serviceName = SystemManager.getInstance().getOrderHandlerMap().get(order + "");

        byte[] bodyByte = new byte[data.readableBytes()];
        data.readBytes(bodyByte);
        TankMessage message = new TankMessage(order,bodyByte,datagramPacket.sender());
        handlerService.submit(channelHandlerContext,message,serviceName);
    }
}
