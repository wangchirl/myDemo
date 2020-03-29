package com.shadow.dataStruct;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 桶排序
 * 1. 元素值域的划分，也就是元素到桶的映射规则。
 * 映射规则需要根据待排序集合的元素分布特性进行选择，
 * 若规则设计的过于模糊、宽泛，则可能导致待排序集合中所有元素全部映射到一个桶上，
 * 若映射规则设计的过于具体、严苛，则可能导致待排序集合中每一个元素值映射到一个桶上
 * 2. 从待排序集合中元素映射到各个桶上的过程，
 * 并不存在元素的比较和交换操作，**在对各个桶中元素进行排序时，
 * 可以自主选择合适的排序算法，每个桶内的排序算法的复杂度和稳定性，
 * 决定了最终的算法的复杂度和稳定性**
 */
public class BucketSort {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{2,3,5,1,4,6,9,8,7,1,1,6,1,11,3,3,3,2,3};
        List<Integer> list = BucketSort.bucketSort(Arrays.asList(arr), 9);
        System.out.println(list);
    }

    /**
     * 桶排序
     * 数组不太好操作，换成集合类
     * @param arr        待排序数组
     * @param bucketSize 桶中元素类型的个数，即每个桶中所能放置多少个不同树中
     * @return 排好序的数组
     */
    public static List<Integer> bucketSort(List<Integer> arr, int bucketSize) {
        // check
        if(null == arr || arr.size() <= 1 || bucketSize < 1){
            return arr;
        }
        // 找出数组中最大，最小的值
        int min = arr.get(0);
        int max = arr.get(0);
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i) < min){
                min = arr.get(i);
            }
            if(arr.get(i)> max){
                max = arr.get(i);
            }
        }
        // 计算桶的个数 ：最大值 - 最小值 代表了数组中元素取值范围区间
        int bucketCount = (max - min)/bucketSize + 1;
        // 按序创建桶，创建一个数组，数组下标是有序的，数组中的每一个元素是一个桶，桶也是一个数组
        List<List<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            bucketList.add(new ArrayList<>());
        }
        // 将待排序的集合依次添加到对应的桶中
        for (int i = 0; i < arr.size(); i++) {
            int bucketIndex = (arr.get(i) -min)/bucketSize;
            bucketList.get(bucketIndex).add(arr.get(i));
        }
        // 对每一个桶中的数据进行排序（可以使用别的排序方式）
        // 然后再将桶中的数据依次取出存放到一个最终的集合中
        // 创建最终集合
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < bucketList.size(); i++) {
            // 取出每一个桶
            List<Integer> everyBucket = bucketList.get(i);
            // 看桶内是否有元素
            if(everyBucket.size() > 0){
                // 递归的调用桶排序为每一个桶进行排序
                // 当某次排序待排序集合都分配到一个桶中时，缩小桶的范围以获得更多得桶
                if(bucketCount == 1){
                    bucketSize--;
                }
                List<Integer> temp = bucketSort(everyBucket, bucketSize);
                for (int j = 0; j < temp.size(); j++) {
                    resultList.add(temp.get(j));
                }
            }
        }
        return resultList;
    }
}
