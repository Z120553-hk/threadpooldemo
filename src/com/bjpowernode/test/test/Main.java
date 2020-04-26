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

    public static void main(String[] args) throws Exception {

        int j = 0;
        for (int i = 0; i < 10; i++) {
            j = j++;
        }
        System.out.println("adjust-branch");
        System.out.println(j);
    }



}

