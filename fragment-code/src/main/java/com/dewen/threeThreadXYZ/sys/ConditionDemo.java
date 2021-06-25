package com.dewen.threeThreadXYZ.sys;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用await()方法来阻塞线程，signal()/singnalAll()方法来唤醒线程。
 * 需要使用lock对象的newCondition()方法获得Condition条件对象（可有多个）。
 * 可实现公平锁，默认是非公平锁
 * 缺点：
 * 必须被Lock包裹，否则会在运行时抛出IllegalMonitorStateException异常。
 * 线程的唤醒必须在线程阻塞之后
 * Lock的实现是基于AQS，效率稍高于synchronized
 */
public class ConditionDemo {

    // 三个线程交替打印ABC
    public static void main(String[] args) {
        Print2 print = new Print2();
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

class Print2 {

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int num = 1;

    public void printA() {

        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
//            for (int i = 0; i < 5; ++i) {
            System.out.println(Thread.currentThread().getName() + "==>A");
//            }
            num = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {

        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
//            for (int i = 0; i < 10; ++i) {
            System.out.println(Thread.currentThread().getName() + "==>B");
//            }
            num = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {

        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
//            for (int i = 0; i < 15; ++i) {
            System.out.println(Thread.currentThread().getName() + "==>C");
//            }
            num = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}