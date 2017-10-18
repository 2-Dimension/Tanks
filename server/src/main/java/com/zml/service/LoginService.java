package com.zml.service;

import com.zml.server.service.AbstractUdpService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/11
 * Time: 16:24
 */
@Service("LoginService")
@Getter
@Setter
public class LoginService extends AbstractUdpService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run() {

    }
}
