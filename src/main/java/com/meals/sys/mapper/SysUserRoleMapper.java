package com.meals.sys.mapper;

import com.meals.sys.entity.SysRole;
import com.meals.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统用户角色表 Mapper 接口
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 查询用户角色清单 返回的是角色Key
     * @param id
     * @return
     */
    List<String> getUserRoles(Long id);
    /**
     * 获取用户角色ID
     * @param id
     * @return
     */
    List<Long> getUserRoleIds(Long id);
    /**
     * 获取用户角色
     * @param id
     * @return
     */
    List<SysRole> getUserRoleObj(Long id);
    /**
     * 获取当前角色下的用户ID
     * @param roleId
     * @return
     */
    List<Long> getRoleUserIds(Long roleId);
}
