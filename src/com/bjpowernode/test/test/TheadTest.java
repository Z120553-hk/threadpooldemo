package com.bjpowernode.test.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TheadTest {


    public static void main(String[] args) {

      /*  Resource resource = new Resource();
        Produ produ = new Produ(resource);

        Consumer consumer = new Consumer(resource);

        Thread t1 = new Thread(produ);
        Thread t2 = new Thread(consumer);

        Thread t3 = new Thread(produ);
        Thread t4 = new Thread(consumer);
        t1.start();
        t2.start();
        t3.start();
        t4.start();*/

        boolean flag = true;

        new Thread(new User(flag)).start();


        while (flag) {
            System.out.println("deng");
        }
        System.out.println("===");
    }

}

class User implements Runnable {
    boolean flag;

    public User(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }

        flag = false;
    }
}

class Resource {

    private volatile boolean flag = true;

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition_create = lock.newCondition();

    private Condition condition_destory = lock.newCondition();


    public void create() throws InterruptedException {

        lock.lock();
        try {
            while (!flag) {
                //还没有被消费，不生成
                condition_create.await();
            }
            Thread.sleep(1000);
            number++;
            System.out.println("生产了" + number);
            flag = false;
            condition_destory.signal();//唤醒消费者
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void destory() {
        lock.lock();
        try {
            while (flag) {
                //还没有被消费，不生成
                condition_destory.await();
            }
            Thread.sleep(1000);
            System.out.println("消费了" + number);
            flag = true;
            condition_create.signal();//唤醒生产者
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Produ implements Runnable {

    Resource resource;

    public Produ(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (true) {
                resource.create();
            }

        } catch (Exception e) {

        }

    }
}

class Consumer implements Runnable {

    Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (true) {
                resource.destory();
            }

        } catch (Exception e) {

        }

    }
}