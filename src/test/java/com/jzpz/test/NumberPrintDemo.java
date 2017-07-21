package com.jzpz.test;

import org.junit.Test;

/**
 * Created by Administrator on 2017/5/22.
 */
public class NumberPrintDemo {

    // n为即将打印的数字
    private static int n = 1;
    // state=1表示将由线程1打印数字, state=2表示将由线程2打印数字, state=3表示将由线程3打印数字
    private static int state = 1;

    @Test
    public void main() {
        final NumberPrintDemo pn = new NumberPrintDemo();
        new Thread(new Runnable() {
            public void run() {
                // 3个线程打印75个数字, 单个线程每次打印5个连续数字, 因此每个线程只需执行5次打印任务. 3*5*5=75
                for (int i = 0; i < 5; i++) {
                    // 3个线程都使用pn对象做锁, 以保证每个交替期间只有一个线程在打印
                    synchronized (pn) {
                        // 如果state!=1, 说明此时尚未轮到线程1打印, 线程1将调用pn的wait()方法, 直到下次被唤醒
                        while (state != 1)
                            try {
                                pn.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        // 当state=1时, 轮到线程1打印5次数字
                        for (int j = 0; j < 5; j++) {
                            // 打印一次后n自增
                            System.out.println(Thread.currentThread().getName()+ ": " + n++);
                        }
                        System.out.println();
                        // 线程1打印完成后, 将state赋值为2, 表示接下来将轮到线程2打印
                        state = 2;
                        // notifyAll()方法唤醒在pn上wait的线程2和线程3, 同时线程1将退出同步代码块, 释放pn锁.
                        // 因此3个线程将再次竞争pn锁
                        // 假如线程1或线程3竞争到资源, 由于state不为1或3, 线程1或线程3将很快再次wait, 释放出刚到手的pn锁.
                        // 只有线程2可以通过state判定, 所以线程2一定是执行下次打印任务的线程.
                        // 对于线程2来说, 获得锁的道路也许是曲折的, 但前途一定是光明的.
                        pn.notifyAll();
                    }
                }
            }
        }, "线程1").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (pn) {
                        while (state != 2)
                            try {
                                pn.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        for (int j = 0; j < 5; j++) {
                            System.out.println(Thread.currentThread().getName()
                                    + ": " + n++);
                        }
                        System.out.println();
                        state = 3;
                        pn.notifyAll();
                    }
                }
            }
        }, "线程2").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (pn) {
                        while (state != 3)
                            try {
                                pn.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        for (int j = 0; j < 5; j++) {
                            System.out.println(Thread.currentThread().getName() + ": " + n++);
                        }
                        System.out.println();
                        state = 1;
                        pn.notifyAll();
                    }
                }
            }
        }, "线程3").start();
    }
}
