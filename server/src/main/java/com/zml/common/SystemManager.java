package com.zml.common;

import com.zml.model.Role;
import io.netty.channel.EventLoopGroup;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:系统管理
 * User: zhumeilu
 * Date: 2017/9/14
 * Time: 15:18
 */
public class SystemManager {
    private SystemManager(){
        userOrderHandlerMap = new HashMap<Short, String>();
        userOrderHandlerMap.put(Const.HeartBreakCommand,"HeartBreak");  //心跳
        userOrderHandlerMap.put(Const.LoginRequestCommand,"UserLoginUdpService");  //登录

        userOrderHandlerMap.put(Const.RegistRequestCommand,"UserRegistTestUdpService");  //测试  upd位置同步使用
        userOrderHandlerMap.put(Const.PlayerInfoCommand,"PlayerInfoTestUdpService");  //测试 upd位置同步使用
        userOrderHandlerMap.put(Const.QuitGameCommand,"QuitGameTestUdpService");  //测试 upd位置同步使用
    }
    private static SystemManager systemManager = new SystemManager();

    @Getter
    @Setter
    private ApplicationContext context ;        //spring上下文

    @Setter
    private EventLoopGroup udpWorkerGroup;
    @Setter
    private EventLoopGroup tcpBossGroup;
    @Setter
    private EventLoopGroup tcpWorkerGroup;

    private ConcurrentHashMap connections = new ConcurrentHashMap<InetSocketAddress,Role>();      //存储所有的连接
    private ConcurrentHashMap roles = new ConcurrentHashMap<Integer,Role>();      //存储所有的角色

    private ConcurrentHashMap heartBreakMap = new ConcurrentHashMap<InetSocketAddress,Long>();      //存储所有的心跳
    public static AtomicInteger id  = new AtomicInteger(0);
    @Getter
    private HashMap<Short,String> userOrderHandlerMap;           //存储命令和服务对应map

    public static SystemManager getInstance(){
        return systemManager;
    }

    //登录，将sender和role保存
    public void login(InetSocketAddress sender,Role role){
        connections.put(sender,role);
        roles.put(role.getId(),role);
    }
    //退出
    public void logout(InetSocketAddress sender){
        Role remove = (Role) connections.remove(sender);
        roles.remove(remove.getId());
    }


    public Role getRoleBySender(InetSocketAddress sender){
        return (Role)connections.get(sender);
    }

    public Role getRoleById(Integer id){
        return (Role) roles.get(id);
    }


    public ConcurrentHashMap getConnections() {
        return connections;
    }

    public Integer generateId() {
        return id.getAndIncrement();
    }


    public ConcurrentHashMap getHeartBreakMap() {
        return heartBreakMap;
    }

    public void shutdownServer(){
        udpWorkerGroup.shutdownGracefully();
        System.out.println("-------udp服务器关闭----------");
        tcpBossGroup.shutdownGracefully();
        System.out.println("-------tcp boss服务器关闭----------");
        tcpWorkerGroup.shutdownGracefully();
        System.out.println("-------tcp worker服务器关闭----------");

    }
}
