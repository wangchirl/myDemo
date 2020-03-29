package com.shadow.dataStruct;

import java.util.Arrays;

/**
 * 归并排序
 * 1. 把长度为n的输入序列分成两个长度为 n/2的子序列[递归分区，直到序列只剩下一个元素为止]
 * 2. 对这两个子序列分别采用归并排序
 * 3. 将两个排序好的子序列合并成一个最终的排序序列[借助辅助数组]
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 4, 6, 9, 8, 7};
        arr = MergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 归并排序
     *
     * @param arr
     * @return 返回排序后的数组
     */
    public static int[] mergeSort(int[] arr) {
        // check
        if (null == arr || arr.length <= 1) {
            return arr;
        }
        // 将数组从中间拆分为左右两部分
        int mid = arr.length >> 1;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 合并两个序列，并进行排序
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        // 创建临时数组，顺序从小到大存入两个数组中的元素
        int[] newArr = new int[left.length + right.length];
        // 定义两个指针，分别指向两个数组
        int lIndex = 0;
        int rIndex = 0;
        // 给临时数组赋值
        for (int i = 0; i < newArr.length; i++) {
            // 比较两个数组中的元素
            // 如果left数组的下标都超过了left的最大长度，则只需要放入right元素
            if (lIndex >= left.length) {
                newArr[i] = right[rIndex++]; // 放入元素后right指针后移一位
            } else if (rIndex >= right.length) {// 相反情况
                newArr[i] = left[lIndex++];
            } else if (left[lIndex] > right[rIndex]) {// 左元素大于右元素，放入右元素
                newArr[i] = right[rIndex++];
            } else {
                newArr[i] = left[lIndex++];
            }
        }
        // 返回临时数组
        return newArr;
    }
}
