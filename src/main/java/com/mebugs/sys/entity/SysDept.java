package com.mebugs.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统部门表
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 部门名
     */
    private String name;

    /**
     * 部门权限
     */
    private Long role;


}
