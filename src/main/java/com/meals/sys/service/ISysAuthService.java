package com.meals.sys.service;

import com.meals.sys.entity.SysAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 权限集表 服务类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
public interface ISysAuthService extends IService<SysAuth> {

    /**
     * 获取全部权限树 redis缓存方法
     */
    List<SysAuth> getAllAuth();

    /**
     * 获取权限树 接受三种传参
     * 分别是全部权限树 角色的权限树 用户的权限树
     * @param type all/role/user
     * @param id 0/roleId/userId
     * @param have null/roleAuths/userAuths
     * @return
     */
    List<SysAuth> getAuthTree(String type,Long id,List<Long> have);

    /**
     * 刷新权限树 入参同GET方法
     * @param type
     * @param id
     * @param have
     * @return
     */
    List<SysAuth> putAuthTree(String type,Long id,List<Long> have);
}
