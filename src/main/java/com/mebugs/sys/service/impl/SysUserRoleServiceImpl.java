package com.mebugs.sys.service.impl;

import com.mebugs.sys.entity.SysUserRole;
import com.mebugs.sys.mapper.SysUserRoleMapper;
import com.mebugs.sys.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统用户角色表 服务实现类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    /**
     * 查询用户角色清单 返回的是角色Key数组
     * @param id
     * @return
     */
    @Override
    public List<String> getUserRoles(Long id) {
        return this.baseMapper.getUserRoles(id);
    }
}
