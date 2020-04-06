package com.bjpowernode.test.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.我们在使用 HashSet 对自定义类进行去重的时候，一定要覆盖自定义类的 equals 和 hashCode 方法，hashCode 方法是找到当前对象在 Node 数组重的位置，而 equals 是比较当前对象与对应坐标链表中的对象是否相同。
 * <p>
 * 2.在使用Set对象储存自定义对象的时候，每次都会调用自定义对象的 hashCode 方法，但是 equals 方法并不是每次都会被调用到
 */

public class TestHashSet {

    public static void main(String[] args) {
        Set set = new HashSet();
        User u1 = new User("hk", 12);
        User u2 = new User("hk", 12);
        set.add(u1);
        set.add(u2);
        ConcurrentHashMap m = new ConcurrentHashMap();
        System.out.println(set.size());

        test();

    }

    public static String test() {
        try {
            //int i = 1/0;
            System.out.println("66");
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(123);
        }
        return "";
    }
}

class User {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return age != null ? age.equals(user.age) : user.age == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    private String username;
    private Integer age;

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }


}
