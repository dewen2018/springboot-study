package com.dewen.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 读取项目内文件
 *
 * @author dewen
 * @date 2022/12/30 17:06
 */
public class ReadFile {
    public void loadPropertiesFile() throws IOException {
        Properties properties = new Properties();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        properties.load(is);
        System.out.println("端口号：" + properties.getProperty("server.port"));
    }

    public void loadFile01() throws IOException {
        //加载资源文件
        ClassPathResource classPathResource = new ClassPathResource("excelfile/07版.xlsx");
        //获取文件
        File file = classPathResource.getFile();
        //获取路径
        String path = classPathResource.getPath();
        System.out.println("path:" + path);
        System.out.println("file:" + file);
        //转成输入流
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        //获取文件流
        FileInputStream inputStream = new FileInputStream(file);
    }

    public void loadFile02() throws IOException {
        //获取文件的URL
        File file = ResourceUtils.getFile("classpath:excelfile/03版.xls");
        System.out.println("文件:" + file);
        //转成string输入文本
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        System.out.println("内容：" + content);
    }
}
