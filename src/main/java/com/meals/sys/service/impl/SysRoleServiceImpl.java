package com.meals.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meals.data.cons.Constant;
import com.meals.sys.entity.SysRole;
import com.meals.sys.mapper.SysRoleMapper;
import com.meals.sys.service.ISysAuthService;
import com.meals.sys.service.ISysRoleAuthService;
import com.meals.sys.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meals.task.thread.ThreadAuthRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysRoleAuthService sysRoleAuthService;
    @Autowired
    private ISysAuthService sysAuthService;
    @Autowired
    private ThreadAuthRole threadAuthRole;


    /**
     * 获取角色分页
     * @return
     */
    @Override
    public IPage<SysRole> rolePage(Page page, String name) {
        LambdaQueryWrapper<SysRole> query = Wrappers.<SysRole>lambdaQuery();
        if(StringUtils.isNotBlank(name))
        {
            query.like(SysRole::getName,name);
        }
        return this.page(page,query);
    }

    /**
     * 获取角色的完整信息
     * @param id
     * @return
     */
    @Override
    public SysRole getRoleInfo(Long id) {
        SysRole role = this.getById(id);
        if(role!=null)
        {
            //查询权限勾选集
            List<Long> roleAuthIds = sysRoleAuthService.getRoleAuthIds(role.getId());
            role.setAuthIds(roleAuthIds);
            //查询权限树
            role.setAuthTree(sysAuthService.getAuthTree(Constant.ROLE,role.getId(),roleAuthIds));
            return role;
        }
        return null;
    }

    /**
     * 保存角色数据 新增修改通用方法
     * @return
     */
    @Override
    public boolean saveOne(SysRole sysRole) {
        boolean changUserCache = false;
        if(sysRole.getId() != null) {
            //表示需要同步刷新相关用户的缓存信息
            changUserCache = true;
        }
        this.saveOrUpdate(sysRole);
        //无论新增还是修改先保存主表 再提交关联表
        sysRoleAuthService.updateRoleAuth(sysRole.getId(),sysRole.getAuthIds());
        //启动异步线程去刷新缓存
        threadAuthRole.syncRoleAuth(sysRole.getId(),changUserCache,false);
        return true;
    }
    /**
     * 删除角色
     * @return
     */
    @Override
    public boolean delRole(Long id) {
        this.removeById(id);
        //启动异步线程去刷新缓存
        threadAuthRole.syncRoleAuth(id,true,true);
        return true;
    }
}
