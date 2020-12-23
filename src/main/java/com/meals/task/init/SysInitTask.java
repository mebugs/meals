package com.meals.task.init;

import com.meals.data.cons.Constant;
import com.meals.sys.service.ISysAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 系统模块启动初始化任务
 * 1.启动初始化全部权限集以及全部权限树
 * 2.启动初始化全部字典集（以Type为KEY）
 * 3.启动初始化全部区域数据以及全部区域树
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-19
 */
@Component
public class SysInitTask  implements ApplicationRunner {
    @Autowired
    private ISysAuthService sysAuthService;

    /**
     * 启动执行方法
     * @param args
     */
    @Override
    public void run(ApplicationArguments args)  {
        //执行全部权限集缓存 弃用
        //sysAuthService.getAllAuth();
        //启动PUT权限TREE
        sysAuthService.putAuthTree("All", Constant.LONG_ZERO,null);
    }
}
