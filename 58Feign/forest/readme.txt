Forest 底层封装了2种不同的http框架：Apache httpClient和OKhttp。所以这个开源框架并没有对底层实现进行重复造轮子，而是在易用性上面下足了功夫。

我用Forest最终完成了和多个服务商api对接的项目，这些风格迥异的API，我仅用了1个小时时间就把他们转化为了本地方法。然后项目顺利上线。

Forest作为一款更加高层的http框架，其实你并不需要写很多代码，大多数时候，你仅通过一些配置就能完成http的本地化调用。而这个框架所能覆盖的面，却非常之广，满足你绝大多数的http调用请求。

Forest有以下特点：

以Httpclient和OkHttp为后端框架
通过调用本地方法的方式去发送Http请求, 实现了业务逻辑与Http协议之间的解耦
相比Feign更轻量，不依赖Spring Cloud和任何注册中心
支持所有请求方法：GET, HEAD, OPTIONS, TRACE, POST, DELETE, PUT, PATCH
支持灵活的模板表达式
支持过滤器来过滤传入的数据
基于注解、配置化的方式定义Http请求
支持Spring和Springboot集成
实现JSON和XML的序列化和反序列化
支持JSON转换框架: Fastjson,Jackson, Gson
支持JAXB形式的XML转换
支持SSL的单向和双向加密
支持http连接池的设定
可以通过OnSuccess和OnError接口参数实现请求结果的回调
配置简单，一般只需要@Request一个注解就能完成绝大多数请求的定义
支持异步请求调用


http://localhost:8080/swagger-ui.html