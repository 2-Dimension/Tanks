package com.zml.service;

import io.netty.channel.Channel;
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

    public static void submit(Channel channel, Object msgObject)
            throws InstantiationException, IllegalAccessException {

        HitService hitService = (HitService)context.getBean("");
        hitService.setChannel(channel);
        hitService.setHitCommand(null);

        executorService.submit(hitService);
    }

}
