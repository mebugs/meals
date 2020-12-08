package com.mebugs.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mebugs.sys.entity.SysUser;
import com.mebugs.sys.to.UserDo;
import com.mebugs.sys.vo.UserVo;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询账号分页
     * @param page
     * @param userDo
     * @return
     */
    IPage<UserVo> getPage(Page page, UserDo userDo);
}
