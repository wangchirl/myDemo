package com.shadow.dataStruct;

import java.util.Arrays;

/**
 * 快速排序
 * 1. 从数列中挑出一个元素作为基准值（默认数列第一个元素）[挖坑]，从数列最后往前找，找出第一个小于基准值的元素，放在基准值的原来位置[挖坑]，前指针后移一位，然后再从左边往后找，找到第一个大于基准值的元素，将该元素放在上一个挖坑的位置，后指针前移一位，依次进行
 * 2. 遍历数列，将小于基准值的元素放在左边，大于基准值的元素放在右边，这样做完后会将数列分为三个区，基准值处于数列中间位置，这个动作称为分区（partition）操作
 * 3. 递归地（recursive）把小于基准值的子数列和大于基准值的子数列排序（**这里的关键是找到基准值最终在数列中的位置**）
 */
public class QuickSort {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = new int[]{2, 3, 5, 1, 4, 6, 9, 8, 7};
        quickSort.quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        // check
        if (null == arr || arr.length <= 1) {
            return;
        }
        // 前后元素位置
        int low = 0;
        int high = arr.length - 1;
        quickSort(arr, low, high);
    }

    private static void quickSort(int[] arr, int low, int high) {
        // 递归退出条件
        if (low >= high) {
            return;
        }
        // 分区，找到基准值元素位置
        // int midIndex = partition(arr,low,high);
        int midIndex = partition2(arr, low, high);
        // 递归完成 左分区和右分区 排序
        quickSort(arr, low, midIndex - 1);
        quickSort(arr, midIndex + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        // 确定基准值，取最后一个元素为基准值
        int pivot = arr[high];
        // 定义基准值最终存放的位置index
        int pivotIndex = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {// 如果比基准值小则交换
                // 交换
                if (i > pivotIndex) {//i只可能大于等于pivotIndex，等于情况不进行交换
                    // 将小于的元素进行元素交换
                    int temp = arr[i];
                    arr[i] = arr[pivotIndex];
                    arr[pivotIndex] = temp;
                }
                // 向后移动一位
                pivotIndex++;
            }
        }
        // 找到位置进行交换
        int temp = arr[pivotIndex];
        arr[pivotIndex] = pivot;
        arr[high] = temp;
        return pivotIndex;
    }

    // 另外一种思考方式分区操作
    // 挖坑 - 填坑 东拆西补或西拆东补，一边拆一边补
    private static int partition2(int[] array, int low, int high) {
        // 指定左指针 i 和右指针 j
        int i = low;
        int j = high;
        // 将第一个数作为基准数，挖坑
        int x = array[low];
        // 使用循环实现分区操作
        while (i < j) { // 指针位置不能超
            // 1.从右边向左移动j，找到第一个小于基准值的值 araay[j]
            while ((array[j] > x) && i < j) { // 循环找，大于的话 指针 j--
                j--;
            }
            // 2.将右侧找到的小于基准值的值加入到左边的坑位置，左指针加1 i++
            if (i < j) {
                array[i] = array[j];
                i++;
            }
            // 3.从左向右移动i，找到第一个大于基准值的值 array[i]
            while ((array[i] < x) && i < j) { // 循环找 小于的话指针 i++
                i++;
            }
            // 4.将左侧找到的大于等于基准值的值加入到右边的坑中，右指针向左移动，j--
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }
        // 使用基准值填坑
        array[i] = x; // array[j] = x;
        // 返回基准值的位置索引
        return i; // return j;
    }


}
