1.添加healthcheck和web包
http://localhost:8082/node2/doc.html
http://localhost:8082/node2/actuator/readiness


2.为什么要选择SOFABoot？他能解决什么问题？
    根据官方介绍，他主要能解决以下的问题：
    Spring Boot 的健康检查只有 Liveness Check 的能力，缺少 Readiness Check 的能力
    Liveness Check 和 Readiness Check 概念来自于 Kuberentes，分别代表运行时检查和启动时检查。
    利用 Readiness Check 的能力，SOFA 中间件中的各个组件只有在 Readiness Check 通过之后，才将流量引入到应用的实例中，比如 RPC，
    只有在 Readiness Check 通过之后，才会向服务注册中心注册，后面来自上游应用的流量才会进入

2.1提供模块化开发的能力
基于 Spring 上下文隔离提供模块化开发能力，每个 SOFABoot 模块使用独立的 Spring 上下文，避免不同 SOFABoot 模块间的 BeanId 冲突

2.2增加日志空间隔离的能力
在超大规模微服务运维的场景下，运维能力的平台化是一定要解决的问题，而监控又是其中非常主要的一个点，针对于日志监控这种情况，
Spring Boot 并没有提供任何解决方案。大部分的开源组件，具体要打印哪些日志，打印到什么路径，什么文件下面，都是由应用的使用者来决定，
这样会导致每一个应用的日志配置都各式各样，每一个应用都需要去监控系统中配置自己应用的日志监控，导致关键的监控的实施成本特别高

中间件框架自动发现应用的日志实现依赖并独立打印日志，避免中间件和应用日志实现绑定，通过 sofa-common-tools 实现

2.3增加类隔离的能力
基于 SOFAArk 框架提供类隔离能力，方便使用者解决各种类冲突问题

为了解决以上的问题，又因为 SOFA 中间件中的各个组件本身就需要集成 Spring Boot，所以蚂蚁集团基于 Spring Boot 开发并开源了 SOFABoot，
来解决以上的问题，也方便使用者在 Spring Boot 中方便地去使用 SOFA 中间件。

以上都是官方给出的解释。



https://gitee.com/sofastack/sofa-boot
https://github.com/sofastack
https://www.sofastack.tech/