package com.meals.sys.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色授权清单
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long rid;

    /**
     * 授权ID
     */
    private Long aid;


}
