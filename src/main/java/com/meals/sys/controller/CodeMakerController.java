package com.meals.sys.controller;

import com.meals.data.response.R;
import com.meals.security.permission.Authorize;
import com.meals.sys.entity.SysTableCodeConfig;
import com.meals.sys.service.SysCodeMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  代码生成服务
 * </p>
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-27
 */
@RestController
@RequestMapping("/code")
public class CodeMakerController {
    @Autowired
    private SysCodeMakerService sysCodeMakerService;

    /**
     * 获取数据库的所有表
     * @return
     */
    @Authorize("DEV-CODE")
    @GetMapping("/getTableList")
    public R getTableList(String tableName) {
        return R.ok(sysCodeMakerService.findTableList(tableName));
    }

    /**
     * 获取表中的所有字段以及列属性
     * @param tableName
     * @return
     */
    @Authorize("DEV-CODE-M")
    @GetMapping("/getTableColumnList")
    public R getTableColumnList(String tableName) {
        return R.ok(sysCodeMakerService.findColumnList(tableName));
    }

    /**
     * 生成代码
     * @param sysTableCodeConfig
     * @return
     */
    @Authorize("DEV-CODE-M")
    @PostMapping("/make")
    public R makeCode(@RequestBody SysTableCodeConfig sysTableCodeConfig){
        return R.ok(sysCodeMakerService.makeCode(sysTableCodeConfig));
    }
}
