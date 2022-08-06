// package com.dewen.controller;
//
// import com.alibaba.fastjson.JSON;
// import com.dewen.tools.FileObj;
// import org.elasticsearch.action.index.IndexRequest;
// import org.elasticsearch.action.index.IndexResponse;
// import org.elasticsearch.client.RequestOptions;
// import org.elasticsearch.common.xcontent.XContentType;
// import org.springframework.web.bind.annotation.RestController;
//
// import java.io.File;
// import java.io.IOException;
// import java.util.Base64;
//
// @RestController
// public class TestController {
//
//     public FileObj readFile(String path) throws IOException {
//         //读文件
//         File file = new File(path);
//
//         FileObj fileObj = new FileObj();
//         fileObj.setName(file.getName());
//         fileObj.setType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
//
//         byte[] bytes = getContent(file);
//
//         //将文件内容转化为base64编码
//         String base64 = Base64.getEncoder().encodeToString(bytes);
//         fileObj.setContent(base64);
//
//         return fileObj;
//     }
//
//     public void upload(FileObj file) throws IOException {
//         IndexRequest indexRequest = new IndexRequest("fileindex");
//
//         //上传同时，使用attachment pipline进行提取文件
//         indexRequest.source(JSON.toJSONString(file), XContentType.JSON);
//         indexRequest.setPipeline("attatchment");
//
//         IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//         System.out.println(indexResponse);
//     }
// }
