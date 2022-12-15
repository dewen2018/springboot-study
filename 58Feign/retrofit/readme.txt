很多情况下，我们希望将http请求日志记录下来。通过@RetrofitClient的logLevel和logStrategy属性，您可以指定每个接口的日志打印级别以及日志打印策略。

retrofit-spring-boot-starter支持了5种日志打印级别(ERROR, WARN, INFO, DEBUG, TRACE)，默认INFO；支持了4种日志打印策略（NONE, BASIC, HEADERS, BODY），默认BASIC。

4种日志打印策略含义如下：

NONE：No logs.
BASIC：Logs request and response lines.
HEADERS：Logs request and response lines and their respective headers.
BODY：Logs request and response lines and their respective headers and bodies (if present).
retrofit-spring-boot-starter默认使用了DefaultLoggingInterceptor执行真正的日志打印功能，其底层就是okhttp原生的HttpLoggingInterceptor。

当然，你也可以自定义实现自己的日志打印拦截器，只需要继承BaseLoggingInterceptor（具体可以参考DefaultLoggingInterceptor的实现），然后在配置文件中进行相关配置即可。

retrofit:
  # 日志打印拦截器
  logging-interceptor: com.github.lianjiatech.retrofit.spring.boot.interceptor.DefaultLoggingInter