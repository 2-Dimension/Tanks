package com.zml.service;

import com.zml.common.TankMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/11
 * Time: 16:23
 */
@Service
public class HandlerService {

    @Autowired
    private static ApplicationContext context;
    private static final int MAX_THREAD_NUM = 50;

    private static ExecutorService executorService =
            Executors.newFixedThreadPool(MAX_THREAD_NUM);

    public static void submit(ChannelHandlerContext ctx, TankMessage message, String serviceName)
            throws InstantiationException, IllegalAccessException {

        AbstractService service = (AbstractService) context.getBean(serviceName);
        service.setChannel(ctx.channel());
        service.setMessage(message);
        executorService.submit(service);
    }

}
