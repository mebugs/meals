package com.meals.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.meals.data.cons.Constant;
import com.meals.sys.entity.SysAuth;
import com.meals.sys.mapper.SysAuthMapper;
import com.meals.sys.service.ISysAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限集表 服务实现类
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
@Service
public class SysAuthServiceImpl extends ServiceImpl<SysAuthMapper, SysAuth> implements ISysAuthService {


    /**
     * 获取全部权限集 redis缓存方法
     */
    @Cacheable(value="AuthList",key="'All'")
    @Override
    public List<SysAuth> getAllAuth() {
        return getAll();
    }

    /**
     * 获取权限树 接受三种传参
     * 分别是全部权限树 角色的权限树 用户的权限树
     * 缓存效果AuthTree0All AuthTree2Role AuthTree5User
     * @param type all/role/user
     * @param id 0/roleId/userId
     * @param have null/roleAuths/userAuths
     * @return
     */
    @Cacheable(value="AuthTree",key="#id+'-'+#type")
    @Override
    public List<SysAuth> getAuthTree(String type, Long id, List<Long> have) {
        return getAuthTreeByFilter(have);
    }

    /**
     * 刷新权限树 入参同GET方法
     * @param type
     * @param id
     * @param have
     * @return
     */
    @CachePut(value="AuthTree",key="#id+'-'+#type")
    @Override
    public List<SysAuth> putAuthTree(String type, Long id, List<Long> have) {
        return getAuthTreeByFilter(have);
    }
    /**
     * 清空某个角色或用户的权限树
     * @param type
     * @param id
     */
    @CacheEvict(value="AuthTree",key="#id+'-'+#type")
    @Override
    public void cleanAuthTree(String type, Long id) {
        //清空缓存 不需要任何代码
    }

    /**
     * 查询全部可用权限集
     * @return
     */
    private List<SysAuth> getAll()
    {
        return this.list(Wrappers.<SysAuth>lambdaQuery().eq(SysAuth::getStatus, Constant.USED));
    }

    /**
     * 根据传入的已知权限ID获取权限树
     * @param filter
     * @return
     */
    private List<SysAuth> getAuthTreeByFilter(List<Long> filter){
        //获取一级菜单 PID为-1的数据 （当前不设计多级菜单，目前仅提供两级菜单
        List<SysAuth> menuOne = this.baseMapper.getAuthTreeByFilter(Constant.LEVEL_ONE,Constant.LEVEL_ONE,filter,Constant.LONG_MONE);
        for(SysAuth one : menuOne)
        {
            //获取2级菜单
            List<SysAuth> menuTwo = this.baseMapper.getAuthTreeByFilter(Constant.LEVEL_ONE,Constant.LEVEL_TWO,filter,one.getId());
            one.setChildren(menuTwo);
            for(SysAuth two : menuTwo)
            {
                //获取按钮权限
                List<SysAuth> btnOne = this.baseMapper.getAuthTreeByFilter(Constant.LEVEL_TWO,Constant.LEVEL_ONE,filter,two.getId());
                two.setChildren(btnOne);
                //按钮接口权限目前仅做1级 如需拓展从此处追加
            }
        }
        return menuOne;
    }
}
