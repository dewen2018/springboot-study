package com.dewen.provinceCityArea;

public class PathTest {

    public static String getClassPath() {
        String path = new Object() {
            public String getPath() {
                return this.getClass().getResource("").getPath();
            }
        }.getPath();
        return path;
    }

    public static void main(String[] args) throws Exception {
//		Thread.currentThread().getContextClassLoader().getResource("/").getPath();
         System.out.println("类路径:" + PathTest.getClassPath());
    }
}
 