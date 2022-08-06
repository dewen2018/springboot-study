package com.dewen.utils;

public class IpLongUtils {
    /**
     * 把字符串IP转换成long
     *
     * @param ipStr 字符串IP
     * @return IP对应的long值
     */
    public static long ip2Long(String ipStr) {
        String[] ip = ipStr.split("\\.");
        return (Long.valueOf(ip[0]) << 24) + (Long.valueOf(ip[1]) << 16)
                + (Long.valueOf(ip[2]) << 8) + Long.valueOf(ip[3]);
    }

    /**
     * 把IP的long值转换成字符串
     *
     * @param ipLong IP的long值
     * @return long值对应的字符串
     */
    public static String long2Ip(long ipLong) {
        StringBuilder ip = new StringBuilder();
        ip.append(ipLong >>> 24).append(".");
        ip.append((ipLong >>> 16) & 0xFF).append(".");
        ip.append((ipLong >>> 8) & 0xFF).append(".");
        ip.append(ipLong & 0xFF);
        return ip.toString();
    }

    /**
     * 对于转换字符串IPv4和数值类型，可以放在应用层
     * <p>
     * 存储IPv4地址时，应该使用32位的无符号整数（UNSIGNED INT）来存储IP地址 int(4)，而不是使用字符串。
     * 相对字符串存储，使用无符号整数来存储有如下的好处：
     * 节省空间，不管是数据存储空间，还是索引存储空间 便于使用范围查询（BETWEEN...AND），且效率更高 通常，在保存IPv4地址时，
     * 一个IPv4最小需要7个字符，最大需要15个字符，所以，使用VARCHAR(15)即可。MySQL在保存变长的字符串时，还需要额外的一个字节来保存此字符串的长度。
     * 而如果使用无符号整数来存储，只需要4个字节即可。另外还可以使用4个字段分别存储IPv4中的各部分，但是通常这不管是存储空间和查询效率应该都不是很高
     * （可能有的场景适合使用这种方式存储）。
     * <p>
     * 使用无符号整数来存储也有缺点
     * 不便于阅读, 需要手动转换
     * 对于转换来说，MySQL提供了相应的函数来把字符串格式的IP转换成整数INET_ATON，以及把整数格式的IP转换成字符串的INET_NTOA。如下所示：
     * select inet_aton('192.168.0.1');
     * select inet_ntoa(3232235521);
     * 对于IPv6来说，使用VARBINARY同样可获得相同的好处，同时MySQL也提供了相应的转换函数，即INET6_ATON和INET6_NTOA。
     *
     * CREATE TABLE `test1` (
     *   `id` int(1) NOT NULL DEFAULT '0',
     *   `ipv4` bigint(21) unsigned DEFAULT NULL,
     *   `ipv6` varchar(32) DEFAULT NULL
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(ip2Long("255.255.255.255"));
        System.out.println(long2Ip(3232235521L));
        System.out.println(ip2Long("10.0.0.1"));
    }

}
