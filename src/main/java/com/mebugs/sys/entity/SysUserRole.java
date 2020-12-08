package com.mebugs.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 系统用户角色表
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "uid", type = IdType.INPUT)
    private Long uid;

    /**
     * 角色ID
     */
    private Long rid;


}
