package com.bjpowernode.test.datastructure;

public class Mysort {

    /**
     * [4,2,5,7,3,8]排序
     * 第一次结果2、4、5、3、7、8
     * 2、4、3、5、7、8
     * 2、3、4、5、7、8
     */

    /*public static void main(String[] args) {
            int [] arrs = {8,7,6,5,4,3};
       // MP_sort(arrs);
        System.out.println("============");
        SELECT_sort(arrs);
    }*/
    public static void MP_sort(int[] arrs) {

        for (int i = 0; i < arrs.length - 1; i++) {

            for (int j = 0; j < arrs.length - 1 - i; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    int tmp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = tmp;
                }
            }
            System.out.println();
            for (int k : arrs) {
                System.out.print(k);
            }

        }
    }

    /**
     * [4,2,5,7,3,8]排序
     */
    public static void SELECT_sort(int[] arrs) {

        for (int i = 0; i < arrs.length - 1; i++) {
            int index = i;//认为他就是最小元素的下标
            for (int j = i + 1; j < arrs.length; j++) {
                if (arrs[i] > arrs[j]) {
                    index = j;
                }
            }
            if (index != i) {
                //最小值已变更
                int tmp = arrs[index];
                arrs[index] = arrs[i];
                arrs[i] = tmp;
            }
            System.out.println();
            for (int k : arrs) {
                System.out.print(k);
            }

        }
    }

    /**
     * 快速排序
     * 思路：选取基准元素tmp = arr[start]，先后从往前遍历，如果tmp < arr(end),则end--，
     * 直到tmp >= arr(end)，此时让arr[start]=arr[end]
     * 然后从左往右遍历，如果tmp > arr[start]，start++，
     * 直到tmp <= arr[start],此时让arr[end]=arr[start]
     * <p>
     * 这样遍历一次之后，tmp所在的位置就是中间位置,同理，遍历tmp左边和右边的子数组。
     * 最终循环结束，就是有序的数组
     *
     * @param arr   - 数组
     * @param start - 起始位置
     * @param end   - 结束位置
     */
    public static void quickSort(int[] arr, int start, int end) {
        //数组arr空or仅有一个元素则退出
        if (start >= end - 1)
            return;

        if (start < end) {
            //寻找基准元素的位置
            int index = getIndex(arr, start, end);

            //遍历index位置左边和右边的子数组。
            quickSort(arr, 0, index - 1);
            quickSort(arr, index + 1, end);
        }
    }

    public static int getIndex(int[] arr, int start, int end) {

        //选取基准数据
        int tmp = arr[start];

        while (start < end) {
            //开始从右往左遍历，当队尾元素大于等于tmp时,向前挪动指针
            while (start < end && tmp <= arr[end]) {
                end--;
            }
            //执行到这里说明tmp大于arr[end]了，让end位置的值赋值给start位置的值
            arr[start] = arr[end];

            //开始从左往右遍历,当队首元素小于等于tmp时,向后挪动指针
            while (start < end && tmp >= arr[start]) {
                start++;
            }
            arr[end] = arr[start];
        }

        //执行到这里，start和end肯定相等，也就是tmp的位置，需要将tmp赋值给arr[start]
        arr[start] = tmp;
        return start;
    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(1000);
    }

    static void mergeSort(int[] arr, int bgn, int end) {
        // int [] arrs = {8,12,6,13,4,23};
        //数组arr空or仅有一个元素则退出
        if (bgn >= end - 1)
            return;

        int mid = (bgn + end) / 2;
        mergeSort(arr, bgn, mid);
        mergeSort(arr, mid, end);
        //mergeSortInOrder(arr, bgn, mid, end);
    }


}
