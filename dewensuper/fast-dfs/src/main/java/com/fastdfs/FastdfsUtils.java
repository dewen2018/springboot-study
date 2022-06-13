package com.fastdfs;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class FastdfsUtils {

    public static final String DEFAULT_CHARSET = "UTF-8";

    private static FastFileStorageClient fastFileStorageClient;
    private static FdfsWebServer fdfsWebServer;

    @Autowired
    public void setFastDFSClient(FastFileStorageClient fastFileStorageClient, FdfsWebServer fdfsWebServer) {
        FastdfsUtils.fastFileStorageClient = fastFileStorageClient;
        FastdfsUtils.fdfsWebServer = fdfsWebServer;
    }

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 返回文件地址
     * @description 上传文件
     */
    public StorePath uploadFile(MultipartFile file) {
        try {
            // 设置文件信息
            Set<MetaData> mataData = new HashSet<>();
            mataData.add(new MetaData("author", "fastdfs"));
            mataData.add(new MetaData("description", file.getOriginalFilename()));
            // 上传
            StorePath storePath = fastFileStorageClient.uploadFile(
                    file.getInputStream(),
                    file.getSize(),
                    FilenameUtils.getExtension(file.getOriginalFilename()),
                    mataData);
            return storePath;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * 上传缩略图
     *
     * @param file 图片对象
     * @return 返回图片地址
     * @description 上传缩略图
     */
    public static String uploadImageAndCrtThumbImage(MultipartFile file) {
        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    file.getInputStream(),
                    file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            return storePath.getFullPath();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 返回文件地址
     * @description 上传文件
     */
    public static String uploadFile(File file) {
        // ((DefaultFastFileStorageClient) fastFileStorageClient).getStorageNode("group1").setIp("192.168.0.102");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            StorePath storePath = fastFileStorageClient.uploadFile(
                    inputStream,
                    file.length(),
                    FilenameUtils.getExtension(file.getName()),
                    null);
            return storePath.getFullPath();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 上传缩略图
     *
     * @param file 图片对象
     * @return 返回图片地址
     * @description 上传缩略图
     */
    public static String uploadImageAndCrtThumbImage(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    inputStream,
                    file.length(),
                    FilenameUtils.getExtension(file.getName()),
                    null);
            return storePath.getFullPath();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将byte数组生成一个文件上传
     *
     * @param bytes         byte数组
     * @param fileExtension 文件扩展名
     * @return 返回文件地址
     * @description 将byte数组生成一个文件上传
     */
    public static String uploadFile(byte[] bytes, String fileExtension) {
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(
                stream,
                bytes.length,
                fileExtension,
                null);
        return storePath.getFullPath();
    }

    /**
     * 文件下载
     *
     * @param path     文件路径，例如：/group1/path=M00/00/00/itstyle.png
     * @param filename 下载的文件命名
     * @return
     */

    public void downloadFile(String path, String filename, HttpServletResponse response) throws IOException {
        // 获取文件
        StorePath storePath = StorePath.parseFromUrl(path);
        if (StringUtils.isBlank(filename))
            filename = FilenameUtils.getName(storePath.getPath());
        byte[] bytes = fastFileStorageClient.downloadFile(
                storePath.getGroup(),
                storePath.getPath(),
                new DownloadByteArray());
        response.reset();
        response.setContentType("applicatoin/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.close();
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件访问地址
     * @param file    文件保存路径
     * @description 下载文件
     */
    public static boolean downloadFile(String fileUrl, File file) {
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            byte[] bytes = fastFileStorageClient.downloadFile(
                    storePath.getGroup(),
                    storePath.getPath(),
                    new DownloadByteArray());
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(bytes);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * 删除
     *
     * @param path
     */

    public static boolean deleteFile(String path) {
        if (StringUtils.isEmpty(path)) return false;
        try {
            StorePath storePath = StorePath.parseFromUrl(path);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;

    }

    /**
     * 删除
     *
     * @param path
     */
    public void delete(String path) {
        fastFileStorageClient.deleteFile(path);
    }


    /**
     * 删除
     *
     * @param group
     * @param path
     */
    public void delete(String group, String path) {
        fastFileStorageClient.deleteFile(group, path);
    }

    /**
     * 封装文件完整URL地址
     *
     * @param path
     * @return
     */
    public static String getResAccessUrl(String path) {
        String url = fdfsWebServer.getWebServerUrl() + path;
        log.info("上传文件地址为：\n" + url);
        return url;
    }
}