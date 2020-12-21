package com.meals.sys.controller;


import com.meals.data.response.R;
import com.meals.sys.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 查询全部角色供页面选择
     * @return
     */
    @GetMapping("allList")
    public R allList()
    {
        return R.ok(sysRoleService.list());
    }
}

