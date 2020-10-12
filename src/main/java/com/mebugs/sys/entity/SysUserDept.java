package com.mebugs.sys.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户部门表
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 部门ID
     */
    private Long did;


}
