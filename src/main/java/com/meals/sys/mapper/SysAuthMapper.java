package com.meals.sys.mapper;

import com.meals.sys.entity.SysAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限集表 Mapper 接口
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
public interface SysAuthMapper extends BaseMapper<SysAuth> {

    /**
     * 根据传入的已知权限ID获取权限树
     * @param level 菜单按钮等级
     * @param type 类型
     * @param filter 数据范围
     * @param pid 父级ID
     * @return
     */
    List<SysAuth> getAuthTreeByFilter(Integer type,Integer level, List<Long> filter,Long pid);
}
