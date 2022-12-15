package com.dewen.controller;

import com.alibaba.fastjson.JSON;
import com.dewen.common2.Result;
import com.dewen.common2.UploadFile;
import com.dewen.config.MinioConfig;
import com.dewen.services.MinioService;
import io.minio.ObjectStat;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequestMapping("/minio")
@RestController
@Slf4j
public class MinioController {

    @Autowired
    private MinioService minioService;

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private UploadFile uploadFile;


    @SneakyThrows
    @PostMapping("/file")
    public Result uploadFile(MultipartFile file, @RequestParam("bucketName") String bucketName) {
        uploadFile.uploadFile(file, bucketName);
        return Result.success();
    }

    @PostMapping("/files")
    public Result uploadFiles(@RequestParam("multipartFiles") List<MultipartFile> files, @RequestParam("bucketName") String bucketName) {
        for (MultipartFile file : files) {
            uploadFile.uploadFile(file, bucketName);
        }
        return Result.success();
    }

    @GetMapping("/file")
    public Result download(HttpServletResponse response, @RequestParam("fileName") String fileName, @RequestParam("bucketName") String bucketName) {
        try {
            //获取存储捅名
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioConfig.getBucketName();
            //获取对象信息和对象的元数据。
            ObjectStat objectStat = minioService.statObject(bucketName, fileName);
            //setContentType 设置发送到客户机的响应的内容类型
            response.setContentType(objectStat.contentType());
            //设置响应头
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectStat.name(), "UTF-8"));
            //文件流
            InputStream object = minioService.getObject(bucketName, fileName);
            //设置文件大小
            response.setHeader("Content-Length", String.valueOf(objectStat.length()));
            IOUtils.copy(object, response.getOutputStream());
            //关闭流
            object.close();
            return Result.success();
        } catch (Exception e) {
            log.error("下载文件失败,错误信息: " + e.getMessage());
            return Result.fail("下载文件失败,错误信息: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/file")
    public Result deleteFile(@RequestParam("bucketName") String bucketName, @RequestParam("objectName") String objectName) {
        minioService.removeObject(bucketName, objectName);
        return Result.success();
    }


    @GetMapping(value = "/file/list")
    public Result<List<Object>> getFileList(@RequestParam("bucketName") String bucketName) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        try {
            //获取存储捅名
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioConfig.getBucketName();
            //列出存储桶中所有对象
            Iterable<io.minio.Result<Item>> results = minioService.listObjects(bucketName);
            //迭代器
            Iterator<io.minio.Result<Item>> iterator = results.iterator();

            List<Object> items = new ArrayList<>();

            String format = "{'fileName':'%s','fileSize':'%s'}";

            while (iterator.hasNext()) {
                //返回迭代中的下一个元素。
                Item item = iterator.next().get();
                //封装信息
                items.add(JSON.parse(String.format(format, "http://localhost:9000/" + bucketName + "/" + item.objectName(), formatFileSize(item.size()))));
            }
            return Result.success(items);
        } catch (Exception e) {
            log.error("获取文件列表失败,错误信息: " + e.getMessage());
            return Result.fail("获取文件列表失败");
        }
    }

    @GetMapping("/preview/file")
    public Result<List<Object>> getPreviewFile(@RequestParam("bucketName") String bucketName,
                                               @RequestParam("expires") Integer expires,
                                               @RequestParam("objectName") String objectName) {
        //获取存储捅名
        bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioConfig.getBucketName();
        //生成一个给HTTP GET请求用的presigned URL
        String filePath = minioService.presignedGetObject(bucketName, objectName, expires);
        //封装信息
        return Result.success(filePath);
    }


    @GetMapping("/previewList")
    public Result<List<Object>> getPreviewList(@RequestParam("bucketName") String bucketName,
                                               @RequestParam("expires") Integer expires
    ) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {

        try {
            //获取存储捅名
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioConfig.getBucketName();
            //列出存储桶中所有对象
            Iterable<io.minio.Result<Item>> myObjects = minioService.listObjects(bucketName);
            //迭代器
            Iterator<io.minio.Result<Item>> iterator = myObjects.iterator();
            List<Object> items = new ArrayList<>();
            String format = "{'fileName':'%s','fileSize':'%s'}";
            while (iterator.hasNext()) {
                //返回迭代中的下一个元素。
                Item item = iterator.next().get();
                //生成一个给HTTP GET请求用的presigned URL
                String filePath = minioService.presignedGetObject(bucketName, item.objectName(), expires);
                //封装信息
                items.add(JSON.parse(String.format(format, filePath, formatFileSize(item.size()))));
            }
            return Result.success(items);
        } catch (Exception e) {
            return Result.fail("生成可以预览的文件链接失败,错误信息:" + e.getMessage());
        }

    }

    /**
     * 显示文件大小信息单位
     *
     * @param fileS
     * @return
     */
    private static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + " B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + " KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + " MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + " GB";
        }
        return fileSizeString;
    }

}