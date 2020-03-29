package com.shadow.dataStruct;

import java.util.Arrays;

/**
 * 插入排序
 * 1. 从第一个元素开始，该元素可以认为是已经排序的
 * 2. 取出下一个元素，**在已排序的元素序列中从后向前扫描**
 * 3. 如果该元素(已排序元素)大于新元素，将该元素移到下一位置
 * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5. 将新元素插入到该位置
 * 6. 重复2-5
 */
public class InsertSort {
    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        int[] arr = new int[]{2,3,5,1,4,6,9,8,7};
        insertSort.insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int[] arr) {
        // check
        if (null == arr || arr.length <= 1) {
            return;
        }
        // 排序
        int len = arr.length;
        // 默认第一元素有序，所以 i 从 1 开始
        for (int i = 1; i < len; i++) {
            // 待插入的元素
            int temp = arr[i];
            // 前置索引，即有序区间的最后一个元素的前面位置索引
            int index = i-1;
            // 倒序遍历已排序的，已排序的开始默认是下标 0，所以 j = i-1 ,j>=0
            for (int j = i-1; j >= 0; j--) {
                // 从后往前比较过程中，如果元素大于待插入元素则将元素后移一位
                if (temp < arr[j]) {
                    // 往后移动一位，前置索引往前移动一位
                    arr[index+1] = arr[index--];
                }
                // 比较过程中如果元素小于等于待插入元素，则将待插入元素放在该元素后面
                // 放在前置索引的后一位
                arr[index+1] = temp;
            }
        }
    }
}
