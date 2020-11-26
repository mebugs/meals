package com.mebugs.security.mvcconfig;

import com.mebugs.security.interceptor.RoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 拦截器配置类
 * 注册拦截器
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-25
 */
@Configuration
public class MyMvcConfig  implements WebMvcConfigurer {
    /*
     * 拦截器配置
     * 在spring-mvc.xml配置文件内添加<mvc:interceptor>标签配置拦截器。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 角色校验拦截器配置
        registry.addInterceptor(new RoleInterceptor()).addPathPatterns("/**");
    }
}
