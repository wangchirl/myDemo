package com.shadow.dataStruct;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int n = 6;
        int index = binarySearch(arr, n);
        System.out.println(n + "所在数组的位置是：" + index);
    }

    /**
     * @param arr   有序序列
     * @param value 要查找的值
     * @return 索引位置
     */
    private static int binarySearch(int[] arr, int value) {
        int low = 0;
        int high = arr.length;
        return binarySearch(arr, value, low, high);
    }

    /**
     * 二分查找 - 非递归实现
     *
     * @param arr
     * @param value
     * @param low
     * @param high
     * @return
     */
    private static int binarySearch1(int[] arr, int value, int low, int high) {
        // check
        if (arr == null) {
            return -1;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == value) { // 刚好等于中间值
                return mid;
            } else if (arr[mid] < value) {// 大于中间值，右边找
                low = mid + 1;
            } else {// 小于中间值，左边找
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找 - 递归实现
     *
     * @param arr
     * @param value
     * @param low
     * @param high
     * @return
     */
    public static int binarySearch(int[] arr, int value, int low, int high) {
        // check
        if (arr == null) {
            return -1;
        }
        // 递归结束条件
        if (low >= high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (arr[mid] == value) {// 刚好等于中间值，直接返回
            return mid;
        } else if (arr[mid] < value) {// 大于中间值，右边找
            return binarySearch(arr, value, mid + 1, high);
        } else {// 小于中间值，左边找
            return binarySearch(arr, value, low, mid - 1);
        }

    }

}
