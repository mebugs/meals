package com.meals.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meals.data.cons.Constant;
import com.meals.security.context.JwtUserContext;
import com.meals.security.entity.JwtUser;
import com.meals.security.utils.EncryptionUtils;
import com.meals.security.utils.JwtUtils;
import com.meals.sys.entity.SysRole;
import com.meals.sys.entity.SysUser;
import com.meals.sys.mapper.SysUserMapper;
import com.meals.sys.service.*;
import com.meals.sys.to.UserDo;
import com.meals.sys.vo.UserAuthVo;
import com.meals.sys.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleAuthService sysRoleAuthService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ISysUserAuthService sysUserAuthService;
    @Autowired
    private ISysAuthService sysAuthService;

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
    public JwtUser putJwtUser(Long id) {return getJwtUserFromDb(id);}

    /**
     * 登录
     * @param sysUser
     * @return 登录成功返回JWT密码 登录失败返回NULL 不提供详细的错误提醒
     */
    @Override
    public String login(SysUser sysUser) {
        //根据账号密码查询未锁定的用户
        SysUser query = this.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getName,sysUser.getName()).eq(SysUser::getStatus, Constant.USER_OPEN).last("limit 1"));
        //账号未被锁定且匹配成功
        if(query!=null)
        {
            //密码计算
            String inputPwd = EncryptionUtils.encode(sysUser.getPassword()+query.getSalt());
            if(query.getPassword().equals(inputPwd))
            {
                //组装JWT数据 登陆时为了提升效率不对JwtUser进行封装 首次封装放在过滤器中完成
                return jwtUtils.generateToken(query.getId());
            }
        }
        return null;
    }

    /**
     * 保存账号数据 新增 修改 锁定 解锁 超管权限
     * @param userDo
     * @return
     */
    @Override
    @Transactional
    public boolean saveOne(UserDo userDo) {
        SysUser sysUser = new SysUser();
        // 锁定或解锁的status字段会自动拷贝到sysUser对象
        BeanUtils.copyProperties(userDo,sysUser);
        if(userDo.getId()==null || userDo.getResetPwd()!=null)
        {
            //新增用户 数据库status默认值为1
            //或触发密码重置操作
            //创建密码 默认密码为账号+salt的加密值
            String salt = EncryptionUtils.getSalt();
            sysUser.setSalt(salt);
            String pwd = EncryptionUtils.encode(userDo.getName()+salt);
            sysUser.setPassword(pwd);
        }
        this.saveOrUpdate(sysUser);
        if(userDo.getRoles()!=null && userDo.getRoles().size()>0)
        {
            //更新角色清单 同时组装用户权限清单 发起线程刷新权限树和JWT缓存
            sysUserRoleService.putUserRoles(sysUser.getId(),userDo.getRoles());
        }
        //更新JWT用户缓存集
        sysUserService.putJwtUser(sysUser.getId());
        return true;
    }

    /**
     * 查询账号分页
     * @param page
     * @param userDo
     * @return
     */
    @Override
    public IPage<UserVo> getPage(Page page, UserDo userDo) {
        return this.baseMapper.getPage(page,userDo);
    }

    /**
     * 更具账号ID查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserVo getUserInfo(Long id) {
        SysUser sysUser = this.getById(id);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(sysUser,userVo);
        userVo.setRoles(sysUserRoleService.getUserRoleIds(id));
        return userVo;
    }

    /**
     * 获取自己的用户信息
     * @return
     */
    @Override
    public UserVo getMine() {
        Long userId = JwtUserContext.getUser().getId();
        SysUser sysUser = this.getById(userId);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(sysUser,userVo);
        List<UserAuthVo> allAuthTree = new ArrayList<>();
        //获取我的权限集
        List<Long> myAuthIds = sysUserAuthService.getUserAuthIds(userId);
        //获取用户权限集
        UserAuthVo myAuth = new UserAuthVo(Constant.ALL_AUTH,sysAuthService.getAuthTree(Constant.USER,userId,myAuthIds));
        allAuthTree.add(myAuth);
        //获取用户各角色权限集
        List<SysRole> myRoles = sysUserRoleService.getUserRoleObj(userId);
        for(SysRole role : myRoles)
        {
            //提取该角色的权限清单
            List<Long> roleAuthIds = sysRoleAuthService.getRoleAuthIds(role.getId());
            UserAuthVo roleAuth = new UserAuthVo(role.getName(),sysAuthService.getAuthTree(Constant.ROLE,role.getId(),roleAuthIds));
            allAuthTree.add(roleAuth);
        }
        userVo.setUserAuthVos(allAuthTree);
        return userVo;
    }

    /**
     * 用户自行修改密码
     * @param nowPwd
     * @param newPwd
     * @return
     */
    @Override
    public boolean resetPwd(String nowPwd, String newPwd) {
        SysUser sysUser = this.getById(JwtUserContext.getUser().getId());
        //验证旧密码
        String inputPwd = EncryptionUtils.encode(nowPwd+sysUser.getSalt());
        if(sysUser.getPassword().equals(inputPwd))
        {
            //密码验证通过
            //修改密码
            SysUser update = new SysUser();
            update.setId(sysUser.getId());
            String salt = EncryptionUtils.getSalt();
            update.setSalt(salt);
            String pwd = EncryptionUtils.encode(newPwd+salt);
            update.setPassword(pwd);
            return this.updateById(update);
        }
        return false;
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
        //查询用户角色清单 待移除
        jwtUser.setRoles(sysUserRoleService.getUserRoleKeys(id));
        //查询用户准确权限集
        jwtUser.setAuthKeys(sysUserAuthService.getUserAuthKeys(id));
        return jwtUser;
    }
}
