package com.zml;

import com.zml.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: zhumeilu
 * Date: 2017/9/19
 * Time: 20:03
 */
public class Test {

    @org.junit.Test
    public void test1(){

        Role role = new Role();
        role.setId(1);
        role.setAttack(1);
        List list1 = new ArrayList();
        list1.add(role);

        List list2 = new ArrayList();
        list2.add(role);

        role.setAttack(2);

        System.out.println(list1);
        System.out.println(list2);

    }
}
