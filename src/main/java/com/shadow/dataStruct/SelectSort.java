package com.shadow.dataStruct;

import java.util.Arrays;

/**
 * 选择排序
 * 1. 初始化状态：无序区间为R[1...n]，有序区间为空
 * 2. 每一趟都从未排序的区间找到最小元素的下标，将其与无序区间的第一个元素进行交换
 * 3. 重复2，直到最后
 */
public class SelectSort {
    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        int[] arr = new int[]{2,3,5,1,4,6,9,8,7};
        selectSort.selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public  void selectSort(int[] arr){
        // check
        if(null == arr || arr.length <= 1){
            return;
        }
        // 排序
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            // 默认第 i 个位置的元素最小
            int minIndex = i;
            for (int j = i; j < len ; j++) {
                // 找到比 minIndex 还小的元素时
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            // 元素替换
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
