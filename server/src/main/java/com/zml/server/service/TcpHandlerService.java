package com.zml.server.service;

import com.zml.common.SystemManager;
import com.zml.server.message.TcpMessage;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/11
 * Time: 16:23
 */
@Component("TcpHandlerService")
public class TcpHandlerService {

    @Autowired
    private ApplicationContext context;

    private static final int MAX_THREAD_NUM = 50;

    private  ExecutorService executorService =
            Executors.newFixedThreadPool(MAX_THREAD_NUM);

    public void submit(ChannelHandlerContext ctx, TcpMessage message)
            throws InstantiationException, IllegalAccessException {

        short cmd = message.getCmd();
        System.out.println("-------cmd---------"+cmd);
        //根据cmd获取服务名
        String serviceName = SystemManager.getInstance().getUserOrderHandlerMap().get(cmd);
        AbstractTcpService service = (AbstractTcpService) context.getBean(serviceName);
        System.out.println("-------handlerService:"+cmd+"--根据命令获取serviceName:"+serviceName+"---获取的service实例："+service);
        service.setChannel(ctx.channel());
        service.setMessage(message);
        executorService.submit(service);
    }

}
