package com.mebugs.security.permission;

import java.lang.annotation.*;

/**
 * 自定义角色控制注解
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-25
 */
// 标注这个类它可以标注的位置
@Target({ElementType.METHOD, ElementType.TYPE})
// 标注这个注解的注解保留时期
@Retention(RetentionPolicy.RUNTIME)
// 是否生成注解文档
@Documented
public @interface RolePermission {
    /**
     * 角色KEY，如果是多个角色，用逗号分割
     * @return
     */
    public String role() default "";
}
