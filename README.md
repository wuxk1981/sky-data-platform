

## 天下科技游戏数据服务平台
## SpringCloud && Vue 分布式微架构 + 前后端分离解决方案
> **目前正在开发阶段, 请持续关注, 相关文档会根据项目进度逐渐完善**

## 技术栈
- 后端：SpringCloud、oAuth2、JWT、Eureka、Fegin、Ribbon、Zuul、Hystrix...
- 前端：Vue2.x、iView、Webpack...

## 预览

## 目录结构

    Fastjee:
          ├─sky-auth-service `(授权中心:8500)`
          ├─sky-common    (通用模块)
          ├─sky-config-server    `(配置中心:8888)`
          ├─sky-config-info   (配置中心指向的git仓库)
          ├─sky-service-gateway   `(对外暴露的API服务网关:8082)`
          ├─sky-log-persist (持久化工具-Mongodb集成实现:8080)
          ├─sky-service-registry  `(Eureka服务注册中心:8761)`
          ├─sky-usercenter  `(用户中心微服务:5004)`
          └─sky-webui `(基于vue的前端实现, 使用iview-admin脚手架:9500)`
              ├─build (项目构建配置)
              ├─src
              │  ├─api  (API请求封装)
              │  ├─assets (静态资源)
              │  ├─components (自定义组件)
              │  ├─libs (工具文件)
              │  ├─locale (多语言文件)
              │  ├─router (路由配置)
              │  ├─store  (状态管理)
              │  ├─styles (样式文件)
              │  ├─template (模板文件)
              │  ├─utils  (自定义工具)
              │  ├─vendors (公共库文件)
              │  └─views  (页面文件)
              └─static  (静态资源)

## 开发进度

 - 2018/08/27： 初次提交, 完成项目搭建

