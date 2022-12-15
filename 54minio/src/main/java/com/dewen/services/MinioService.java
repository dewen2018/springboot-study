package com.dewen.services;

import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MinioService {


    /**
     * 判断 bucket是否存在
     *
     * @param bucketName
     * @return
     */
    boolean bucketExists(String bucketName);

    /**
     * 创建 bucket
     *
     * @param bucketName
     */
    void makeBucket(String bucketName);

    /**
     * 文件上传
     *
     * @param bucketName
     * @param objectName
     * @param filename
     */
    void putObject(String bucketName, String objectName, String filename);

    /**
     * 文件上传
     *
     * @param bucketName
     * @param objectName
     * @param stream
     */
    void putObject(String bucketName, String objectName, InputStream stream, String contentType);

    /**
     * 文件上传
     *
     * @param bucketName
     * @param multipartFile
     */
    void putObject(String bucketName, MultipartFile multipartFile, String filename);

    /**
     * 删除文件
     *
     * @param bucketName
     * @param objectName
     */
    boolean removeObject(String bucketName, String objectName);

    /**
     * 下载文件
     *
     * @param fileName
     * @param originalName
     * @param response
     */
    void downloadFile(String bucketName, String fileName, String originalName, HttpServletResponse response);

    /**
     * 获取文件路径
     *
     * @param bucketName
     * @param objectName
     * @return
     */
    String getObjectUrl(String bucketName, String objectName);


    /**
     * @description: 文件下载
     * @param: bucketName
     * objectName
     * @return: io.minio.ObjectStat
     * @author yangc
     * @date: 2020-10-20 20:24
     */
    ObjectStat statObject(String bucketName, String objectName);

    /**
     * 以流的形式获取一个文件对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    InputStream getObject(String bucketName, String objectName);


    /**
     * 列出存储桶中所有对象
     *
     * @param bucketName 存储桶名称
     * @return
     */
    Iterable<Result<Item>> listObjects(String bucketName);


    /**
     * 生成一个给HTTP GET请求用的presigned URL
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param expires    失效时间（以秒为单位），默认是7天，不得大于七天
     * @return
     */
    String presignedGetObject(String bucketName, String objectName, Integer expires);


    /**
     * 设置存储桶策略
     *
     * @param bucketName 存储桶名称
     * @return
     */
    void setBucketPolicy(String bucketName, String policy) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ErrorResponseException, XmlParserException, InvalidBucketNameException, InsufficientDataException, InternalException, ServerException;


    /**
     * 获取存储桶策略
     *
     * @param bucketName 存储桶名称
     * @return
     */
    String getBucketPolicy(String bucketName) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, BucketPolicyTooLargeException, ErrorResponseException, XmlParserException, InvalidBucketNameException, InsufficientDataException, InternalException, ServerException;
}