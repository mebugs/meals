package com.mebugs.sys.mapper;

import com.mebugs.sys.entity.SysUserRole;
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
     * 查询用户角色清单 返回的是角色Key数组
     * @param id
     * @return
     */
    List<String> getUserRoles(Long id);
}
