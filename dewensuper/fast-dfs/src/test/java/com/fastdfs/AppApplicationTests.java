package com.fastdfs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class AppApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void Upload() {
        String fileUrl = this.getClass().getResource("/dewen.jpg").getPath();
        File file = new File(fileUrl);
        String str = FastdfsUtils.uploadFile(file);
        FastdfsUtils.getResAccessUrl(str);
        System.out.println(FastdfsUtils.getResAccessUrl(str));
    }

    @Test
    public void Delete() {
        FastdfsUtils.deleteFile("group1/M00/00/00/rBEAClu8OiSAFbN_AAbhXQnXzvw031.jpg");
    }
}
