package com.shadow.dataStruct;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[8];
        arr[1] = 6;
        arr[2] = 4;
        arr[3] = 2;
        arr[4] = 1;
        arr[5] = 3;
        arr[6] = 7;
        arr[7] = 5;
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
    
    
    public static void heapSort(int[] arr){
        // 1.先建堆 - 大顶堆或小顶堆
        buildHeap(arr,arr.length-1);
        // 2.排序
        sort(arr,arr.length-1);
    }

    /**
     * 我们把堆顶元素跟最后一个元素互换,然后对 1~n-1 区间的元素再堆化,然后在
     * 将堆顶元素跟最后一个元素互换继续操作，
     * 有点类型删除堆顶元素
     * @param arr 主要这里数组中的元素是从1开始的
     * @param n
     */
    private static void sort(int[] arr, int n) {
        while (n > 1){
            // 将堆中的第一个元素和最后一个元素交换位置
            int temp = arr[1];
            arr[1] = arr[n];
            arr[n] = temp;
            // 递归调用 - 建堆 - 再排序
            headifFromTop2Button(arr,1,--n);
        }
    }


    /**
     * 建堆：建大顶堆，堆化是自上而下堆化
     *
     * @param arr 待建堆的数组
     * @param n   数组最后一个元素的下标 -- 堆中最后一个元素的下标
     */
    public static void buildHeap(int[] arr, int n) {
        // 从后往前处理数组数据，堆化的时候是自上而下堆化
        for (int i = n / 2; i > 0; i--) {
            headifFromTop2Button(arr, i, n);
        }
    }

    private static void headifFromTop2Button(int[] arr, int begin, int end) {
        while (true) {
            // 定义最大值的下标
            int maxPos = begin;
            // 比较当前节点与其左子节点，右子节点的大小关系，找出最大值
            // 左子节点
            if (2 * begin <= end && arr[maxPos] < arr[2 * begin]) {
                maxPos = 2 * begin;
            }
            // 右子节点
            if (2 * begin + 1 <= end && arr[maxPos] < arr[2 * begin + 1]) {
                maxPos = 2 * begin + 1;
            }
            // 当循环经过前面两个条件都没有堆maxPos值进行修改时
            // 说明已经到达数组末尾了，跳出循环
            if(begin == maxPos){
                break;
            }
            // 交换元素
            int temp = arr[begin];
            arr[begin] = arr[maxPos];
            arr[maxPos] = temp;
            // 开始位置移动到最大值的位置
            begin = maxPos;
        }
    }
}
