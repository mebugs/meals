package com.meals.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色配置表
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    private String name;
    /**
     * 角色KEY
     */
    private String roleKey;
    /**
     * 备注
     */
    private String remark;


}
