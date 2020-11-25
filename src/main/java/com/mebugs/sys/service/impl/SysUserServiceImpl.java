package com.mebugs.sys.service.impl;

import com.mebugs.data.cons.Constant;
import com.mebugs.security.entity.JwtUser;
import com.mebugs.sys.entity.SysUser;
import com.mebugs.sys.mapper.SysUserMapper;
import com.mebugs.sys.service.ISysUserRoleService;
import com.mebugs.sys.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 获取安全用户信息 优先从Redis中获取 不存在则从数据获取
     * @param id
     * @return
     */
    @Cacheable(value="jwtUser",key="#id")
    @Override
    public JwtUser getJwtUser(Long id) {
        return getJwtUserFromDb(id);
    }
    /**
     * 设置安全用户信息 用户状态修改&角色变动是调用 更新Redis
     * @param id
     * @return
     */
    @CachePut(value="jwtUser",key="#id")
    @Override
    public JwtUser putJwtUser(Long id) {
        return getJwtUserFromDb(id);
    }

    /**
     * 从数据库获取安全用户信息
     * @param id
     * @return
     */
    private JwtUser getJwtUserFromDb(Long id)
    {
        SysUser sysUser = this.getById(id);
        //用户被锁定或删除
        if(sysUser.getStatus() > Constant.USER_OPEN)
        {
            return null;
        }
        JwtUser jwtUser = new JwtUser();
        BeanUtils.copyProperties(sysUser,jwtUser);
        //查询用户角色清单
        jwtUser.setRoles(sysUserRoleService.getUserRoles(id));
        return jwtUser;
    }
}
