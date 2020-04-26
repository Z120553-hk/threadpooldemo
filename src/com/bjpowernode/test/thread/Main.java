package com.bjpowernode.test.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class Main {
    /* public static void main(String[] args) throws Exception{
         long s = System.currentTimeMillis();
         int count = 0;
         for (int i = 0; i < 3; i++) {
             Thread t1 = new Thread(new User());
             t1.start();
             count++;
         }
         System.out.println( System.currentTimeMillis() - s);
         while (count == 3) {
             System.out.println("===");
             System.out.println(System.currentTimeMillis() - s);
             count++;
         }
         System.out.println("main");
     }*/
    public static void main(String[] args) throws Exception {
        long s = System.currentTimeMillis();
        // 无缓冲无界线程池
        ExecutorService executor = Executors.newFixedThreadPool(8);
        // 相对ExecutorService，CompletionService可以更精确和简便地完成异步任务的执行
        CompletionService<Long> completion = new ExecutorCompletionService<Long>(executor);
        //Future<Long> f = null;
        CountWorker11 countWorker = null;
        // long total = 0;
        for (int i = 0; i < 4; i++) { // 四个线程负责统计
            countWorker = new CountWorker11(i);
            completion.submit(countWorker);
            //f = executor.submit(countWorker);
            //total += f.get();

        }
        // 关闭线程池
        executor.shutdown();

        // 主线程相当于第五个线程，用于汇总数据
        long total = 0;
        for (int i = 0; i < 4; i++) {
            total += completion.take().get();
        }
        System.out.println(total / 1024 / 1024 / 1024 + "G");
        System.out.println(System.currentTimeMillis() - s);
    }

}



class User implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
    }
}

class CountWorker11 implements Callable<Long> {
    private Integer type;

    public CountWorker11() {
    }

    public CountWorker11(Integer type) {
        this.type = type;
    }

    @Override
    public Long call() throws Exception {
        ArrayList<String> paths = new ArrayList<>(Arrays.asList("c:", "d:", "e:", "f:"));
        return countDiskSpace(paths.get(type));
    }

    // 统计磁盘大小
    private Long countDiskSpace(String path) throws Exception {
        File file = new File(path);
        long totalSpace = file.getTotalSpace();
        System.out.println(path + " 总空间大小 : " + totalSpace / 1024 / 1024 / 1024 + "G");
        Thread.sleep(1000);
        System.out.println("branch....");
        return totalSpace;
    }
}