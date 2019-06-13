package com.bjpowernode.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxy {

    int i = 0;

    public static void main(String[] args) {
        Iuser target = () -> System.out.println("adduser...");

        DynamicProxy handler = new DynamicProxy(target);
        Iuser proxy = (Iuser) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),handler);
        proxy.adduser();
    }


}

class DynamicProxy implements InvocationHandler {

    private Object target;
    public DynamicProxy(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("adduser..bofore");
        Object result = method.invoke(target,args);
        System.out.println("adduser..after");
        return result;
    }
}
interface Iuser {
    void adduser();
}