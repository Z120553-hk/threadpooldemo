package com.bjpowernode.test.datastructure;

public class MyArraylist {

    private Object[] datas;

    /**
     * 初始化数组大小
     */
    private static int initsize = 5;

    int index = 0;//数组下标从0开始到size - 1

    public MyArraylist() {
        datas = new Object[initsize];
    }

    /**
     * 默认放在最后一个位置
     *
     * @param obj - 目标元素
     */
    public void add(Object obj) {
        int length = datas.length;
        if (index > length - 1) {
            // throw new RuntimeException("满了，扩容去吧");
            //扩容
            copyArr();
        }
        datas[index] = obj;
        index++;
    }

    /**
     * 把元素放在指定位置
     *
     * @param obj    - 目标元素
     * @param _index - 指定下标位置
     */
    public void add(Object obj, int _index) {
        if (index >= datas.length) {
            //  throw new RuntimeException("数组已满");
            //扩容
            copyArr();
        }
        if (_index > datas.length - 1 || _index < 0) {
            throw new RuntimeException("数组越界");
        }
        if (_index == datas.length - 1) {
            //是要放入数组的末尾,不涉及移动后面元素的位置
            datas[_index] = obj;
        } else {
            //插入数组非尾的位置
            for (int i = index - 1; i >= _index; i--) {
                datas[i + 1] = datas[i];
            }
            datas[_index] = obj;
        }
        index++;
    }

    public Object get(int _index) {
        if (_index > datas.length - 1 || _index < 0) {
            throw new RuntimeException("数组越界");
        }
        return datas[_index];

    }

    /**
     * 移除指定元素
     *
     * @param obj - 目标元素
     * @return
     */
    public boolean remove(Object obj) {
        for (int i = 0; i < datas.length; i++) {
            if (obj == null) {
                if (equals(datas[i] == null)) {
                    //后面的元素向前移动，最后一个元素给空
                    for (int j = i + 1; j < index; j++) {
                        datas[j - 1] = datas[j];
                    }
                    datas[index - 1] = null;
                    break;
                }
            } else {
                if (obj.equals(datas[i])) {
                    //后面的元素向前移动，最后一个元素给空
                    for (int j = i + 1; j < index; j++) {
                        datas[j - 1] = datas[j];
                    }
                    datas[index - 1] = null;
                    break;
                }
            }

        }
        index--;
        return true;
    }

    /**
     * 移除指定位置的元素
     *
     * @param obj    - 目标元素
     * @param _index - 指定下标位置
     * @return
     */
    public boolean remove(Object obj, int _index) {
        if (_index > datas.length - 1 || _index < 0) {
            throw new RuntimeException("数组越界");
        }
        for (int j = _index + 1; j < index; j++) {
            datas[j - 1] = datas[j];
        }
        datas[index - 1] = null;
        index--;
        return true;
    }

    /**
     * 扩容
     */
    void copyArr() {
        Object[] tmp = new Object[initsize * 2];
        for (int i = 0; i < datas.length; i++) {
            tmp[i] = datas[i];
        }
        datas = tmp;
    }
}

class Test {

    public static void main(String[] args) throws Exception {
        MyArraylist myArraylist = new MyArraylist();
        myArraylist.add("a");
        myArraylist.add("b");
        myArraylist.add("c");
        myArraylist.add("d");
        myArraylist.add("e");
        myArraylist.get(2);
        myArraylist.add("f", 2);
        myArraylist.remove("f", 2);

    }
}
