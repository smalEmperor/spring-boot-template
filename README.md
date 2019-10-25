spring boot 2.1.6版本(日志系统默认使用slf4j+logback,数据库连接池默认使用hikari,需要监控功能请自行配置Druid) + mybatis-plus(mybatis-plus-boot-starter 3.2.0) 
+ sharding-jdbc(sharding-jdbc-spring-boot-starter 4.0.0-RC1)分库分表中间件 + lombok(简化实体bean和日志开发) + swagger(接口文档生成工具) + thymeleaf模板引擎
+ hibernate validator(参数效验工具 spring boot默认依赖) + 缓存使用redis的Lettuce客户端(spring boot 默认缓存实现,其他缓存实现自行配置)

统一数据返回请使用封装的工具类

关于上面使用到的一些技术，使用自行百度或者谷歌学习

请修改包名device