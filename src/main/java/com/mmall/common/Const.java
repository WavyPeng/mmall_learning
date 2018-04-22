package com.mmall.common;

/**
 * 常量类
 * Created by WavyPeng on 2018/4/20.
 */
public class Const {

    //当前用户
    public static final String CURRENT_USER = "currentUser";
    //邮箱
    public static final String EMAIL = "email";
    //用户名
    public static final String USERNAME = "username";
    //通过内部接口类对常量进行分组
    public interface Role{
        int ROLE_CUSTOMER = 0;  //普通用户
        int ROLE_ADMIN = 1;     //管理员
    }
}
