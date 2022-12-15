package com.dewen.services.impl;

import com.dewen.common2.MinioUtil;
import com.dewen.services.MinioService;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioServiceImpl implements MinioService {

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 判断 bucket是否存在
     *
     * @param bucketName
     * @return
     */
    @Override
    public boolean bucketExists(String bucketName) {
        return minioUtil.bucketExists(bucketName);
    }

    /**
     * 创建 bucket
     *
     * @param bucketName
     */
    @Override
    public void makeBucket(String bucketName) {
        minioUtil.makeBucket(bucketName);
    }

    /**
     * 文件上传
     *
     * @param bucketName
     * @param objectName
     * @param filename
     */
    @Override
    public void putObject(String bucketName, String objectName, String filename) {
        minioUtil.putObject(bucketName, objectName, filename);
    }


    @Override
    public void putObject(String bucketName, String objectName, InputStream stream, String contentType) {
        minioUtil.putObject(bucketName, objectName, stream, contentType);
    }

    /**
     * 文件上传
     *
     * @param bucketName
     * @param multipartFile
     */
    @Override
    public void putObject(String bucketName, MultipartFile multipartFile, String filename) {
        minioUtil.putObject(bucketName, multipartFile, filename);
    }

    /**
     * 删除文件
     * @param bucketName
     * @param objectName
     */
    @Override
    public boolean removeObject(String bucketName,String objectName) {
        return minioUtil.removeObject(bucketName,objectName);
    }

    /**
     * 下载文件
     *
     * @param fileName
     * @param originalName
     * @param response
     */
    @Override
    public void downloadFile(String bucketName, String fileName, String originalName, HttpServletResponse response) {
        minioUtil.downloadFile(bucketName,fileName, originalName, response);
    }

    /**
     * 获取文件路径
     * @param bucketName
     * @param objectName
     * @return
     */
    @Override
    public String getObjectUrl(String bucketName,String objectName) {
        return minioUtil.getObjectUrl(bucketName,objectName);
    }

    /**
     * @param bucketName
     * @param objectName
     * @description: 文件下载
     * @param: bucketName
     * objectName
     * @return: io.minio.ObjectStat
     * @author yangc
     * @date: 2020-10-20 20:24
     */
    @Override
    public ObjectStat statObject(String bucketName, String objectName) {
        return minioUtil.statObject(bucketName,objectName);
    }

    /**
     * 以流的形式获取一个文件对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    @Override
    public InputStream getObject(String bucketName, String objectName) {
        return minioUtil.getObject(bucketName,objectName);
    }

    /**
     * 列出存储桶中所有对象
     *
     * @param bucketName 存储桶名称
     * @return
     */
    @Override
    public Iterable<Result<Item>> listObjects(String bucketName) {
        return minioUtil.listObjects(bucketName);
    }

    /**
     * 生成一个给HTTP GET请求用的presigned URL
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param expires    失效时间（以秒为单位），默认是7天，不得大于七天
     * @return
     */
    @Override
    public String presignedGetObject(String bucketName, String objectName, Integer expires) {
        return minioUtil.presignedGetObject(bucketName, objectName, expires);
    }

    /**
     * 设置存储桶策略
     *
     * @param bucketName 存储桶名称
     * @param policy
     * @return
     */
    @Override
    public void setBucketPolicy(String bucketName, String policy) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ErrorResponseException, XmlParserException, InvalidBucketNameException, InsufficientDataException, InternalException, ServerException {
        minioUtil.setBucketPolicy(bucketName, policy);
    }

    /**
     * 获取存储桶策略
     *
     * @param bucketName 存储桶名称
     * @return
     */
    @Override
    public String getBucketPolicy(String bucketName) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, BucketPolicyTooLargeException, ErrorResponseException, XmlParserException, InvalidBucketNameException, InsufficientDataException, InternalException, ServerException {
        return minioUtil.getBucketPolicy(bucketName);
    }


}

