package com.meals.sys.mapper;

import com.meals.sys.entity.SysUserAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户授权清单（为了方便查询，用户授权收据会直接生成好） Mapper 接口
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
public interface SysUserAuthMapper extends BaseMapper<SysUserAuth> {

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
