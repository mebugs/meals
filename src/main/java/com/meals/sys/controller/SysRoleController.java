package com.meals.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meals.data.response.R;
import com.meals.data.utils.PageUtils;
import com.meals.security.permission.RolePermission;
import com.meals.sys.entity.SysRole;
import com.meals.sys.service.ISysRoleService;
import com.meals.sys.to.UserDo;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *     角色管理更新
 *  角色的状态字段意义不大 因此角色是直接执行删除能力
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 * update 2020-12-22
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

    /**
     * 获取角色分页
     * @return
     */
    @GetMapping("rolePage")
    public R rolePage(Page page, String name) {
        return R.ok(sysRoleService.rolePage(PageUtils.setPageOrder(page,null,true),name));
    }

    /**
     * 获取角色的完整信息
     * @param id
     * @return
     */
    @GetMapping("roleInfo/{id}")
    public R getRoleInfo(@PathVariable Long id) {
        return R.ok(sysRoleService.getRoleInfo(id));
    }

    /**
     * 保存角色数据
     * @return
     */
    @PostMapping("save")
    public R saveOne(@RequestBody SysRole sysRole){
        return R.ok();
    }
}

