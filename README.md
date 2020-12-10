# meal（一顿饭）
如名所示意那样，减少一顿饭的时间消耗。
主要为了简化自身开发的基础脚手架（基于SpringBoot）
本仓库不会包含过多的业务逻辑，方便快速铺开一个新业务系统，简化开发的基础脚手架（基于SpringBoot）
meal采用了前后端分离的形式进行构建，本仓库为服务端仓库
本仓库地址：
meal（[gitee地址](https://gitee.com/mebugs/meal) [github地址](https://github.com/mebugs/meal)）
前端仓库地址：
meal-ui（[gitee地址](https://gitee.com/mebugs/meal-ui) [github地址](https://github.com/mebugs/meal-ui)）
前端项目通过Vue+Vuex构建

# 基本配置
 - JDK 1.8 已知可能持续使用很长时间的版本
 - MySql 5.7 经典版本
 - Redis 6.0 比较新的一个版本（实际上目前项目中对版本要求没有十分严格）

# 主要涉及的第三方框架或插件
 - springboot 2.3.3 为未来构建SpringCloud提供基础
 - mybatis-plus 3.4.0 国内最棒的JAVA开源脚手架（基于MyBatise）
 - jjwt 0.9.1 快速构建JWT鉴权的插件包

# 基本能力
 - 用户 & 角色 基础关系
 - 提供了一些基础的配置，过滤器、拦截器、数据插件等
 - 提供完整的用户管理相关基本能力
 - 因为本仓库只是为了快速跑起来可以实现一些小项目的运行，并没有封装过多的业务逻辑

# 关键类说明
 - RedisConfig 封装Redis常用方法，继承CachingConfigurerSupport实现缓存能力（用于支持Cacheable、CachePut注解快速缓存）
 - MyBatisPlusConfig MyBatisPlus插件配置类（按照最新版本适配）
 - ExceptionHandler 通过AOP实现全局异常捕获后进行标准格式体返回（支持自定义异常）
 - JwtFilter JWT登录校验过滤器
 - JwtUserContext 登录用户上下文，主体数据存于Redis，在JwtFilter中提取存入（ThreadLocal）
 - @RolePermission 自定义注解，角色权限检验（注解于方法或类上）
 - RoleInterceptor 角色拦截器，配合自定义注解实现角色访问接口拦截
 - MyMvcConfig SpringBoot 2.0后添加校验器需要继承WebMvcConfigurer（addInterceptors）