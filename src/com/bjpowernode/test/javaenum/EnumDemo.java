package com.bjpowernode.test.javaenum;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

public enum EnumDemo {

    MON,TUE

/* 这段代码实际上调用了2次 Enum(String name, int ordinal)：
            new Enum<EnumTest>("ONE",0);
new Enum<EnumTest>("TWO",1);
new Enum<EnumTest>("THREE",2);*/
}
class  Test {
    public static void main(String[] args) {
        for (EnumDemo e : EnumDemo.values()) {
            System.out.println(e.name());
            System.out.println(e.ordinal());
        }


        // EnumSet的使用
        EnumSet<EnumDemo> weekSet = EnumSet.allOf(EnumDemo.class);
        for (EnumDemo day : weekSet) {
            System.out.println(day);
        }

        // EnumMap的使用
        EnumMap<EnumDemo, String> weekMap = new EnumMap(EnumDemo.class);
        weekMap.put(EnumDemo.MON, "星期一");
        weekMap.put(EnumDemo.TUE, "星期二");
        // ... ...
        for (Iterator<Map.Entry<EnumDemo, String>> iter = weekMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<EnumDemo, String> entry = iter.next();
            System.out.println(entry.getKey().name() + ":" + entry.getValue());
        }
    }
}
