package com.deaifish.mall;


import com.deaifish.mall.pojo.bo.JwtUser;

/**
 * @description JwtUser 上下文工具类
 *
 * @author DEAiFISH
 * @date 2025/1/13 13:18
 */
public class AuthUserContext {
    private static final ThreadLocal<JwtUser> USER_INFO_IN_TOKEN_HOLDER = new ThreadLocal<>();

    public static JwtUser get() {
        return USER_INFO_IN_TOKEN_HOLDER.get();
    }


    public static JwtUser forceGet() {
        return USER_INFO_IN_TOKEN_HOLDER.get();
    }

    public static void set(JwtUser userInfo) {
        USER_INFO_IN_TOKEN_HOLDER.set(userInfo);
    }

    public static void clean() {
        if (USER_INFO_IN_TOKEN_HOLDER.get() != null) {
            USER_INFO_IN_TOKEN_HOLDER.remove();
        }
    }
}