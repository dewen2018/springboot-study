FROM java:8
VOLUME /tmp
# 将当前目录下的jar包复制到docker容器的/目录下
ADD native-1.0.0.jar app.jar
# 声明服务运行在8080端口
EXPOSE 80
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
# 指定维护者的名字
MAINTAINER dewen