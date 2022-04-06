Spring Boot Admin Server 可以监控的功能很多，下面描述下可以监测的部分内容：
    应用运行状态，如时间、垃圾回收次数，线程数量，内存使用走势。
    应用性能监测，通过选择 JVM 或者 Tomcat 参数，查看当前数值。
    应用环境监测，查看系统环境变量，应用配置参数，自动配置参数。
    应用 bean 管理，查看 Spring Bean ，并且可以查看是否单例。
    应用计划任务，查看应用的计划任务列表。
    应用日志管理，动态更改日志级别，查看日志。
    应用 JVM 管理，查看当前线程运行情况，dump 内存堆栈信息。
    应用映射管理，查看应用接口调用方法、返回类型、处理类等信息。


客户端可使用client2配置文件，较为全面


Spring Boot Admin Server 配置属性详解
属性	描述	默认值
spring.boot.admin.context-path	上下文路径在应为Admin Server的静态资产和API提供服务的路径的前面加上前缀。相对于Dispatcher-Servlet	/
spring.boot.admin.monitor.status-interval	更新client端状态的时间间隔，单位是毫秒	10000
spring.boot.admin.monitor.status-lifetime	client端状态的生命周期，该生命周期内不会更新client状态，单位是毫秒	10000
spring.boot.admin.monitor.connect-timeout	查询client端状态信息时的连接超时，单位是毫秒	2000
spring.boot.admin.monitor.read-timeout	查询client端状态信息时的读取超时时间，单位是毫秒	10000
spring.boot.admin.monitor.default-retries	失败请求的默认重试次数。Modyfing请求（PUT，POST，PATCH，DELETE）将永远不会重试	0
spring.boot.admin.monitor.retries.*	键值对，具有每个endpointId的重试次数。默认为默认重试。Modyfing请求（PUT，POST，PATCH，DELETE）将永远不会重试
spring.boot.admin.metadata-keys-to-sanitize	要被过滤掉的元数据（当与正则表达式相匹配时，这些数据会在输出的json数据中过滤掉）	".password$", ".secret$", ".key$", ".$token$", ".credentials.", ".*vcap_services$"
spring.boot.admin.probed-endpoints	要获取的client的端点信息	"health", "env", "metrics", "httptrace:trace", "threaddump:dump", "jolokia", "info", "logfile", "refresh", "flyway", "liquibase", "heapdump", "loggers", "auditevents"
spring.boot.admin.instance-proxy.ignored-headers	向client发起请求时不会被转发的headers信息	"Cookie", "Set-Cookie", "Authorization"
spring.boot.admin.ui.public-url	用于在ui中构建基本href的基本URL	如果在反向代理后面运行（使用路径重写），则可用于进行正确的自我引用。如果省略了主机/端口，将从请求中推断出来
spring.boot.admin.ui.brand	导航栏中显示的品牌	<img src="assets/img/icon-spring-boot-admin.svg"><span>Spring Boot Admin</span>
spring.boot.admin.ui.title	页面标题	"Spring Boot Admin"
spring.boot.admin.ui.favicon	用作默认图标的图标，用于桌面通知的图标	"assets/img/favicon.png"
spring.boot.admin.ui.favicon-danger	当一项或多项服务关闭并用于桌面通知时，用作网站图标	"assets/img/favicon-danger.png"
Spring Boot Admin Client 配置属性详解
属性	描述	默认值
spring.boot.admin.client.enabled	启用Spring Boot Admin Client	true
spring.boot.admin.client.url	要注册的server端的url地址。如果要同时在多个server端口注册，则用逗号分隔各个server端的url地址
spring.boot.admin.client.api-path	管理服务器上注册端点的Http路径	"instances"
spring.boot.admin.client.username	如果server端需要进行认证时，该属性用于配置用户名
spring.boot.admin.client.password	如果server端需要进行认证时，该属性用于配置密码
spring.boot.admin.client.period	重复注册的时间间隔（以毫秒为单位）	10000
spring.boot.admin.client.connect-timeout	连接注册的超时时间（以毫秒为单位）	5000
spring.boot.admin.client.read-timeout	读取注册超时（以毫秒为单位）	5000
spring.boot.admin.client.auto-registration	如果设置为true，则在应用程序准备就绪后会自动安排注册应用程序的定期任务	true
spring.boot.admin.client.auto-deregistration	当上下文关闭时，切换为在Spring Boot Admin服务器上启用自动解密。如果未设置该值，并且在检测到正在运行的CloudPlatform时，该功能处于活动状态	null
spring.boot.admin.client.register-once	如果设置为true，则客户端将仅向一台管理服务器注册（由定义spring.boot.admin.instance.url）；如果该管理服务器出现故障，将自动向下一个管理服务器注册。如果为false，则会向所有管理服务器注册	true
spring.boot.admin.client.instance.health-url	要注册的health-url地址。如果可访问URL不同（例如Docker），则可以覆盖。在注册表中必须唯一	默认该属性值与management-url 以及endpoints.health.id有关。比如工程中该值为：healthUrl=http://127.0.0.1:8080/actuator/health，其中http://127.0.0.1:8080/actuator是management-url，health是endpoints.health.id
spring.boot.admin.client.instance.management-base-url	用于计算要注册的管理URL的基本URL。该路径是在运行时推断的，并附加到基本URL	默认该属性值与management.port, service-url 以及server.servlet-path有关，如工程中该值为http://127.0.0.1:8080，其中8080端口是配置的获取actuator信息的端口。127.0.0.1是设置的service-url值，如果没有设置service-url的话，则为配置的server.servlet-path值（项目的启动路径）
spring.boot.admin.client.instance.management-url	要注册的management-url。如果可访问的URL不同（例如Docker），则可以覆盖	默认该属性值与management-base-url 和 management.context-path两个属性值有关，如 managementUrl=http://127.0.0.1:8080/actuator，其中http://127.0.0.1:8080为management-base-url，/actuator是management.context-path
spring.boot.admin.client.instance.service-base-url	用于计算要注册的服务URL的基本URL。该路径是在运行时推断的，并附加到基本URL	默认该属性值与hostname, server.port有关，如http://127.0.0.1:8080，其中8080端口是配置的server.port。127.0.0.1是client所在服务器的hostname
spring.boot.admin.client.instance.service-url	要注册的服务网址。如果可访问的URL不同（例如Docker），则可以覆盖	默认值是基于service-base-url 和 server.context-path进行赋值
spring.boot.admin.client.instance.name	要注册的名称	默认值是配置的spring.application.name的值，如果没有配置该属性的话，默认值是spring-boot-application
spring.boot.admin.client.instance.prefer-ip	在猜测的网址中使用ip地址而不是主机名。如果设置了server.address/ management.address，它将被使用。否则，InetAddress.getLocalHost()将使用从返回的IP地址	false
spring.boot.admin.client.instance.metadata.*	要与此实例相关联的元数据键值对
spring.boot.admin.client.instance.metadata.tags.*	标记作为要与此实例相关联的键值对