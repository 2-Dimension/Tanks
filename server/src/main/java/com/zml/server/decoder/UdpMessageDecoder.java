package com.zml.server.decoder;

import com.zml.server.message.UdpMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by jiangwenping on 17/2/8.
 */
public class UdpMessageDecoder extends MessageToMessageDecoder<DatagramPacket> {



    private UdpMessage parseByteBuf(DatagramPacket datagramPacket) {
        InetSocketAddress sender = datagramPacket.sender();
        ByteBuf byteBuf = datagramPacket.content();
        System.out.println("-------decoder_length:"+byteBuf.readableBytes());
        //head
        short head = byteBuf.readShort();
        //length
        int length = byteBuf.readInt();
        //cmd
        short cmd = byteBuf.readShort();
        short version = byteBuf.readByte();
        System.out.println("---------解析的head："+head+"------head:"+length+"--cmd:"+cmd+"---version:"+version);
        byte[] body = new byte[byteBuf.readableBytes()];
        byteBuf.getBytes(byteBuf.readerIndex(), body);
//        byteBuf.readBytes(body);
        System.out.println("---body:"+body.length);
        UdpMessage message = new UdpMessage();
        message.setHead(head);
        message.setLength(length);
        message.setCmd(cmd);
        message.setBody(body);
        message.setSender(sender);
        return  message;
    }

    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
        UdpMessage message = parseByteBuf(datagramPacket);
        list.add(message);
    }
}
