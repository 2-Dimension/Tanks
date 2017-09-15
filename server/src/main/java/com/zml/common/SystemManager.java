package com.zml.common;

import com.zml.model.Role;

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

    }

    private static SystemManager systemManager = new SystemManager();

    private ConcurrentHashMap connections = new ConcurrentHashMap<InetSocketAddress,Role>();      //存储所有的连接

    private static HashMap<String,String> orderHandlerMap = new HashMap<String,String>();           //存储命令和服务对应map
    private ConcurrentHashMap heartBreakMap = new ConcurrentHashMap<InetSocketAddress,Long>();      //存储所有的心跳
    public static AtomicInteger id  = new AtomicInteger(0);

    static{
        orderHandlerMap.put("0","HeartBreak");  //心跳
        orderHandlerMap.put("1","Login");  //登录
        orderHandlerMap.put("2","Quit");  //退出

        orderHandlerMap.put("100","UpdateRole");  //更新角色信息
        orderHandlerMap.put("101","GetRoleList");  //获取所有角色信息

        orderHandlerMap.put("201","Move");  //移动
        orderHandlerMap.put("202","Turn");  //转向
        orderHandlerMap.put("203","Fire");  //开火
        orderHandlerMap.put("204","Hit");  //击中

    }

    public static SystemManager getInstance(){
        return systemManager;
    }


    public ConcurrentHashMap getConnections() {
        return connections;
    }

    public static AtomicInteger getId() {
        return id;
    }

    public static HashMap<String, String> getOrderHandlerMap() {
        return orderHandlerMap;
    }

    public ConcurrentHashMap getHeartBreakMap() {
        return heartBreakMap;
    }
}
