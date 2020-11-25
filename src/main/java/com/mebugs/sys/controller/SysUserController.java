package com.mebugs.sys.controller;


import com.mebugs.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

}

