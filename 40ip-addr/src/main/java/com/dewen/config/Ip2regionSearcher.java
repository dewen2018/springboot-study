package com.dewen.config;

import lombok.SneakyThrows;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


public class Ip2regionSearcher implements DisposableBean {

    private static final Pattern SPLIT_PATTERN = Pattern.compile("\\|");

    private final Searcher searcher;

    public Ip2regionSearcher(Searcher searcher) {
        this.searcher = searcher;
    }

    @SneakyThrows
    public String searchStr(String ip) {
        return searcher.search(ip);
    }

    public IPInfo search(String ip) {
        long sTime = System.nanoTime();
        String region = searchStr(ip);
        if (region == null) {
            return null;
        }
        IPInfo ipInfo = new IPInfo();
        String[] splitInfos = SPLIT_PATTERN.split(region);
        // 补齐5位
        if (splitInfos.length < 5) {
            splitInfos = Arrays.copyOf(splitInfos, 5);
        }
        ipInfo.setCountry(filterZero(splitInfos[0]));
        ipInfo.setRegion(filterZero(splitInfos[1]));
        ipInfo.setProvince(filterZero(splitInfos[2]));
        ipInfo.setCity(filterZero(splitInfos[3]));
        ipInfo.setIsp(filterZero(splitInfos[4]));
        long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
        System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        return ipInfo;
    }

    /**
     * 数据过滤，因为 ip2Region 采用 0 填充的没有数据的字段
     *
     * @param info info
     * @return info
     */
    @Nullable
    private String filterZero(@Nullable String info) {
        // null 或 0 返回 null
        if (info == null || BigDecimal.ZERO.toString().equals(info)) {
            return null;
        }
        return info;
    }

    @Override
    public void destroy() throws Exception {
        if (this.searcher != null) {
            this.searcher.close();
        }
    }

}