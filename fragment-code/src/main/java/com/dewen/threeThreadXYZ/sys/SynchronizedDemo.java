package com.dewen.threeThreadXYZ.sys;

/**
 * 使用wait()方法来阻塞线程，使用notify()和notifyAll()方法来唤醒线程。
 * 调用wait()方法后，线程将被阻塞，wait()方法将会释放当前持有的监视器锁(monitor)，直到有线程调用notify/notifyAll()方法后方能继续执行。
 * notify/notifyAll()方法只是解除了等待线程的阻塞，并不会马上释放监视器锁，而是在相应的被synchronized关键字修饰的同步方法或同步代码块执行结束后才自动释放锁。
 * 默认使用非公平锁，无法修改。
 * 缺点：
 * 使用几个方法时，必须处于被synchronized关键字修饰的同步方法或同步代码块中，否则程序运行时，会抛出IllegalMonitorStateException异常。
 * 线程的唤醒必须在线程阻塞之后，否则，当前线程被阻塞之后，一直没有唤醒，线程将会一直等待下去（对比LockSupport）
 */
public class SynchronizedDemo {

    // 三个线程交替打印ABC
    public static void main(String[] args) {

        Print print = new Print();

        new Thread(() -> {
            while (true) {
                print.printA();
            }
        }, "A").start();
        new Thread(() -> {
            while (true) {
                print.printB();
            }
        }, "B").start();
        new Thread(() -> {
            while (true) {
                print.printC();
            }
        }, "C").start();
    }
}

class Print {

    Object object = new Object();
    int num = 1;

    public void printA() {
        synchronized (object) {
            try {
                while (num != 1) {
                    object.wait();
                }
//                for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "==>A");
//                }
                num = 2;
                object.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (object) {
            try {
                while (num != 2) {
                    object.wait();
                }
//                for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "==>B");
//                }
                num = 3;
                object.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (object) {
            try {
                while (num != 3) {
                    object.wait();
                }
//                for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "==>C");
//                }
                num = 1;
                object.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}