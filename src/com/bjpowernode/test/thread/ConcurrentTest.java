package com.bjpowernode.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentTest {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<List> executorCompletionService = new ExecutorCompletionService<List>(executorService);
        ConcurrentTask ConcurrentTask = new ConcurrentTask();

        for (int i = 0; i < 10; i++) { // 3个线程负责统计
            executorCompletionService.submit(ConcurrentTask);
        }
        // 关闭线程池
        executorService.shutdown();

    }
}

class ConcurrentTask implements Callable<List> {

    static int k = 0;

    @Override
    public List call() throws Exception {

        try {


            Thread.sleep(1000);
            k++;
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(k);


        return new ArrayList();
    }

    ;
}