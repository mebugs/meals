package com.meals.sys.service.impl;

import com.meals.sys.entity.SysRoleAuth;
import com.meals.sys.mapper.SysRoleAuthMapper;
import com.meals.sys.service.ISysRoleAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色授权清单 服务实现类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
@Service
public class SysRoleAuthServiceImpl extends ServiceImpl<SysRoleAuthMapper, SysRoleAuth> implements ISysRoleAuthService {

    /**
     * 查询角色勾选的权限集
     * @param id
     * @return
     */
    @Override
    public List<Long> getRoleAuthIds(Long id) {
        return this.baseMapper.getRoleAuthIds(id);
    }
}
