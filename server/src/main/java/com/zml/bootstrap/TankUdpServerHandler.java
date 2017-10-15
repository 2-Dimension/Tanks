package com.zml.bootstrap;

import com.zml.command.DemoCommand;
import com.zml.command.TankCommand;
import com.zml.common.SystemManager;
import com.zml.common.TankMessage;
import com.zml.service.HandlerService;
import com.zml.util.ConvertUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhumeilu on 17/9/10.
 */
public class TankUdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private HandlerService handlerService;

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {

        logger.info("----------收到客户端请求--------");
        System.out.println("----------收到客户端请求--------");
        ByteBuf data = datagramPacket.content();
        //获取命令
        byte[] orderByte = new byte[2];
        data.readBytes(orderByte);
        short order = ConvertUtil.getShort(orderByte);
        System.out.println(order);
        if(order==2333){
            System.out.println("------收到demoCommand----------");
            DemoCommand.UserInfoCommand.Builder builder = DemoCommand.UserInfoCommand.newBuilder();
            builder.setId("1");
            builder.setPositionX("11");
            builder.setPositionZ("22");
            DatagramPacket datagramPacketRet = new DatagramPacket(Unpooled.copiedBuffer(builder.build().toByteArray()),datagramPacket.sender());

            channelHandlerContext.writeAndFlush(datagramPacketRet);
            return;
        }
        String serviceName = SystemManager.getInstance().getOrderHandlerMap().get(order + "");

        byte[] bodyByte = new byte[data.readableBytes()];
        data.readBytes(bodyByte);
        TankMessage message = new TankMessage(order,bodyByte,datagramPacket.sender());
        handlerService.submit(channelHandlerContext,message,serviceName);
    }
}
