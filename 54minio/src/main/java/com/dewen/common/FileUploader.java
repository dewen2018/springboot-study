//package com.dewen.common;
//
//import io.minio.*;
//import io.minio.errors.MinioException;
//
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//public class FileUploader {
//    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
//        try {
//            // Create a minioClient with the MinIO server playground, its access key and secret key.
//            MinioClient minioClient = MinioClient.builder()
//                    .endpoint("http://127.0.0.1:9000")
//                    .credentials("minioadmin", "minioadmin")
//                    .build();
//
//            // Make 'dewen' bucket if not exist.
//            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("dewen").build());
//            if (!found) {
//                // Make a new bucket called 'dewen'.
//                minioClient.makeBucket(MakeBucketArgs.builder().bucket("dewen").build());
//            } else {
//                System.out.println("Bucket 'dewen' already exists.");
//            }
//
//            // Upload 'file' as object name 'fileobject' to bucket
//            ObjectWriteResponse objectWriteResponse = minioClient.uploadObject(UploadObjectArgs.builder()
//                    .bucket("dewen")
////                    .object("minio-8.1.0-all.jar")
//                    .object("测试文件.xlsx")
//                    //文件路径
////                    .filename("E:\\code2\\minio\\src\\main\\java\\com\\dewen\\common\\minio-8.1.0-all.jar")
//                    .filename("E:\\code2\\minio\\src\\main\\java\\com\\dewen\\common\\测试文件.xlsx")
//                    .build());
//            System.out.println("Successfully uploaded.");
//            System.out.println(objectWriteResponse);
//        } catch (MinioException e) {
//            System.out.println("Error occurred: " + e);
//            System.out.println("HTTP trace: " + e.httpTrace());
//        }
//    }
//}