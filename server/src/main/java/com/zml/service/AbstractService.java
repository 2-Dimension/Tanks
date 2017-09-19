package com.zml.service;

import com.zml.common.TankMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/14
 * Time: 16:23
 */
@Getter
@Setter
public abstract class AbstractService implements Runnable{
    protected Channel channel;
    protected TankMessage message;
}
