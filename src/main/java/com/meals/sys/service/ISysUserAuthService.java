package com.meals.sys.service;

import com.meals.sys.entity.SysUserAuth;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户授权清单（为了方便查询，用户授权收据会直接生成好） 服务类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
public interface ISysUserAuthService extends IService<SysUserAuth> {

    /**
     * 更新用户权限集
     * @param id
     * @param userAuthIds
     */
    void updateUserAuth(Long id, List<Long> userAuthIds);

    /**
     * 获取用户权限集
     * @param id
     * @return
     */
    List<Long> getUserAuthIds(Long id);

    /**
     * 获取用户权限KEY 授权专用
     * @param id
     * @return
     */
    List<String> getUserAuthKeys(Long id);
}
