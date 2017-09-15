package com.zml.service;

import com.zml.common.SystemManager;
import com.zml.model.Role;
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
        InetSocketAddress sender = datagramPacket.sender();


        AtomicInteger id = SystemManager.getInstance().getId();

        //随机生成一个角色
        Role newRole = new Role();
        newRole.setId(id.getAndIncrement());
        newRole.setPosition_x(RandomUtils.nextFloat(0f,10f));
        newRole.setPosition_y(RandomUtils.nextFloat(0f,10f));
        newRole.setPosition_z(RandomUtils.nextFloat(0f,10f));
        SystemManager.getInstance().getConnections().put(sender,newRole);
        //返回生成的信息

//        BaseCommand.PositionCommand.Builder builder = BaseCommand.PositionCommand.newBuilder();
//        builder.setId(newRole.getId());
//        builder.setPositionX(newRole.getPosition_x());
//        builder.setPositionY(newRole.getPosition_y());
//        builder.setPositionZ(newRole.getPosition_z());
//        BaseCommand.PositionCommand build = builder.build();
//
//        byte[] command = ConvertUtil.getBytes((short) 2);
//        DatagramPacket datagramPacketRet = new DatagramPacket(Unpooled.copiedBuffer(command,build.toByteArray()),sender);
//        channelHandlerContext.writeAndFlush(datagramPacketRet);

    }
}
