# 前言
meals是meal工程的增强版本，不再仅仅通过角色进行授权控制，而是细分到具体的菜单、按钮、元素的权限配置。

关于meal:

meal（[gitee地址](https://gitee.com/mebugs/meal) [github地址](https://github.com/mebugs/meal)）

meal-ui（[gitee地址](https://gitee.com/mebugs/meal-ui) [github地址](https://github.com/mebugs/meal-ui)）

# meals
meals（就餐时光），主要对业务系统基础的用户-角色-权限-授权支撑体系进行构建，减少新业务系统搭建的时间。

meals不会包含过多的业务逻辑，主要为了方便快速铺开一个新业务系统，简化开发的基础脚手架（基于SpringBoot）。

meals采用了前后端分离的形式进行构建，本仓库为服务端仓库。

本仓库地址：

meals（[gitee地址](https://gitee.com/mebugs/meals) [github地址](https://github.com/mebugs/meals)）

前端仓库地址：

meals-ui（[gitee地址](https://gitee.com/mebugs/meals-ui) [github地址](https://github.com/mebugs/meals-ui)）

前端项目通过Vue+Vuex构建。

meals-ui通过动态路由以及自定义指令和公共授权方法实现页面的权限管理与维护。

# 基本配置
 - JDK 1.8 已知可能持续使用很长时间的版本
 - MySql 5.7 经典版本
 - Redis 6.0 比较新的一个版本（实际上目前项目中对版本要求没有十分严格）

# 主要涉及的第三方框架或插件
 - springboot 2.3.3 为未来构建SpringCloud提供基础
 - mybatis-plus 3.4.0 国内最棒的JAVA开源脚手架（基于MyBatise）
 - jjwt 0.9.1 快速构建JWT鉴权的插件包

# 基本能力
**本工程基于meal继续开发，部分已有能力不再此处赘述**
 - 增加权限集管理，提供权限集的界面配置能力
 - 增加角色管理，可以自定义角色，角色不再仅仅是预置
 - 增加角色权限集关联，可以为角色自由配置权限集
 - 支持账号多选角色，同时增加账号权限集查询，可以看到当前账号具备的权限清单

# 关键类说明
 - RedisConfig 封装Redis常用方法，继承CachingConfigurerSupport实现缓存能力（用于支持Cacheable、CachePut注解快速缓存）
 - MyBatisPlusConfig MyBatisPlus插件配置类（按照最新版本适配）
 - ExceptionHandler 通过AOP实现全局异常捕获后进行标准格式体返回（支持自定义异常）
 - JwtFilter JWT登录校验过滤器
 - JwtUserContext 登录用户上下文，主体数据存于Redis，在JwtFilter中提取存入（ThreadLocal）
 - MyMvcConfig SpringBoot 2.0后添加校验器需要继承WebMvcConfigurer（addInterceptors）
 - @Authorize 自定义注解（修订自meal的@RolePermission），权限检验（注解于方法或类上）
 - AuthorizeInterceptor 权限拦截器（修订自meal的RoleInterceptor），配合自定义注解实现角色访问接口拦截

# 启动说明
1. MySql启动并刷库，新建数据库meals（如果改名，请修改application-dev.yml中的配置文件，数据可用户名和密码自行配置）
2. Redis数据库需要优先启动，工程中默认配置在localhost（如果是外部redis，请修改application-dev.yml中的配置文件）
3. 通过App类启动服务端项目（本工程打包配置的是jar包，可在pom.xml中自行修改）
4. 前端启动移步 meals-ui

# 前端仓库地址

meals-ui（[gitee地址](https://gitee.com/mebugs/meals-ui) [github地址](https://github.com/mebugs/meals-ui)）

前端项目通过Vue+Vuex构建。

meals-ui通过动态路由以及自定义指令和公共授权方法实现页面的权限管理与维护。