package com.meals.sys.vo;

import com.meals.sys.entity.SysAuth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户权限集实体
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAuthVo {
    /**
     * 快速构造
     * @param name
     * @param tree
     */
    public UserAuthVo(String name, List<SysAuth> tree){
        this.roleName = name;
        this.authTree = tree;
    }
    private String roleName;

    private List<SysAuth> authTree;
}
