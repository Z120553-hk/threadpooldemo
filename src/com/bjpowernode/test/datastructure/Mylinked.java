package com.bjpowernode.test.datastructure;

public class Mylinked {

    Node first;//头结点

    int size;//链表的长度

    public void add(Object value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = newNode;
        } else {
            Node tmp = first;//当前节点
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            //设置最后一个元素的下一个引用
            tmp.setNext(newNode);
        }
        size++;
    }

    public void add(Object value, int index) {
        Node newNode = new Node(value);
        if (index > size - 1) {
            //插入链表最后的元素
            add(value);
        } else {
            //在已有的元素之间插入
            Node oldnode = first;//头节点,从头结点开始遍历,得到目标位置的元素，后面需要把新节点的next指向它
            Node pre = first;//目标节点的上一节点
            for (int i = 0; i < index; i++) {
                if (i == index - 1) {
                    pre = oldnode;
                }
                oldnode = oldnode.getNext();
            }
            //需要把上一个节点的next引用指向自己，自己的下一引用指向下一节点
            pre.setNext(newNode);
            newNode.setNext(oldnode);
            size++;
        }
    }

    public Object get(int index) {
        Node tmp = first;//当前节点
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp.getValue();
    }

    public void remove(int index) {
        if (index > size - 1) {
            //没有节点
            return;
        }
        if (index == 0) {
            //删除头元素
            first = first.getNext();
        } else {
            //找到删除元素的头一个元素
            Node tmp = first;//当前节点
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.getNext();
            }
            tmp.setNext(tmp.getNext().getNext());
            size--;
        }
    }

    public void remove(Object obj) {
        if (size > 0) {
            Node tmp = first;
            int k = 0;//表示循环了几次才找到目标对象
            while (!tmp.getValue().equals(obj)) {
                tmp = tmp.getNext();
                k++;
            }
            if (size == 1) {
                tmp = null;
            } else {
                Node node = tmp.getNext();
                //需要找到tmp的上一个节点
                Node preNode = first;
                for (int i = 1; i < k; i++) {
                    preNode = preNode.getNext();
                }
                preNode.setNext(node);
            }
            size--;
        }

    }

    class Node {

        private Node next;//下一节点的引用

        private Object value;//结点值

        public void setNext(Node next) {
            this.next = next;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Node(Object value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public Object getValue() {
            return value;
        }
    }

    int length() {
        return size;
    }
}

class Main {

    public static void main(String[] args) throws Exception {
        Mylinked mylinked = new Mylinked();
        mylinked.add("a");
        mylinked.add("b");
        mylinked.add("c");
        mylinked.add("d");
        mylinked.add("e");

        System.out.println(mylinked.get(0));
        System.out.println(mylinked.get(1));
        System.out.println(mylinked.get(2));
        System.out.println(mylinked.get(3));
        System.out.println(mylinked.get(4));
        mylinked.add("f", 2);
        //  mylinked.remove(5);
        mylinked.remove("e");


    }
}