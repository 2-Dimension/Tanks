package com.zml.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zml.command.TankCommand;
import com.zml.common.SystemManager;
import com.zml.model.Role;
import com.zml.util.ConvertUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.DatagramPacket;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:更新位置服务
 * User: zhumeilu
 * Date: 2017/9/11
 * Time: 16:24
 */
@Service("UpdateRoleService")
@Getter
@Setter
public class UpdateRoleService extends AbstractService {


    public void run() {
        InetSocketAddress sender = message.getSender();
        try {
            TankCommand.UpdateRoleCommand updateRoleCommand = TankCommand.UpdateRoleCommand.parseFrom(message.getBody());
            //根据id获取role
            Role role = SystemManager.getInstance().getRoleById(updateRoleCommand.getId());
            role.setGunRoll(updateRoleCommand.getGunRoll());
            role.setGunRot(updateRoleCommand.getGunRot());
            role.setPosition_x(updateRoleCommand.getPositionX());
            role.setPosition_y(updateRoleCommand.getPositionY());
            role.setPosition_z(updateRoleCommand.getPositionZ());
            role.setRot_x(updateRoleCommand.getRotX());
            role.setRot_y(updateRoleCommand.getRotY());
            role.setRot_z(updateRoleCommand.getRotZ());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        //获取所有连接，遍历发送位置
        ConcurrentHashMap connections = SystemManager.getInstance().getConnections();
        Enumeration keys = connections.keys();
        while (keys.hasMoreElements()){
            DatagramPacket datagramPacketRet = new DatagramPacket(Unpooled.copiedBuffer(ConvertUtil.getBytes(message.getCmd()),message.getBody()),(InetSocketAddress)keys.nextElement());
            channel.write(datagramPacketRet);
        }
        channel.flush();
    }
}
