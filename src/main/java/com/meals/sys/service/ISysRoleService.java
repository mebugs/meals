package com.meals.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meals.sys.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 获取角色分页
     * @return
     */
    IPage<SysRole> rolePage(Page page, String name);

    /**
     * 获取角色的完整信息
     * @param id
     * @return
     */
    SysRole getRoleInfo(Long id);

    /**
     * 保存角色数据 新增修改通用方法
     * @return
     */
    boolean saveOne(SysRole sysRole);

    /**
     * 删除角色
     * @return
     */
    boolean delRole(Long id);
}
