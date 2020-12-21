package com.meals.sys.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户 前端传递实体
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDo implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 账号
     */
    private String name;


    /**
     * 角色清单
     */
    private List<Long> roles;

    /**
     * 查询角色ID
     */
    private Long roleId;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 重置密码
     */
    private Integer resetPwd;
}
