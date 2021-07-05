//package com.dewen.classloader;
//
//import java.io.*;
//
///**
// * @author wxw
// * @version 2019/1/18
// */
//public class MyClassLoader extends ClassLoader {
//    private String name;
//    private String path;
//
//    public MyClassLoader(String name, String path) {
//        this.name = name;
//        this.path = path;
//    }
//
//    public MyClassLoader() {
//    }
//
//    @Override
//    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        byte[] data = loadClassData(name);
//        return defineClass(name,data,0,data.length);
//    }
//
//    private byte[] loadClassData(String name) {
//        name = path+name+".class";
//        InputStream in = null;
//        ByteArrayOutputStream out = null;
//
//        try {
//            in = new FileInputStream(new File(name));
//            out = new ByteArrayOutputStream();
//            int i = 0;
//            while ((i = in.read())!=-1){
//                out.write(i);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                out.close();
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return out.toByteArray();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public static void main(String[] args) {
//        MyClassLoader myClassLoader = new MyClassLoader("StringUtil","D:\\BaiduYunDownload\\");
//        try {
//            Class<?> ll = myClassLoader.loadClass("StringUtil");
//            System.out.println(ll.getClassLoader());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
