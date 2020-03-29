package com.shadow.dataStruct;

import java.util.Arrays;

/**
 * 基数排序
 * 1. 找出数组中的最大值
 * 2. 对最大值循环进行按位排序，即个位，十位，百位...
 * 3. 排序过程类似计数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,5,1,4,6,9,8,7};
        RadixSort.radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        // 1.取得数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 2.从个位开始，对数组按位进行排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            sort(arr, exp);
        }
    }

    /**
     * 对数组按位进行排序(桶排序过程)
     * 不同的是，这里按位进行，桶只需要 10 个，即 0-9 的桶
     *
     * @param arr
     * @param exp
     */
    private static void sort(int[] arr, int exp) {
        // 临时数组
        int[] temp = new int[arr.length];
        // 定义桶
        int[] buckets = new int[10];
        // 对数组中的元素找到对应位的值，分配到对应的桶中，做统计
        for (int i = 0; i < arr.length; i++) {
            // arr[i]/exp 取得元素对应位的值，%10 是确定在当前位所在桶的位置
            // 这里是来左统计的 因此 ++
            buckets[(arr[i] / exp) % 10]++;
        }
        // 桶内的元素是按位统计的总数
        // 这里对每个桶进行汇总，确定元素的位置范围
        for (int i = 1; i < buckets.length; i++) {
            buckets[i] += buckets[i - 1];
        }
        // 将数据存储到临时数组中
        // 逆序
        for (int i = arr.length - 1; i >= 0; i--) {
            // 这里和统计位时一样，-1 是取下标
            temp[buckets[(arr[i] / exp) % 10] - 1] = arr[i];
            // 范围缩减 1
            buckets[(arr[i] / exp) % 10]--;
        }
        // 将排序好的数据赋值给原数组
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }
        // 置空，以供GC回收
        temp = null;
        buckets = null;
    }
}
