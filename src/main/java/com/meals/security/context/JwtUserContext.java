package com.meals.security.context;

import com.meals.security.entity.JwtUser;

/**
 * JWT安全用上下文对象
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-25
 */

public class JwtUserContext {
    public static final  ThreadLocal<JwtUser> jwtUserLocal = new ThreadLocal<JwtUser>();

    /**
     * 传入安全用户信息 过滤器时存入
     * @param jwtUser
     */
    public static void setUser(JwtUser jwtUser) {
        jwtUserLocal.set(jwtUser);
    }

    /**
     * 获取当前请求的用户信息
     * @return
     */
    public static JwtUser getUser() {
        return jwtUserLocal.get();
    }
}
