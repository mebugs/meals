# meal（一顿饭）
如名所示意那样，主要为了简化自身开发的基础构建

不会包含过多的业务逻辑，方便自己能够快速铺开一个新业务系统的基本搭建配置

meal采用了前后端分离的形式进行构建

当前仓库为服务端仓库

前端工程为VUE构建：meal-ui


# 基本配置
 - JDK 1.8 已知可能持续使用很长时间的版本
 - MySql 5.7 经典版本
 - Redis 6.0 比较新的一个版本（实际上目前项目中对版本要求没有十分严格）

# 主要涉及的第三方框架或插件
 - springboot 2.3.3 为未来构建SpringCloud提供基础
 - mybatis-plus 3.4.0 国内最棒的JAVA开源脚手架（基于MyBatise）
 - jjwt 0.9.1 快速构建JWT鉴权的插件包

# 基本能力
 - 用户 - 部门 - 角色 管理
 - 因为本仓库只是为了快速跑起来可以实现一些小项目的运行，并没有封装过多的东西