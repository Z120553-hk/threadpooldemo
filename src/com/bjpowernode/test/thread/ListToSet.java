package com.bjpowernode.test.thread;


import java.util.*;
import java.util.concurrent.*;

public class ListToSet {

    public static void main(String[] args) throws  Exception{
       ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<List> executorCompletionService = new ExecutorCompletionService<List>(executorService);
        Mytask mytask;
        //long total = 0;
        List<String> list = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            list.add(i + "");
        }
        long s = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) { // 3个线程负责统计
            mytask = new Mytask(list, i);
            executorCompletionService.submit(mytask);
        }
        // 关闭线程池
        executorService.shutdown();

        // 主线程相当于第五个线程，用于汇总数据
        for (int i = 0; i < 3; i++) {
            List<String>  dataset = executorCompletionService.take().get();
            for (String str : dataset) {
                System.out.print(str + ",");
            }
            System.out.println();
        }
        long s1 = System.currentTimeMillis();
        System.out.println(s1 - s);

    }
}

class Mytask implements Callable<List> {

    List<String> datalist;

    int type;
    public Mytask(List<String> list,int type) {
        this.datalist = list;
        this.type = type;
    }
    @Override
    public List call() throws Exception {
        int length = datalist.size();
        int start = 0;
        int end = 0;
        switch (type){
            case 0:
                start = 0;
                end = length * 3 /10;
                break;
            case 1:
                start = length * 3 /10;
                end = length * 6 /10;
                break;
            case 2:
                start = length * 6 /10;
                end = length;
                break;
        }
        List l3 = copy(datalist, start, end);
        return  l3;
    }

    public List copy(List<String> list, int start, int end) throws  Exception{
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName());
        List<String> set = new ArrayList<>();
        for (int i = start; i < end; i++) {
            set.add(list.get(i));
        }
        return set;
    }
}