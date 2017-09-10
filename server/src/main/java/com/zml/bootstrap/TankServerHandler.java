package com.zml.bootstrap;

import com.zml.command.BaseCommand;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by zhumeilu on 17/9/7.
 */
public class TankServerHandler extends ChannelHandlerAdapter {


//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        BaseCommand.ServerCommand command = (BaseCommand.ServerCommand) msg;
//        BaseCommand.CommandType commandType = command.getCommandType();
//
//
//        if(BaseCommand.CommandType.Hit.equals(command.getCommandType())){
//            System.out.println("服务器接收到的消息："+command.toString());
//
//        }
//    }




    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
