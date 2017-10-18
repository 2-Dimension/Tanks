package com.zml.service;

import com.zml.common.SystemManager;
import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramPacket;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/11
 * Time: 16:24
 */
@Service("HeartBeatService")
@Getter
@Setter
public class HeartBeatService implements Runnable{

    private Channel channel;
    private DatagramPacket datagramPacket;

    public void run() {
        InetSocketAddress sender = datagramPacket.sender();

        ConcurrentHashMap heartBreakMap = SystemManager.getInstance().getHeartBreakMap();
        heartBreakMap.put(sender,System.currentTimeMillis());
    }
}
