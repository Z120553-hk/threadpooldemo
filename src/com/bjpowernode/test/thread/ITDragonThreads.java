package com.bjpowernode.test.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ITDragonThreads {
    public static void main(String[] args) throws Exception {

         ExecutorService executorService  = Executors.newFixedThreadPool(8);
        CompletionService<Long> executorCompletionService = new ExecutorCompletionService<Long>(executorService);

        CountWorker countWorker;
        //long total = 0;
        long s = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) { // 3个线程负责统计
            countWorker = new CountWorker(i);
            executorCompletionService.submit(countWorker);
        }
        // 关闭线程池
        executorService.shutdown();

        // 主线程相当于第五个线程，用于汇总数据
        long total = 0;
        for (int i = 0; i < 3; i++) {
            total += executorCompletionService.take().get();
        }
        System.out.println(total / 1024 / 1024 / 1024 +"G");
        long s1 = System.currentTimeMillis();
        System.out.println(s1 - s);
    }
}
class CountWorker  implements Callable<Long> {
    private Integer type = 0;
    public CountWorker(int type) {
        this.type = type;
    }
    @Override
    public Long call() throws Exception {
        List<String> list = new ArrayList<String>(Arrays.asList("c:", "d:", "e:"));
        Long l3 = countDiskSpace(list.get(type));
        return   l3;
    }

    // 统计磁盘大小
    private Long countDiskSpace (String path) throws Exception{
        File file = new File(path);
        long totalSpace = file.getTotalSpace();
        System.out.println(path + " 总空间大小 : " + totalSpace / 1024 / 1024 / 1024 + "G");
        System.out.println(Thread.currentThread().getName());
        return totalSpace;
    }
}