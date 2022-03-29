package com.dewen.threeThreadXYZ.sys;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用park()来阻塞线程，用unpark()方法来唤醒线程。
 * 这里有一个许可证的概念，许可不能累积，并且最多只能有一个许可，只有1和0的区别。
 * 特点：
 * 使用灵活，可以直接使用
 * 线程唤醒可在线程阻塞之前，因为调用unpark()方法后，线程已经获得了一个许可证（但也只能有一个许可证），之后阻塞时，可以直接使用这个许可证来通行。
 * 效率高
 */
public class LockSupportDemo {

    // 三个线程交替打印ABC
    public static void main(String[] args) throws Exception {

        Print3 print = new Print3();

        Thread threadA = new Thread(() -> {
            while (true) {
                print.printA();
            }
        }, "A");

        Thread threadB = new Thread(() -> {
            while (true) {
                print.printB();
            }
        }, "B");

        Thread threadC = new Thread(() -> {
            while (true) {
                print.printC();
            }
        }, "C");

        threadA.start();
        threadB.start();
        threadC.start();

        while (true) {
            LockSupport.unpark(threadA);
            LockSupport.unpark(threadB);
            LockSupport.unpark(threadC);
        }

    }
}

class Print3 {

    private int num = 1;

    public void printA() {
        while (num != 1) {
            LockSupport.park();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "==>A");
        }
        num = 2;
    }

    public void printB() {
        while (num != 2) {
            LockSupport.park();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "==>B");
        }
        num = 3;
    }

    public void printC() {
        while (num != 3) {
            LockSupport.park();
        }
        for (int i = 0; i < 15; i++) {
            System.out.println(Thread.currentThread().getName() + "==>C");
        }
        num = 1;
    }
}