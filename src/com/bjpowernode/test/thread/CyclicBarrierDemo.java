package com.bjpowernode.test.thread;

import java.util.concurrent.*;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception{

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4,() -> {
            System.out.println( Thread.currentThread().getName());
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


        threadPoolExecutor.shutdown();
    }
}
class Test {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N,new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程"+Thread.currentThread().getName());
            }
        });

        for(int i=0;i<N;i++)
            new Writer(barrier).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(500);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}