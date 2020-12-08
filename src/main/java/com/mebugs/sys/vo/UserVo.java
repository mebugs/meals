package com.mebugs.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统用户 账号视图对象
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 账号
     */
    private String name;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 所属角色名组合字串
     */
    private String roleStr;

    /**
     * 所属角色的ID
     */
    private List<Long> roles;


}
