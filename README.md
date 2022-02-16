SpringBoot开发骨架项目
===

本项目的目的是最小化集成各种必须组件和通用配置，可以作为任何生产项目开发的骨架。至于先行的试验功能可以在 https://gitee.com/eric-tutorial/SpringCloud-multiple-gradle

# 组件

技术 | 说明                | 官网
----|-------------------|----
Spring Boot | 容器+MVC框架          | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
Spring Security | 认证和授权框架           | [https://spring.io/projects/spring-security](https://spring.io/projects/spring-security)
MyBatis | ORM框架             | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
MyBatisPlus | ORM框架扩展           | https://mybatis.plus/
HikariCP  | 数据库连接池            | https://github.com/brettwooldridge/HikariCP
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

# 开发

## 单元测试

符合AIR原则。

所有需要测试的层次，需要按照`增加`，`修改`，`查询`，`删除`顺序来操作。保证每一种操作都能预期断言成立。
也保证最后数据与测试之前没有区别。每次发布线上新系统的时候，就先经过一遍单元测试。

保证test按照一定顺序执行。JVM的加载顺序也有点乱，所以还是按照方法名来实现。方法名字前加上a，b，c，d。
```java
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
```
