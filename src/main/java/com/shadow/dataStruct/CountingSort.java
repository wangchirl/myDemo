package com.shadow.dataStruct;

import java.util.Arrays;

/**
 * 计数排序
 * 1. 找出待排序的数组中最大和最小的元素
 * 2. 统计数组中每个值为 i 的元素出现的次数，存入额外数组C的第 i 项
 * 3. **对所有的计数累加**
 * 4. 反向填充目标数组，将每个元素 i 放在新数组的第C(i) 项，每放一个元素就将新数组的 C(I) 减去 1
 */
public class CountingSort {


    public static void main(String[] args) {
        int[] arr = new int[]{2,3,5,1,4,6,9,8,7};
        CountingSort.countingSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void countingSort(int[] arr){
        // 找出待排序数组的最大，最小值，找出取值区间
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
            if(arr[i] < min){
                min = arr[i];
            }
        }
        // 定义一个额外的数组
        int bucketSize = max - min + 1;
        int[] bucket = new int[bucketSize];
        // 统计对应元素的个数，数组的下标不是单纯的值
        for (int i = 0; i < bucket.length; i++) {
            // 取出原数组中的值减去最小值，确定在新数组中的位置
            // 可能出现多次一样的值，记录出现的次数 += 1
            int bucketIndex = arr[i] - min;
            bucket[bucketIndex] += 1;
        }
        // 对额外数组元素进行累加，值全部加起来
        for (int i = 1; i < bucket.length; i++) {
            bucket[i] = bucket[i] + bucket[i-1];
        }
        // 创建临时数组 R 存储最终有序的数据列表
        int[] temp = new int[arr.length];
        // 逆序扫描待排序的数组，可保证元素的稳定性
        for (int i = arr.length-1; i >= 0 ; i--) {
            // 这里和额外数组处理一样，目的就是为了能对应上 bucket 的下标
            // 也会出现多次
            int bucketIndex = arr[i] - min;
            // bucket[bucketIndex] 这里是取得累加后的值
            // 也是对应temp数组中占有多少位置
            // 每给temp赋值一次，将temp下标 bucket[bucketIndex] -= 1 往前移动一位
            temp[bucket[bucketIndex]-1] = arr[i];
            bucket[bucketIndex] -= 1;
        }
        // 将临时数据列表依次放入原始数组
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }
    }
}

