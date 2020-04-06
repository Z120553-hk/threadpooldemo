package com.bjpowernode.test.thread;


public class ConcurrentTest01 {
    public static void main(String[] args) throws Exception {
        ConcurrentTask01 concurrentTask01 = new ConcurrentTask01();
        Thread t1 = new Thread(concurrentTask01);
        Thread t2 = new Thread(concurrentTask01);
        Thread t3 = new Thread(concurrentTask01);
        Thread t4 = new Thread(concurrentTask01);
        Thread t5 = new Thread(concurrentTask01);
        Thread t6 = new Thread(concurrentTask01);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

}

class ConcurrentTask01 implements Runnable {

    @Override
    public void run() {
        try {
            int k = 0;
            Thread.sleep(1000);
            k++;
            System.out.println(k);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    ;
}