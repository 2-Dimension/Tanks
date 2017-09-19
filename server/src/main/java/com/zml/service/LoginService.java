package com.zml.service;

import com.zml.command.BaseCommand;
import com.zml.command.TankCommand;
import com.zml.common.Const;
import com.zml.common.SystemManager;
import com.zml.model.Role;
import com.zml.util.ConvertUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.DatagramPacket;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/11
 * Time: 16:24
 */
@Service("HeartBreakService")
@Getter
@Setter
public class LoginService extends AbstractService {


    public void run() {
        InetSocketAddress sender = message.getSender();

        //随机生成一个角色
        Role newRole = new Role();
        newRole.setId(SystemManager.getInstance().generateId());
        newRole.setHp(100);
        newRole.setAttack(10);
        newRole.setAttackSpeed(1);
        newRole.setPosition_x(RandomUtils.nextFloat(0f,10f));
        newRole.setPosition_y(RandomUtils.nextFloat(0f,10f));
        newRole.setPosition_z(RandomUtils.nextFloat(0f,10f));
        SystemManager.getInstance().login(sender,newRole);

        //返回生成的信息
        TankCommand.UpdateRoleCommand.Builder builder = TankCommand.UpdateRoleCommand.newBuilder();

        builder.setId(newRole.getId());
        builder.setAttack(newRole.getAttack());
        builder.setHp(newRole.getHp());
        builder.setPositionX(newRole.getPosition_x());
        builder.setPositionY(newRole.getPosition_y());
        builder.setPositionZ(newRole.getPosition_z());

        TankCommand.UpdateRoleCommand build = builder.build();

        byte[] command = ConvertUtil.getBytes(Const.UpdateRole);
        DatagramPacket datagramPacketRet = new DatagramPacket(Unpooled.copiedBuffer(command,build.toByteArray()),sender);
        channel.writeAndFlush(datagramPacketRet);

    }
}
