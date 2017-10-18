package com.zml.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/10/18
 * Time: 15:18
 */
public interface LoggerSupport {
    default Logger getLogger(){
        return LoggerFactory.getLogger(this.getClass());
    }
}
