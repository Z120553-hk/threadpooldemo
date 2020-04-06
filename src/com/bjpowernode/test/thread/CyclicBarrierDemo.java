package com.bjpowernode.test.thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.*;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("all threads is over");
        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 5, 2000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1));

        for (int i = 0; i < 4; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + " is writing...");
                    cyclicBarrier.await();
                } catch (Exception e) {

                }
            });
        }

        InputStreamReader reder = new InputStreamReader(new FileInputStream(""));

        Iterator it = new HashMap().entrySet().iterator();

        threadPoolExecutor.shutdown();
    }
}

/**
 * CyclicBarrier也是一个同步辅助类，它允许一组线程相互等待，
 * 直到到达某个公共屏障点（common barrier point）。通过它可以完成多个线程之间相互等待，
 * 只有当每个线程都准备就绪后，才能各自继续往下执行后面的操作。类似于CountDownLatch，
 * 它也是通过计数器来实现的。当某个线程调用await方法时，该线程进入等待状态，且计数器加1，
 * 当计数器的值达到设置的初始值时，所有因调用await进入等待状态的线程被唤醒，
 * 继续执行后续操作。因为CycliBarrier在释放等待线程后可以重用，所以称为循环barrier。
 * CycliBarrier支持一个可选的Runnable，在计数器的值到达设定值后（但在释放所有线程之前），
 * 该Runnable运行一次，注，Runnable在每个屏障点只运行一个
 */
class Test {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程" + Thread.currentThread().getName());
            }
        });

        for (int i = 0; i < N; i++)
            new Writer(barrier).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(500);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}