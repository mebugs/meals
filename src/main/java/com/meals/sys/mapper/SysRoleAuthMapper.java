package com.meals.sys.mapper;

import com.meals.sys.entity.SysRoleAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色授权清单 Mapper 接口
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
public interface SysRoleAuthMapper extends BaseMapper<SysRoleAuth> {

    /**
     * 查询角色勾选的权限集
     * @param id
     * @return
     */
    List<Long> getRoleAuthIds(Long id);
}
