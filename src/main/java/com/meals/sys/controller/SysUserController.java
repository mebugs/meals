package com.meals.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meals.data.response.R;
import com.meals.security.context.JwtUserContext;
import com.meals.security.permission.Authorize;
import com.meals.sys.entity.SysUser;
import com.meals.sys.service.ISysUserService;
import com.meals.sys.to.UserDo;
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

    /**
     * 保存账号数据 新增 修改 锁定 解锁
     * @param userDo
     * @return
     */
    @Authorize("SYS-USER-M")
    @PostMapping("/user/save")
    public R saveOne(@RequestBody UserDo userDo){
        return R.ok(sysUserService.saveOne(userDo));
    }

    /**
     * 查询账号分页
     * @param page
     * @param userDo
     * @return
     */
    @Authorize("SYS-USER-V")
    @GetMapping("/user/page")
    public R getPage(Page page, UserDo userDo){
        return R.ok(sysUserService.getPage(page,userDo));
    }

    /**
     * 更具账号ID查询用户信息
     * @param id
     * @return
     */
    @Authorize("SYS-USER-M")
    @GetMapping("/user/get")
    public R getUserInfo(Long id)
    {
        return R.ok(sysUserService.getUserInfo(id));
    }

    /**
     * 获取自己的用户信息
     * @return
     */
    @Authorize("SYS-USER-P")
    @GetMapping("/user/getMine")
    public R getMine(){
        return R.ok(sysUserService.getMine());
    }

    /**
     * 用户自行修改密码
     * @param nowPwd
     * @param newPwd
     * @return
     */
    @Authorize("SYS-USER-P")
    @GetMapping("/user/resetPwd")
    public R resetPwd(String nowPwd,String newPwd){
        return R.ok(sysUserService.resetPwd(nowPwd,newPwd));
    }
}

