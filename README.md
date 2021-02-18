# 基本信息

Meal'S是对Meal工程的增强版。

Meal'S依旧不会包含过多的业务逻辑，提供用户-角色-权限集的基础管理能力。

Meal'S可以实现自定义角色并为角色自由赋予权限，最终用户所具备的权限集是其所选择的角色权限集的并集。

Meal'S工程为了提升效率设置了相对科学的缓存命名空间，下文中会详细说明。

服务端工程地址：[Gitee](https://gitee.com/mebugs/meals "Gitee") [GitHub](https://github.com/mebugs/meals "GitHub")

前端工程地址：[Gitee](https://gitee.com/mebugs/meals-ui "Gitee") [GitHub](https://github.com/mebugs/meals-ui "GitHub")

## 特别致谢

Meal'S服务端使用[MyBatis-Plus](https://mybatis.plus/ "MyBatis-Plus")作为基础脚手架。

Meal'S服务端使用[jjwt](https://github.com/jwtk/jjwt "jjwt")实现JWT快速加解密。

Meal'S-ui前端工程Fork自[vue-admin-template](https://github.com/PanJiaChen/vue-admin-template "vue-admin-template")。

本人是JAVA服务端开发，[@花裤衩](https://github.com/PanJiaChen "@花裤衩")的前端系列开源项目为我学习Vue提供非常大的帮助，非常感谢！

# 功能清单

Meal'S工程仅提供了最基本的账号-角色-权限集支撑能力，没有涉及其他功能页面。

为了提供更为友好的体验，提供了账号查看自身权限集的能力。

## 登陆页

![](https://www.mebugs.com/ /blog/upload/post/POST_1613638188.jpg)

## 工作中心

![](https://www.mebugs.com/ /blog/upload/post/POST_1613639826.jpg)

## 个人中心

![](https://www.mebugs.com/ /blog/upload/post/POST_1613638233.jpg)

## 账号管理

![](https://www.mebugs.com/ /blog/upload/post/POST_1613638247.jpg)

## 角色管理

![](https://www.mebugs.com/ /blog/upload/post/POST_1613638301.jpg)

![](https://www.mebugs.com/ /blog/upload/post/POST_1613638311.jpg)

## 权限集查看

![](https://www.mebugs.com/ /blog/upload/post/POST_1613638354.jpg)


## 代码生成

![](https://www.mebugs.com/ /blog/upload/post/POST_1613638325.jpg)

![](https://www.mebugs.com/ /blog/upload/post/POST_1613638372.jpg)


# 开发说明
## 服务端
### 外部依赖

1. JDK 1.8 已知可能持续使用很长时间的版本
2. MySql 5.7 经典版本
3. Redis 6.0 比较新的一个版本（实际上目前项目中对版本要求没有十分严格）

### 基本缓存结构说明

Meal'S才用了Redis作为默认缓存工具，为了提升鉴权和查询性能，主要实现两大缓存。

权限树缓存：用于界面渲染树形结构。

权限集缓存：用户服务端接口鉴权。

设置缓存的主要目的用于降低数据库查询压力，尤其是查询权限树时，需要组装树形结构。

![](https://www.mebugs.com/ /blog/upload/post/POST_1613638634.jpg)

### 工程目录

![](https://www.mebugs.com/ /blog/upload/post/POST_1613639006.jpg)

### 核心类

 - MyBatisPlusConfig：MyBatis-Plus配置类，配置Mapper扫描位置、分页插件等。
 - RedisConfig：配置Redis为默认缓存工具及常见操作Bean配置。
 - CodeMakerUtils：代码生成器的执行类
 - JwtUserContext：JWT安全用户上下文对象（全局获取登录用户信息）
 - JwtFilter：Jwt过滤器，核心过滤器（登录、授权、拦截）
 - ExceptionHandler：全局异常捕获处理（AOP实现，支持自定义异常处理）
 - AuthorizeInterceptor：权限拦截器（基于自定义注解@Authorize实现鉴权）
 - EncryptionUtils：盐值加密工具（账号的密码生成，不可逆加密算法）
 - SysInitTask：系统启动任务（当前主要缓存全部权限树）
 - ThreadAuthRole：角色权限变动线程（根据变动的角色ID更新角色、涉及用户权限相关缓存）
 - ThreadPoolConfig：基于Spring的线程池配置

### 开发说明

1. 数据库初始化刷库
2. application-dev.yml配置您的数据库信息和redis信息（默认localhost）
3. 启动App类

启动示意图：
![](https://www.mebugs.com/ /blog/upload/post/POST_1613639279.jpg)

## 前端
### 外部依赖

1. Node.js（基本工具）
2. Git（多数前端工程依赖会需要调用Git下载）

### 工程目录

因为Meals-ui和Meal-ui的工程目录结构没有产生变动，因此此处直接引用了Meal-ui的工程目录结构图。

![](https://www.mebugs.com/ /blog/upload/post/POST_1613615776.jpg)

![](https://www.mebugs.com/ /blog/upload/post/POST_1613615784.jpg)

### 相对原本的改动

1. 移除Mocks模拟返回服务（调用meal服务端工程）
2. 移除测试模块以及相关依赖
3. 移除Cookies插件依赖，Cookies统一替换为window.localStorage
4. 补充动态路由由服务端返回的用户角色集Roles控制
5. 移除自定义icon（svg格式），替换为element-ui的图标，直接修改了Sidebar/Item.vue
6. 核心方法getUserBase获取用户基础信息src/store/modules/user.js getInfo()
7. 增加v-droll可以自由在屏幕范围内拖动弹窗（src/directive/droll.js）

以下为Meals-ui基于Meal-ui增加的主要改动。

1. 增加自定义指令v-authorize判断界面按钮菜单是否具备权限
2. 修改路由rolse为auth（路由指定唯一auth标示需要的权限）
3. 代码生成器增强，支持模糊查询表名，支持多选生成代码
4. 对原本的element-ui主题配色进行了修改

```html
<!-- v-authorize使用方式 -->
<button type="button" v-authorize="'SYS-USER-M'" />
```


### 启动界面

![](https://www.mebugs.com/ /blog/upload/post/POST_1613616164.jpg)

### 开发说明

```bash
# 依赖下载
npm install
# 开发模式
npm run dev
# 生产编译
npm run build
```

# 关于个人

[关于米虫先生](http://www.mebugs.com/some/about.html "关于米虫先生")
