demo
	SpringBoot + Netty+ WebSocket 实现消息传递

rpcdemo
    https://mp.weixin.qq.com/s/c7rVkOXPgry8F6cDK4fLwg
    【客户端】发起调用
    【客户端】数据编码
    【客户端】发送编码后的数据到服务端
    【服务端】接收客户端发送的数据
    【服务端】对数据进行解码
    【服务端】处理消息业务并返回结果值
    【服务端】对结果值编码
    【服务端】将编码后的结果值回传给客户端
    【客户端】接收结果值
    【客户端】解码结果值
    【客户端】处理返回数据业务

netty-protobuf
    protocolbuffer(以下简称PB)是google 的一种数据交换的格式，它独立于语言，独立于平台。google 提供了多种语言的实现：java、c#、c++、go 和python，每一种实现都包含了相应语言的编译器以及库文件。

注意
    1.java环境1.8
    2.proto文件名字不能一样
    3..\protoc.exe --java_out=D:\codes\code2\springboot-study\sb-netty\netty-protobuf\src\main\java .\message.proto