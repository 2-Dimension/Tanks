package com.zml.server.handler;

import com.zml.common.LoggerSupport;
import com.zml.common.SystemManager;
import com.zml.server.message.UdpMessage;
import com.zml.server.service.UdpHandlerService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/18
 * Time: 19:11
 */
public class UdpChannelHandler extends SimpleChannelInboundHandler<UdpMessage> implements LoggerSupport{

    Logger logger = LoggerFactory.getLogger(getClass());


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, UdpMessage udpMessage) throws Exception {
        logger.info("---------收到命令："+udpMessage.getCmd()+"-------------");
        UdpHandlerService handlerService = (UdpHandlerService) SystemManager.getInstance().getContext().getBean("UdpHandlerService");
        handlerService.submit(channelHandlerContext,udpMessage);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        getLogger().info("----------------注册了一个链接----------------");
        super.channelRegistered(ctx);
    }
}
