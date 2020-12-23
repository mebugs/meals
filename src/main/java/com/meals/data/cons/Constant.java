package com.meals.data.cons;

/**
 * 数据常量
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-20
 */
public class Constant {
    /**
     * 用户 - 正常
     */
    public static final Integer USER_OPEN = 1;
    /**
     * 用户 - 锁定
     */
    public static final Integer USER_LOCK = 2;
    /**
     * 用户 - 删除
     */
    public static final Integer USER_DELL = 3;

    /**
     * 通用 正常
     */
    public static final Integer USED = 1;
    /**
     * 通用 锁定 不可用
     */
    public static final Integer LOCK = 2;

    /**
     * 通用 Long数字-1
     */
    public static final Long LONG_MONE = -1L;
    /**
     * 通用 Long数字0
     */
    public static final Long LONG_ZERO = 0L;
    /**
     * 通用 一级
     */
    public static final Integer LEVEL_ONE = 1;
    /**
     * 通用 二级
     */
    public static final Integer LEVEL_TWO = 2;
    /**
     * 通用 三级
     */
    public static final Integer LEVEL_THR = 3;

    /**
     * 权限集树类型 All全部 改变量可通用
     */
    public static final String ALL = "All";
    /**
     * 权限集树类型 Role角色
     */
    public static final String ROLE = "Role";
    /**
     * 权限集树类型 User用户
     */
    public static final String USER = "User";
    /**
     * 全部权限
     */
    public static final String ALL_AUTH = "全部权限";
}
