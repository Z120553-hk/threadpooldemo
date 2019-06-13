package com.bjpowernode.test.lamdba;

import java.util.Comparator;

public class LamdbaDemo {
    public static void main(String[] args) {
        //
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("run.....");
            }
        };


        Runnable r_lamdba = () -> System.out.println("run....");

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("compare....");
                return o1 + o2;
            }
        };


        Comparator<Integer> c_lamdba = (o1,o2) -> {
            System.out.println("compare....");
            return o1 + o2;
        };


    }
}
