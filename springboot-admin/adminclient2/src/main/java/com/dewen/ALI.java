package com.dewen;

/**
 * @author Dewen
 * @date 2022/4/21 20:33:30
 */
public class ALI {
    public static void main(String[] args) {
        System.out.println("http://localhost:19001/actuator/metrics/process.cpu.usage".contains("/actuator/"));
    }
}
