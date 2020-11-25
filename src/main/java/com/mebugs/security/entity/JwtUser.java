package com.mebugs.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * JWT用户对象 缓存于Redis中
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class JwtUser {
    //用户ID
    private Long id;
    //用户名
    private String name;
    //角色KEY数组 前端用于判断权限
    private List<String> roles;
}
