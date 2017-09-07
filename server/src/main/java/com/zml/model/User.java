package com.zml.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhumeilu on 17/9/8.
 * 用户
 */
@Getter
@Setter
public class User extends BaseModel{

    private String username;
    private String password;
}
