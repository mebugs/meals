package com.meals.task.thread;

import com.meals.data.cons.Constant;
import com.meals.sys.service.ISysAuthService;
import com.meals.sys.service.ISysUserAuthService;
import com.meals.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 角色权限集缓存更新线程池
 *
 * @author mebugs.com/米虫先生
 * @since 2020-12-23
 */
@Component
public class ThreadAuthUser {

    @Autowired
    private ISysAuthService sysAuthService;
    @Autowired
    private ISysUserAuthService sysUserAuthService;
    @Autowired
    private ISysUserService sysUserService;
    /**
     * 根据根据用户ID更新用户权限树缓存和JWT缓存
     * @param userId 用户ID
     */
    @Async("threadPool")
    public void syncUserAuth(Long userId) {
        //更新用户权限树缓存
        sysAuthService.putAuthTree(Constant.USER,userId,sysUserAuthService.getUserAuthIds(userId));
        //更新JWT用户缓存集
        sysUserService.putJwtUser(userId);
    }
}
