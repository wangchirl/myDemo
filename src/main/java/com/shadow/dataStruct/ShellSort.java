package com.shadow.dataStruct;

import java.util.Arrays;

/**
 * shell 排序
 * 1. 根据数组长度确定初始增长量 gap = length/2 进行分组
 * 2. 对分组的元素进行插入排序
 * 3. 降低增长量 gap 的值 gap = gap/2，继续分组进行排序
 * 4. 直到 gap > 0 时结束
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 4, 6, 9, 8, 7};
        ShellSort.shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        int len = arr.length;
        // 进行分组，初始增长量为数组的一半
        for (int gap = len / 2; gap > 0; gap /= 2) {
            // 对各个组分别进行插入排序
            for (int i = gap; i < len; i++) {
                // 将 arr[i] 插入到所在分组的正确位置上
                insertSort(arr, gap, i);
            }
        }
    }

    private static void insertSort(int[] arr, int gap, int i) {
        // 待插入元素
        int item = arr[i];
        // 设置前置索引
        int preIndex = i - gap;
        // 插入的时候按组进行插入（组内元素两两相隔 gap)
        for (int j = i - gap; j >= 0 && item < arr[j]; j -= gap) {
            // 待插入元素小于当前元素，元素往后移
            arr[preIndex + gap] = arr[preIndex];
            // 前置索引往前移动 gap 位
            preIndex -= gap;
        }
        // 最终的位置
        arr[preIndex + gap] = item;
    }
}
