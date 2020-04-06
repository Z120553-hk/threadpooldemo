package com.bjpowernode.test.thread;

public class VolatileTest {

    //boolean stop = false;
     volatile boolean stop = false;

    public static void main(String[] args) throws Exception {




        VolatileTest v = new VolatileTest();
        Thread ta = new Thread(new Runnable() {
            @Override
            public void run() {
                v.execute();
            }
        });
        ta.start();
        Thread.sleep(2000);
        Thread tb = new Thread(new Runnable() {
            @Override
            public void run() {
                v.shutdown();
            }
        });
        tb.start();
    }

    public void execute() {
        while (!stop) {

        }
        System.out.println("结束了");
    }

    public void shutdown() {
        System.out.println("do stop");
        stop = true;
    }
}
