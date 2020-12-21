package com.meals.sys.service.impl;

import com.meals.sys.entity.SysUserAuth;
import com.meals.sys.mapper.SysUserAuthMapper;
import com.meals.sys.service.ISysUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
