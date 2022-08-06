package com.dewen;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 完全基于文件的查询
 */
public class SearcherTest1 {
    public static void main(String[] args) throws IOException {
        // 1、创建 searcher 对象
        String dbPath = "D:/codes/code2/springboot-study/40ip-addr/src/main/resources/data/ip2region.xdb";
        Searcher searcher = null;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (IOException e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
            return;
        }

        String ip = "1.2.3.4";
        // 2、查询
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }

        // 3、关闭资源
        searcher.close();
        // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。
    }
}