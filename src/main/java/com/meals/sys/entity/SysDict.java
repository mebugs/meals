package com.meals.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 公共字典表
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典值（必须配置为数字）
     */
    private Integer value;

    /**
     * 拓展KEY（为某些特殊场景补充字符串value）
     */
    private String exKey;

    /**
     * 字典类型（请将此值配置常量）
     */
    private Integer type;

    /**
     * 字段等级
     */
    private Integer level;

    /**
     * 默认为0 反之则为父级ID
     */
    private Long pid;

    /**
     * 字典启用状态 1启用 2废弃
     */
    private Integer status;


}
