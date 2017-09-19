package com.zml.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zml.command.BaseCommand;
import com.zml.command.TankCommand;
import com.zml.common.SystemManager;
import com.zml.model.Role;
import com.zml.util.ConvertUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.Enumeration;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/11
 * Time: 16:24
 */
@Service
@Getter
@Setter
public class HitService extends AbstractService {


    public void run() {
        //处理相应的业务
        try {
            TankCommand.HitCommand hitCommand = TankCommand.HitCommand.parseFrom(message.getBody());

            Role role = SystemManager.getInstance().getRoleById(hitCommand.getId());
            int damage = hitCommand.getDamage();
            int enemyId = hitCommand.getEnemyId();
            Role enemy = SystemManager.getInstance().getRoleById(enemyId);
            int hp = enemy.getHp() - damage;
            enemy.setHp(hp==0?0:hp);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        //重新封装消息，分发给所有在线用户
        Enumeration keys = SystemManager.getInstance().getConnections().keys();
        while(keys.hasMoreElements()){
            DatagramPacket datagramPacketRet = new DatagramPacket(Unpooled.copiedBuffer(ConvertUtil.getBytes(message.getCmd()),message.getBody()),(InetSocketAddress)keys.nextElement());
            channel.write(datagramPacketRet);
        }

        channel.flush();
    }
}
