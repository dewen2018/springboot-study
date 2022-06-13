FastDFS是用c语言编写的一款开源的分布式文件系统，充分考虑了冗余备份、负载均衡、线性扩容等机制，
并注重高可用、高性能等指标，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，
解决了大容量存储和负载均衡的问题。特别适合中小文件（建议范围： 4KB < file_size <500MB），
对以文件为载体的在线服务，如相册网站、视频网站等。

SpringBoot2.0
Nginx，提供HTTP服务
Docker，一件安装容器，后面的就都不需要了
FastDFS，C语言编写的一款开源的分布式文件系统
libfastcommon，包含了FastDFS运行所需要的一些基础库
Fastdfs-nginx-module，Nginx结合 fastdfs-nginx-module插件去实现http协议