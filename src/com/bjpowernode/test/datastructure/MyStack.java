package com.bjpowernode.test.datastructure;


class MyStack {

    private Object[] datas;

    private int index;//只想下一个元素的引用或者下标

    public MyStack() {
        this.index = 0;
        datas = new Object[5];
    }


    /**
     * 压栈
     *
     * @param obj
     */
    void push(Object obj) {
        int length = datas.length;
        if (index > length - 1) {
            //需要扩容或者抛异常
            throw new RuntimeException("栈已满");
        }
        datas[index] = obj;
        index++;
    }

    /**
     * 弹栈
     */
    Object pop() {
        if (index <= 0) {
            throw new RuntimeException("栈已空");
        }
        Object o = datas[index - 1];
        index--;
        return o;
    }
}

class TestStack {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);
        //   myStack.push(1);
        System.out.println("=====");
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        // System.out.println(myStack.pop());

    }
}