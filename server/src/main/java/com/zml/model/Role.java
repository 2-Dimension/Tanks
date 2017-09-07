package com.zml.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhumeilu on 17/9/8.
 * 角色
 */
@Getter
@Setter
public class Role extends BaseModel{

    private Float position_x;       //位置
    private Float position_y;
    private Float position_z;

    private Float attack;       //攻击力
    private Float hp;           //血量
    private Float attackSpeed;  //攻击速度


}
