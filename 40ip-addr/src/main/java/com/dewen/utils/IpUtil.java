package com.dewen.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * X-Forwarded-For：一个 HTTP 扩展头部，主要是为了让 Web 服务器获取访问用户的真实 IP 地址。
 * 每个 IP 地址，每个值通过逗号+空格分开，最左边是最原始客户端的 IP 地址，中间如果有多层代理，
 * 每⼀层代理会将连接它的客户端 IP 追加在 X-Forwarded-For 右边。
 * X-Real-IP：一般只记录真实发出请求的客户端IP
 * Proxy-Client-IP：这个一般是经过 Apache http 服务器的请求才会有，
 * 用 Apache http 做代理时一般会加上 Proxy-Client-IP 请求头
 * WL-Proxy-Client-IP：也是通过 Apache http 服务器，在 weblogic 插件加上的头。
 * <p>
 */
@Slf4j
public class IpUtil {
    public static String getIpAddr(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ipAddress = headers.getFirst("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddress().getAddress().getHostAddress();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    log.error("根据网卡获取本机配置的IP异常", e);
                }

            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
        if (ipAddress != null && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.split(",")[0];
        }
        return ipAddress;
    }

    /**
     * memory 算法：整个数据库全部载入内存，单次查询都在0.1x毫秒内，C语言的客户端单次查询在0.00x毫秒级别。
     * binary 算法：基于二分查找，基于ip2region.db文件，不需要载入内存，单次查询在0.x毫秒级别。
     * b-tree 算法：基于btree算法，基于ip2region.db文件，不需要载入内存，单词查询在0.x毫秒级别，比binary算法更快。
     */

//     static {
//         String dbPath = createFtlFileByFtlArray() + "ip2region.db";
//         try {
//             config = new DbConfig();
//         } catch (DbMakerConfigException e) {
//             e.printStackTrace();
//         }
//         try {
//             searcher = new DbSearcher(config, dbPath);
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         }
//     }
//
//     public static String getCityInfo(String ip) {
//
//         if (StringUtils.isEmpty(dbPath)) {
//             log.error("Error: Invalid ip2region.db file");
//             return null;
//         }
//         if (config == null || searcher == null) {
//             log.error("Error: DbSearcher or DbConfig is null");
//             return null;
//         }
//
//         //查询算法
//         //B-tree, B树搜索（更快）
//         int algorithm = DbSearcher.BTREE_ALGORITHM;
//
//         //Binary,使用二分搜索
//         //DbSearcher.BINARY_ALGORITHM
//
//         //Memory,加载内存（最快）
//         //DbSearcher.MEMORY_ALGORITYM
//         try {
//             // 使用静态代码块，减少文件读取操作
// //            DbConfig config = new DbConfig();
// //            DbSearcher searcher = new DbSearcher(config, dbPath);
//
//             //define the method
//             Method method = null;
//             switch (algorithm) {
//                 case DbSearcher.BTREE_ALGORITHM:
//                     method = searcher.getClass().getMethod("btreeSearch", String.class);
//                     break;
//                 case DbSearcher.BINARY_ALGORITHM:
//                     method = searcher.getClass().getMethod("binarySearch", String.class);
//                     break;
//                 case DbSearcher.MEMORY_ALGORITYM:
//                     method = searcher.getClass().getMethod("memorySearch", String.class);
//                     break;
//                 default:
//             }
//
//             DataBlock dataBlock = null;
//             if (Util.isIpAddress(ip) == false) {
//                 System.out.println("Error: Invalid ip address");
//             }
//
//             dataBlock = (DataBlock) method.invoke(searcher, ip);
//             String ipInfo = dataBlock.getRegion();
//             if (!StringUtils.isEmpty(ipInfo)) {
//                 ipInfo = ipInfo.replace("|0", "");
//                 ipInfo = ipInfo.replace("0|", "");
//             }
//             return ipInfo;
//
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//
//         return null;
//     }
//
//     /**
//      * 获取IP属地
//      *
//      * @param ip
//      * @return
//      */
//     public static String getIpPossession(String ip) {
//         String cityInfo = getCityInfo(ip);
//         if (!StringUtils.isEmpty(cityInfo)) {
//             cityInfo = cityInfo.replace("|", " ");
//             String[] cityList = cityInfo.split(" ");
//             if (cityList.length > 0) {
//                 // 国内的显示到具体的省
//                 if ("中国".equals(cityList[0])) {
//                     if (cityList.length > 1) {
//                         return cityList[1];
//                     }
//                 }
//                 // 国外显示到国家
//                 return cityList[0];
//             }
//         }
//         return "未知";
//     }
}