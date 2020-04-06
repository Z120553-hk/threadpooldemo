package com.bjpowernode.test.test;


import com.bjpowernode.test.datastructure.MyArraylist;
import com.bjpowernode.test.datastructure.Mylinked;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

   // Object o = new Object();
    byte [] b = new byte[100];
    static List<Object> list = new ArrayList<Object>();
    public static void main(String[] args) throws Exception {
               // B b =new B();
                //b.test();
        while(true) {
            list.add(new Main());
        }
       // System.out.println("111");
    }


}

 class A {

    public A() {
        System.out.println("A");
    }
    void test() {
        throw new RuntimeException("exception");
    }

}
class B extends A {

    public B() {
        System.out.println("B");
    }


}