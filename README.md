SpringBoot开发骨架项目
===
本项目的目的是最小化集成各种必须组件和通用配置，可以作为任何项目开发的骨架。

# 组件

技术 | 说明                | 官网
----|-------------------|----
Spring Boot | 容器+MVC框架          | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
Spring Security | 认证和授权框架           | [https://spring.io/projects/spring-security](https://spring.io/projects/spring-security)
MyBatis | ORM框架             | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
MyBatisPlus | ORM框架扩展           | https://mybatis.plus/
bean-searcher | 复杂查询而生            | https://gitee.com/ejlchina-zhxu/bean-searcher
sa-token| 轻量级 Java 权限认证框架   | https://gitee.com/dromara/sa-token
Spring-Retry| 重试                | https://github.com/spring-projects/spring-retry
jib | Java应用打包成Docker镜像 | https://github.com/GoogleContainerTools/jib
okhttps | okhttp的封装框架       | https://okhttps.ejlchina.com/

gradle教程 https://hub.fastgit.org/GradleCN/GradleSide

# 配置

技术 | 说明 |
----|----|
国际化配置 |

### 命令打包，跳过TEST
```shell script
./gradlew clean bootJar -x test
```
```shell
./gradlew dependencyInsight --dependency mybatis
```
> 需要解决多工程的依赖分析
>

##  杀后台进程
```shell
ps -ef|grep 'java'|grep 'server'|grep 'base-server'|grep -v 'grep'|awk '{print $2}'|xargs -tI {} kill -9 {}
```


## 后台挂着启动运行

```shell script
nohup java  -Dfile.encoding=utf-8 -Duser.timezone=GMT+08 -jar /home/ubuntu/eric/print/server-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod  > nohup.log 2>&1 &
```

或者`setsid`

```shell script
setsid java  -Dfile.encoding=utf-8 -Duser.timezone=GMT+08 -jar /home/ubuntu/eric/print/server-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod  > nohup.log 2>&1 &
```