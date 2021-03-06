package com.meals.security.permission;

import java.lang.annotation.*;

/**
 * 自定义权限控制注解
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
public @interface Authorize {
    /**
     * 权限KEY，接口如果支持多个权限，逗号隔开
     * @return
     */
    String value() default "";
}
