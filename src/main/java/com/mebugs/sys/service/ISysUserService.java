package com.mebugs.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mebugs.security.entity.JwtUser;
import com.mebugs.sys.entity.SysUser;
import com.mebugs.sys.to.UserDo;
import com.mebugs.sys.vo.UserVo;

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

    /**
     * 登录
     * @param sysUser
     * @return
     */
    String login(SysUser sysUser);

    /**
     * 保存账号数据 新增 修改 锁定 解锁 超管权限
     * @param userDo
     * @return
     */
    boolean saveOne(UserDo userDo);

    /**
     * 查询账号分页
     * @param page
     * @param userDo
     * @return
     */
    IPage<UserVo> getPage(Page page, UserDo userDo);

    /**
     * 更具账号ID查询用户信息
     * @param id
     * @return
     */
    UserVo getUserInfo(Long id);
}
