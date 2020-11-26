package com.mebugs.sys.controller;


import com.mebugs.data.response.R;
import com.mebugs.security.context.JwtUserContext;
import com.mebugs.sys.entity.SysUser;
import com.mebugs.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
@RestController
//用户控制层涉及到登录相关，因此不在类上定义路径
//@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 登录
     * @param sysUser
     * @return
     */
    @PostMapping("/auth/login")
    public R login(@RequestBody SysUser sysUser)
    {
        return R.ok(sysUserService.login(sysUser));
    }

    /**
     * 获取用户基本信息 JwtUser对象数据
     * 本方法经过过滤器 直接返回上下文中的数据即可
     * @return
     */
    @GetMapping("/user/base")
    public R getUserBase()
    {
        return R.ok(JwtUserContext.getUser());
    }
}

