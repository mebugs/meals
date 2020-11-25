package com.mebugs.sys.service;

import com.mebugs.security.entity.JwtUser;
import com.mebugs.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 获取安全用户信息 优先从Redis中获取
     * @param id
     * @return
     */
    JwtUser getJwtUser(Long id);

    /**
     * 设置安全用户信息 用户状态修改&角色变动是调用 更新Redis
     * @param id
     * @return
     */
    JwtUser putJwtUser(Long id);
}
