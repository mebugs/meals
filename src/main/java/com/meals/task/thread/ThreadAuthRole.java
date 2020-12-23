package com.meals.task.thread;

import com.meals.data.cons.Constant;
import com.meals.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色权限集缓存更新线程池
 *
 * @author mebugs.com/米虫先生
 * @since 2020-12-23
 */
@Component
public class ThreadAuthRole {

    @Autowired
    private ISysAuthService sysAuthService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ISysRoleAuthService sysRoleAuthService;
    @Autowired
    private ISysUserAuthService sysUserAuthService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 根据角色ID更新角色权限集 以及 可能涉及的用户缓存
     * @param roleId 角色ID
     * @param invUser 是否设计用户同步
     * @param isDel 是否是删除角色
     */
    @Async("threadPool")
    public void syncRoleAuth(Long roleId, boolean invUser,boolean isDel) {
        if(isDel) {
            //删除当前角色权限树缓存
            sysAuthService.cleanAuthTree(Constant.ROLE,roleId);
        }else{
            //更新当前角色的权限树
            sysAuthService.putAuthTree(Constant.ROLE,roleId,sysRoleAuthService.getRoleAuthIds(roleId));
        }
        if(invUser)
        {
            //提取全部关联此角色的用户 无论是否删除角色（如删除某角色此处提取出用户清单后需要删除关联） 均要调取用户缓存刷新
            List<Long> userIds = sysUserRoleService.getRoleUserIds(roleId);
            if(isDel)
            {
                //删除角色关联
                sysUserRoleService.cleanUserRole(roleId);
            }
            //更新相关用户数据集
            for(Long userId:userIds)
            {
                //提取相关用户当前角色清单后更新用户权限集以及
                sysUserRoleService.putUserRoleAuth(userId);
                //更新用户权限树缓存
                sysAuthService.putAuthTree(Constant.USER,userId,sysUserAuthService.getUserAuthIds(userId));
                //更新JWT用户缓存集
                sysUserService.putJwtUser(userId);
            }
        }
    }
}
