package com.meals.security.filter;

import com.meals.security.context.JwtUserContext;
import com.meals.security.entity.JwtUser;
import com.meals.security.utils.JwtUtils;
import com.meals.security.utils.ResponseUtils;
import com.meals.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT过滤器
 * 继承OncePerRequestFilter只执行一次
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-25
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 执行登录过滤
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String uri = httpServletRequest.getRequestURI();
        //静态资源 API开放接口 鉴权接口 OPTION请求直接放行
        if(uri.startsWith("/static/") || uri.startsWith("/api/") || uri.startsWith("/auth/") || httpServletRequest.getMethod().toUpperCase().equals("OPTIONS"))
        {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            //获取JWT用户对象
            JwtUser jwtUser = jwtUtils.getJwtUserFromRequest(httpServletRequest);
            boolean outFlag = false;
            if(jwtUser!=null)
            {
                //从Redis中提取用户安全信息 不存在则从数据库中获取
                jwtUser = sysUserService.getJwtUser(jwtUser.getId());
                //用户被锁定或删除时会返回NULL 修改过滤控制位
                if(jwtUser == null)
                {
                    outFlag = true;
                }else{
                    //将安全对象放入上下文供全局取用
                    JwtUserContext.setUser(jwtUser);
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }
            }else {
                outFlag = true;
            }
            if(outFlag)
            {
                //返回未登录
                ResponseUtils.errorReturn(ResponseUtils.NEED_AUTH,httpServletResponse);
            }
        }
    }
}
