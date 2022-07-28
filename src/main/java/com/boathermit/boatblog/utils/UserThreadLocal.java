package com.boathermit.boatblog.utils;

import com.boathermit.boatblog.model.po.User;

/**
 * @author Yin Zihang
 * @since 2022/7/28 16:55
 */
public class UserThreadLocal {
    private UserThreadLocal(){}

    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User user){
        LOCAL.set(user);
    }
    public static User get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
