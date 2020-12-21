package com.meals.sys.service;

import com.meals.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统用户角色表 服务类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 查询用户角色清单 返回的是角色Key数组
     * @param id
     * @return
     */
    List<String> getUserRoleKeys(Long id);

    /**
     * 更新角色清单
     * @param id
     * @param roles
     */
    void putUserRoles(Long id, List<Long> roles);

    /**
     * 获取用户角色ID
     * @param id
     * @return
     */
    List<Long> getUserRoleIds(Long id);

    /**
     * 获取用户角色名称
     * @param id
     * @return
     */
    List<String> getUserRoleNames(Long id);
}
