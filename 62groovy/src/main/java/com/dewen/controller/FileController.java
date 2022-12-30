package com.dewen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dewen
 * @date 2022/12/16 11:14
 */
@RestController
@RequestMapping("/file")
@Api(tags = "FileController")
public class FileController {
    /**
     * 文档管理--个性化设置--开启动态请求参数
     * 写相同的key,knife4j即可以上传多附件
     *
     * @param files
     * @param width
     * @param height
     */
    @PostMapping("/changeSize")
    // @ApiImplicitParam(name = "files", value = "上传附件", dataType = "java.io.File", required = true)
    @ApiImplicitParam(name = "files", value = "上传附件", dataTypeClass = MultipartFile.class, required = true)
    public void changeSize(@RequestPart("files") MultipartFile[] files, int width, int height) {
    }


    /**
     * 单附件
     *
     * @param resource
     * @param width
     * @param height
     */
    @PostMapping("/changeSize2")
    @ApiImplicitParam(name = "resource", value = "上传附件", dataTypeClass = MultipartFile.class, required = true)
    public void changeSize2(@RequestPart("resource") MultipartFile resource, int width, int height) {
    }
}
