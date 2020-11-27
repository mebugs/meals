package com.mebugs.security.interceptor;

import com.mebugs.security.context.JwtUserContext;
import com.mebugs.security.permission.RolePermission;
import com.mebugs.security.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色拦截器
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-25
 */
public class RoleInterceptor extends HandlerInterceptorAdapter {

    /**
     * 拦截器前置处理逻辑
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证权限
        if (this.hasPermission(handler)) {
            return true;
        }else{
            //返回无权限
            ResponseUtils.errorReturn(ResponseUtils.NEED_AUTH,response);
            return false;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
    /**
     * 检查当前登录用户是否有权限
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler) {
        //过滤器被启用
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            RolePermission rolePermission = handlerMethod.getMethod().getAnnotation(RolePermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (rolePermission == null) {
                rolePermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RolePermission.class);
            }
            // 如果注解为null, 说明该方法或该类直接放行
            if (rolePermission == null) {
                return true;
            }
            String roles = rolePermission.roles();
            // 如果标记了注解，则判断权限
            if (StringUtils.isNotBlank(roles)) {
                //取出当前登录用户的角色清单 进入拦截器的且需要鉴权的一定已经存在了JwtUser
                List<String> userRoles = JwtUserContext.getUser().getRoles();
                //用户角色为空直接返回
                if(userRoles==null||userRoles.size()<1)
                {
                    return false;
                }
                //可能配置了多个角色
                String[] needRoles = roles.split(",");
                for(String needRole : needRoles)
                {
                    //只要任意角色成功匹配上返回TRUE
                    if(userRoles.contains(needRole))
                    {
                        return true;
                    }
                }
                //循环完毕也为匹配上
                return false;
            }
        }
        return true;
    }
}
