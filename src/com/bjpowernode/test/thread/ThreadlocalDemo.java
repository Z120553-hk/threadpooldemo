package com.bjpowernode.test.thread;

public class ThreadlocalDemo {
    public static void main(String[] args) throws  Exception{
        final Test test =  new ThreadlocalDemo.Test();
        test.set();
        System.out.println(test.getLongThreadLocal());
        System.out.println(test.getStringThreadLocal());

       Thread thread = new Thread() {
           public void run() {
               test.set();
               System.out.println(test.getLongThreadLocal());
               System.out.println(test.getStringThreadLocal());
           };
       };
        thread.start();
        thread.join();
        System.out.println(test.getLongThreadLocal());
        System.out.println(test.getStringThreadLocal());
    }
   static class Test {
        ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

        public void set() {
            longThreadLocal.set(Thread.currentThread().getId());

            stringThreadLocal.set(Thread.currentThread().getName());

        }

        public Long getLongThreadLocal() {
            return longThreadLocal.get();
        }

        public String getStringThreadLocal() {
            return stringThreadLocal.get();
        }
    }
}




