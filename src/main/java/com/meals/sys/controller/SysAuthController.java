package com.meals.sys.controller;


import com.meals.data.cons.Constant;
import com.meals.data.response.R;
import com.meals.sys.service.ISysAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限集表 前端控制器
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
@RestController
@RequestMapping("/sysAuth")
public class SysAuthController {
    @Autowired
    private ISysAuthService sysAuthService;

    /**
     * 查询全部权限集树数据
     * @return
     */
    @GetMapping("allAuthTree")
    public R getAllAuthTree()
    {
        return R.ok(sysAuthService.getAuthTree("All", Constant.LONG_ZERO,null));
    }
}

