package com.dewen.threeThreadXYZ;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test03 {
    static int num = 1;

    public static void main(String[] args) {

        //创建可以锁定代码的对象
        ReentrantLock lock = new ReentrantLock();
        //获取可以操作线程等待唤醒的Condition对象
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        Thread t1 = new Thread("线程一") {
            public void run() {
                while (true) {

                    lock.lock();
                    num++;
                    System.out.println("1");
                    //唤醒等待线程
                    c2.signal();
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                while (true) {

                    lock.lock();

                    if (num == 1) {
                        try {
                            c2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("2");
                    c3.signal();
                    try {
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            }
        };
        Thread t3 = new Thread(() -> {
            while (true) {
                lock.lock();

                if (num == 1 || num == 2) {
                    try {
                        c3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("3");
                c1.signal();
                try {
                    c3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        });
//        {
//            public void run() {
//                while (true) {
//
//                    lock.lock();
//
//                    if (num == 1 || num == 2) {
//                        try {
//                            c3.await();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    System.out.println("3");
//                    c1.signal();
//                    try {
//                        c3.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    lock.unlock();
//                }
//            }
//        };
        t1.start();
        t2.start();
        t3.start();
    }
}