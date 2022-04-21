第一阶段
微服务中，服务是互相调用的，没有单纯的服务provider和consumer
    此处provider2调用provider的服务


Spring Cloud Gateway是Spring官方基于Spring 5.0，Spring Boot 2.0和Project Reactor等技术开发的网关，
Spring Cloud Gateway旨在为微服务架构提供一种简单而有效的统一的API路由管理方式。
Spring Cloud Gateway作为Spring Cloud生态系中的网关，目标是替代Netflix ZUUL，其不仅提供统一的路由方式，
并且基于Filter链的方式提供了网关基本的功能，例如：安全，监控/埋点，和限流等。

规则：http://Gateway_HOST:Gateway_PORT/大写的serviceId/**
其中微服务应用名默认大写访问。
spring.cloud.gateway.discovery.locator.enabled：是否与服务发现组件进行结合，通过 serviceId 转发到具体的服务实例。
默认为false，设为true便开启通过服务中心的自动根据 serviceId 创建路由的功能。

logging.level.org.springframework.cloud.gateway: debug,开启spring-Cloud-gateway的日志级别为debug，方便debug调试。

正确的Spring Cloud Gateway的默认路由规则:http://Gateway_HOST:Gateway_PORT/大写的serviceId/**



暂定，还未规划好。webconsumer，只调用其他服务，不直接操作数据库




在微服务架构这样的分布式环境中，我们不可能使用单节点的服务注册中心，如果down掉了，
那整个项目都会崩溃。所以我们需要构建高可用的服务注册中心以增强系统的可用性。
在Eureka的服务治理设计中，所有节点即是服务提供方，也是服务消费方，服务注册中心也不例外。
eureka.client.register-with-eureka: false
eureka.client.fetch-registry: false
Eureka Server的高可用实际上就是将自己作为服务向其他服务注册中心注册自己，这样就可以形成一组互相注册的服务注册中心。
通过命令启动，选用不同的配置文件
java -jar eureka-server-1.0-SNAPSHOT.jar --spring.profiles.active=server
java -jar eureka-server-1.0-SNAPSHOT.jar --spring.profiles.active=server2
java -jar eureka-server-1.0-SNAPSHOT.jar --spring.profiles.active=server3


配置中心更新：
     http://localhost:8311/actuator/bus-refresh
测试地址：
    http://localhost:15672/
####################################################################################################
第二阶段（parent）
    每个子项目只引入特需jar包

