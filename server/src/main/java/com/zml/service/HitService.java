package com.zml.service;

import com.zml.command.BaseCommand;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/11
 * Time: 16:24
 */
@Service
@Getter
@Setter
public class HitService implements Runnable{

    private Channel channel;
    private BaseCommand.HitCommand hitCommand;

    public void run() {

        int id = hitCommand.getId();
        int enemyId = hitCommand.getEnemyId();
        int damage = hitCommand.getDamage();

        //处理相应的业务

        //重新封装消息，分发给所有在线用户

    }
}
