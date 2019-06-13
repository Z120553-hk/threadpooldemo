package com.bjpowernode.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception{

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " is run over");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }).start();
        }
        System.out.println("waiting two threads running");

        countDownLatch.await();

        System.out.println("main thread is run over");

    }
}
class CountDownLatchDemo_lambda {

    public static void main(String[] args) throws  Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " is run over");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        System.out.println("waiting two threads running");

        countDownLatch.await();

        System.out.println("main thread is run over");
    }
}
class CountDownLatchDemo_executor {
    public static void main(String[] args) throws Exception{

        final CountDownLatch countDownLatch = new CountDownLatch(4);
        
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,  //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
                10, //线程池最大能容忍的线程数
                200, //线程存活时间
                TimeUnit.MILLISECONDS, //参数keepAliveTime的时间单位
                new ArrayBlockingQueue<Runnable>(5) //任务缓存队列，用来存放等待执行的任务
        );

        for (int i = 0; i < 4; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " is run over");
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        threadPoolExecutor.shutdown();
        System.out.println("waiting two threads running");

        countDownLatch.await();

        System.out.println("main thread is run over");
    }
}