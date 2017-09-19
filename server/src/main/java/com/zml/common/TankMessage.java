package com.zml.common;

import lombok.Getter;
import lombok.Setter;

import java.net.InetSocketAddress;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/19
 * Time: 18:43
 */
@Getter
@Setter
public class TankMessage {

    private InetSocketAddress sender;
    private short cmd;
    private byte[] body;

    public TankMessage(short cmd,byte[] body,InetSocketAddress sender){
        this.body = body;
        this.cmd = cmd;
        this.sender = sender;
    }
    public TankMessage(){

    }
}
