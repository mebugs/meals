package com.meals.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.meals.sys.entity.SysUserRole;
import com.meals.sys.mapper.SysUserRoleMapper;
import com.meals.sys.service.ISysRoleAuthService;
import com.meals.sys.service.ISysUserAuthService;
import com.meals.sys.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meals.task.thread.ThreadAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ISysRoleAuthService sysRoleAuthService;
    @Autowired
    private ThreadAuthUser threadAuthUser;
    @Autowired
    private ISysUserAuthService sysUserAuthService;
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
        //获取用户权限集清单
        List<Long> authIds = new ArrayList<>();
        //批量插入新角色
        List<SysUserRole> userRoles = new ArrayList<>();
        for(Long role : roles)
        {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRid(role);
            sysUserRole.setUid(id);
            userRoles.add(sysUserRole);
            //提取该角色的权限清单
            List<Long> roleAuth = sysRoleAuthService.getRoleAuthIds(role);
            authIds.addAll(roleAuth);
        }
        this.saveBatch(userRoles);
        //权限树去重
        List<Long> userAuthIds = authIds.stream().distinct().collect(Collectors.toList());
        //刷新用户权限集
        sysUserAuthService.updateUserAuth(id,userAuthIds);
        //启动用户权限树和JWT权限集缓存
        threadAuthUser.syncUserAuth(id);
    }

    /**
     * 更新用户角色权限集
     * @param id
     */
    @Override
    public void putUserRoleAuth(long id) {
        //提取用户角色清单
        List<Long> roles = getUserRoleIds(id);
        List<Long> authIds = new ArrayList<>();
        for(Long role : roles)
        {
            //提取该角色的权限清单
            List<Long> roleAuth = sysRoleAuthService.getRoleAuthIds(role);
            authIds.addAll(roleAuth);
        }
        //获得用户的新权限集清单
        List<Long> userAuthIds = authIds.stream().distinct().collect(Collectors.toList());
        //刷新用户权限集
        sysUserAuthService.updateUserAuth(id,userAuthIds);
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

    /**
     * 获取当前角色下的用户ID
     * @param roleId
     * @return
     */
    @Override
    public List<Long> getRoleUserIds(Long roleId) {
        return this.baseMapper.getRoleUserIds(roleId);
    }
    /**
     * 清理某个角色的关联数据
     * @param roleId
     */
    @Override
    public void cleanUserRole(Long roleId) {
        //删除当前角色关联用户
        this.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getRid,roleId));
    }
}
