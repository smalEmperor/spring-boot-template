+ spring boot 2.1.6版本(日志系统默认使用slf4j+logback,数据库连接池默认使用hikari,需要监控功能请自行配置Druid) 
+ mybatis-plus(mybatis-plus-boot-starter 3.2.0) 
+ sharding-jdbc(sharding-jdbc-spring-boot-starter 4.0.0-RC1)分库分表中间件 
+ lombok(简化实体bean和日志开发) 
+ swagger(接口文档生成工具,只在开发阶段使用，项目发布或者上线，去掉swagger相关jar包和配置代码)          
+ thymeleaf模板引擎
+ hibernate validator(参数效验工具 spring boot默认依赖) 
+ 缓存使用redis的Lettuce客户端(spring boot 默认缓存实现,其他缓存实现自行配置)

统一数据返回请使用封装的工具类,test包下包含有mybatisplus代码生成器,请自行选择配置使用

使用jdk8编译运行,有关时间的操作请使用jdk的时间api,时间操作工具类DateUtil

接口文档访问地址：http://ip:port/doc.html

请修改包名device

此模板项目只做统一开发环境，简化代码开发之用，不做技术强制使用，关于技术选型，如果你有更好的选择，请自行选择使用