package com.meals.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限集表
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 授权KEY
     */
    @TableField("authKey")
    private String authKey;

    /**
     * 授权名称
     */
    private String name;

    /**
     * 权限等级（当前类型下的等级）
     */
    private Integer level;
    /**
     * 权限类型 1菜单 2按钮/接口
     */
    private Integer type;

    /**
     * 父级ID（生成树）
     */
    private Long pid;

    /**
     * 权限状态 1启用 2锁定 3删除 锁定和删除涉及异步刷新引用此权限及所有子权限的数据
     */
    private Integer status;

    @TableField(exist = false)
    private List<SysAuth> children;
}
