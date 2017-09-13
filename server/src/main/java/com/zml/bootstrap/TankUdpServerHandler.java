package com.zml.bootstrap;

import com.google.protobuf.ExtensionRegistry;
import com.zml.command.BaseCommand;
import com.zml.command.TankCommand;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;


/**
 * Created by zhumeilu on 17/9/10.
 */
public class TankUdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
//        String content = datagramPacket.content().toString(CharsetUtil.UTF_8);
//        System.out.printf("sender"+datagramPacket.sender());
//        System.out.println("服务器收到消息："+content);
//        channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("回复："+content,CharsetUtil.UTF_8),datagramPacket.sender()));

        System.out.println("---接收到消息---");

        ByteBuf data = datagramPacket.content();
        byte[] b = new byte[data.readableBytes()];
        data.readBytes(b);
//        ExtensionRegistry registry = ExtensionRegistry.newInstance();
//        BaseCommand.registerAllExtensions(registry);
//
//        BaseCommand.ServerCommand serverCommand = BaseCommand.ServerCommand.parseFrom(b,registry);
//        System.out.println("commandType:"+serverCommand.getCommandType());
//        BaseCommand.HitCommand extension = serverCommand.getExtension(BaseCommand.HitCommand.hitCommand);
//        System.out.println("damage:"+extension.getDamage());
//        System.out.println("enemyId:"+extension.getEnemyId());
//        System.out.println("id:"+extension.getId());
        TankCommand.HitCommand hitCommand = TankCommand.HitCommand.parseFrom(b);

        System.out.println("commandType:"+hitCommand.getCommandType());
        System.out.println("damage:"+hitCommand.getDamage());
//        System.out.println("enemyId:"+extension.getEnemyId());
//        BaseCommand.HitCommand hitCommand = BaseCommand.HitCommand.parseFrom(b);
//        System.out.println("接收到客户端的消息：damage-"+hitCommand.getDamage());

    }
}
