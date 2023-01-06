package com.dewen;

import java.io.IOException;

public class ExeProtobuf {
    public static void main(String[] args) {
        //下载的protoc.exe文件绝对路径
        String strCmd = "D:\\Programs\\protobuf\\protoc-21.12-win64\\bin\\protoc.exe " +
                //test.proto文件相对路径
                " -I=D:\\codes\\code2\\springboot-study\\sb-netty\\netty-protobuf\\src\\main\\java\\com\\dewen\\protobuf " +
                //要生成的实体类路径
                " --java_out=D:\\codes\\code2\\springboot-study\\sb-netty\\netty-protobuf\\src\\main\\java " +
                //test.proto文件绝对路径
                " D:\\codes\\code2\\springboot-study\\sb-netty\\netty-protobuf\\src\\main\\java\\com\\dewen\\protobuf\\user.proto";

        try {
            Runtime.getRuntime().exec(strCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("over");
    }
}