package com.meals.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户授权清单（为了方便查询，用户授权收据会直接生成好）
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "uid", type = IdType.INPUT)
    private Long uid;

    /**
     * 授权ID
     */
    private Long aid;


}
