package com.dewen.threeThreadXYZ;

import java.util.concurrent.TimeUnit;

public class threeThreadXYZ {
    //打印的次数1--10
    static volatile int count = 0;

    /**
     * 1：让X线程打印
     * 2：让Y线程打印
     * 3：让Z线程打印
     */
    static volatile int flag = 1;

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (flag == 1) {
                        System.out.print("X ");//打印X
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        flag = 2;
                    }
                }

            }
        }, "X").start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (flag == 2) {
                        System.out.print("Y ");//打印Y
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        flag = 3;
                    }
                }

            }
        }, "Y").start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (flag == 3) {
                        System.out.print("Z ");//打印Z
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (count++ == 9) {//如果为10 次则系统停止
                            System.exit(0);//系统停止
                        }
                        System.out.println();//换行
                        flag = 1;
                    }
                }

            }
        }, "Z").start();

    }

}
