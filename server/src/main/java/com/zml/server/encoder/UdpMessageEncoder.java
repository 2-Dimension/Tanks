package com.zml.server.encoder;

import com.zml.server.message.UdpMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by jiangwenping on 17/2/8.
 */
public class UdpMessageEncoder extends MessageToMessageEncoder<UdpMessage> {

    public ByteBuf writeByteBuf(UdpMessage netMessage) throws Exception {
        ByteBuf byteBuf = Unpooled.buffer(1024);
        //head
        byteBuf.writeShort(netMessage.getHead());
        byte[] body = netMessage.getBody();
        //length
        byteBuf.writeInt(body.length);
        //cmd
        byteBuf.writeShort(netMessage.getCmd());
        //version
        byteBuf.writeByte(netMessage.getVersion());
        System.out.println(body.length);
        //body
        byteBuf.writeBytes(netMessage.getBody());
        return byteBuf;
    }

    protected void encode(ChannelHandlerContext channelHandlerContext, UdpMessage udpMessage, List<Object> list) throws Exception {
        ByteBuf byteBuf = writeByteBuf(udpMessage);
        list.add(new DatagramPacket(byteBuf,udpMessage.getSender()));
    }
}
