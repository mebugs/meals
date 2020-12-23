package com.meals.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.meals.sys.entity.SysUserAuth;
import com.meals.sys.mapper.SysUserAuthMapper;
import com.meals.sys.service.ISysUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户授权清单（为了方便查询，用户授权收据会直接生成好） 服务实现类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
@Service
public class SysUserAuthServiceImpl extends ServiceImpl<SysUserAuthMapper, SysUserAuth> implements ISysUserAuthService {

    /**
     * 更新用户权限集
     * @param id
     * @param userAuthIds
     */
    @Override
    public void updateUserAuth(Long id, List<Long> userAuthIds) {
        this.remove(Wrappers.<SysUserAuth>lambdaQuery().eq(SysUserAuth::getUid,id));
        List<SysUserAuth> newAuth = new ArrayList<>();
        for(Long aid : userAuthIds)
        {
            SysUserAuth userAuth = new SysUserAuth();
            userAuth.setUid(id);
            userAuth.setAid(aid);
            newAuth.add(userAuth);
        }
        this.saveBatch(newAuth);
    }

    /**
     * 获取用户权限集
     * @param id
     * @return
     */
    @Override
    public List<Long> getUserAuthIds(Long id) {
        return this.baseMapper.getUserAuthIds(id);
    }

    /**
     * 获取用户权限KEY 授权专用
     * @param id
     * @return
     */
    @Override
    public List<String> getUserAuthKeys(Long id) {
        return this.baseMapper.getUserAuthKeys(id);
    }
}
