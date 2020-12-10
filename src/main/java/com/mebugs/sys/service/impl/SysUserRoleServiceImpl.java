package com.mebugs.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mebugs.sys.entity.SysUserRole;
import com.mebugs.sys.mapper.SysUserRoleMapper;
import com.mebugs.sys.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<String> getUserRoleKeys(Long id) {
        return this.baseMapper.getUserRoles(id);
    }

    /**
     * 更新角色清单
     * @param id
     * @param roles
     */
    @Override
    public void putUserRoles(Long id, List<Long> roles) {
        //删除当前用户角色
        this.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUid,id));
        //批量插入新角色
        List<SysUserRole> userRoles = new ArrayList<>();
        for(Long role : roles)
        {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRid(role);
            sysUserRole.setUid(id);
            userRoles.add(sysUserRole);
        }
        this.saveBatch(userRoles);
    }

    /**
     * 获取用户角色ID
     * @param id
     * @return
     */
    @Override
    public List<Long> getUserRoleIds(Long id) {
        return this.baseMapper.getUserRoleIds(id);
    }

    /**
     * 获取用户角色名称
     * @param id
     * @return
     */
    @Override
    public List<String> getUserRoleNames(Long id) {
        return this.baseMapper.getUserRoleNames(id);
    }
}
