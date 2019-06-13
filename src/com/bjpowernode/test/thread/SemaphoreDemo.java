package com.bjpowernode.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。
 那么我们就可以通过Semaphore来实现：
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(2));

        for(int i = 0; i < 8; i++) {
            threadPoolExecutor.execute(new Worker(i, semaphore));
        }

        threadPoolExecutor.shutdown();
    }
}
class Worker implements Runnable {
    private Semaphore semaphore;
    private int num;
    public Worker(int num,Semaphore semaphore) {
        this.num = num;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("worker" + this.num + " is use");
            Thread.sleep(2000);
            System.out.println("worker" + this.num + " is shifang");
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
