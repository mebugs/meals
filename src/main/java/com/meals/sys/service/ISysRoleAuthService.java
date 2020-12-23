package com.meals.sys.service;

import com.meals.sys.entity.SysRoleAuth;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色授权清单 服务类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
public interface ISysRoleAuthService extends IService<SysRoleAuth> {

    /**
     * 查询角色勾选的权限集
     * @param id
     * @return
     */
    List<Long> getRoleAuthIds(Long id);

    /**
     * 先删除当前ID下的权限集 然后批量刷入新的权限集
     * @param id
     * @param authIds
     */
    void updateRoleAuth(Long id, List<Long> authIds);
}
