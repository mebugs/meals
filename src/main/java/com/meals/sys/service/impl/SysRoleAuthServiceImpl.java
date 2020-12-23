package com.meals.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.meals.sys.entity.SysRoleAuth;
import com.meals.sys.mapper.SysRoleAuthMapper;
import com.meals.sys.service.ISysRoleAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    /**
     * 先删除当前ID下的权限集 然后批量刷入新的权限集
     * @param id
     * @param authIds
     */
    @Override
    @Transactional
    public void updateRoleAuth(Long id, List<Long> authIds) {
        this.remove(Wrappers.<SysRoleAuth>lambdaQuery().eq(SysRoleAuth::getRid,id));
        List<SysRoleAuth> newAuth = new ArrayList<>();
        for(Long aid : authIds)
        {
            SysRoleAuth roleAuth = new SysRoleAuth();
            roleAuth.setRid(id);
            roleAuth.setAid(aid);
            newAuth.add(roleAuth);
        }
        this.saveBatch(newAuth);
    }
}
