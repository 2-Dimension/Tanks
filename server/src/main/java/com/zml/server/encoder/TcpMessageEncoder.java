package com.zml.server.encoder;

import com.zml.server.message.TcpMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by jiangwenping on 17/2/8.
 */
public class TcpMessageEncoder extends MessageToByteEncoder<TcpMessage> {


    @Override
    protected void encode(ChannelHandlerContext ctx, TcpMessage msg, ByteBuf byteBuf) throws Exception {
        writeByteBuf(byteBuf,msg);
    }

    public void writeByteBuf(ByteBuf byteBuf,TcpMessage netMessage) throws Exception {
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
        System.out.println("--------encoder_length:"+byteBuf.readableBytes());
    }

}
