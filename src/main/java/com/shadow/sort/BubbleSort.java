package com.shadow.sort;

import java.util.Arrays;

public class BubbleSort {


    public static void main(String[] args) {
        int [] a = {4,6,7,1,2,3};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
        Integer [] b = {4,6,7,1,2,3};
        bubbleSort(b);
        System.out.println(Arrays.toString(b));
        int [] c = {4,6,7,1,2,3};
        bubbleSort(c);
        System.out.println(Arrays.toString(c));
        Integer [] d = {4,6,7,1,2,3};
        selectSort(d);
        System.out.println(Arrays.toString(d));
        int [] e = {4,6,7,1,2,3};
        insertSort(e);
        System.out.println(Arrays.toString(e));
        Integer [] f = {4,6,7,1,2,3};
        insertSort(f);
        System.out.println(Arrays.toString(f));
        Integer [] g = {4,6,7,1,2,3};
        shellSort(g);
        System.out.println(Arrays.toString(g));
        Integer [] h = {4,6,7,1,2,3};
        mergeSort(h);
        System.out.println(Arrays.toString(h));

        Integer [] i = {4,6,7,1,2,3};
//        System.out.println(partition(i,0,i.length-1));
//        System.out.println(Arrays.toString(i));
        quickSort(i);
        System.out.println(Arrays.toString(i));
    }



    // 快速排序
    public static void quickSort(Comparable[] array){
        int low = 0;
        int high = array.length - 1;
        quickSort(array,low,high);
    }

    private static void quickSort(Comparable[] array, int low, int high) {
        if(low < high){
            //分区操作， 将一个数组分成两个分区，返回分区界限索引
            int index = partition(array,low,high);
            //对左边分区进行快排
            quickSort(array,low,index-1);
            //对右边分区进行快排
            quickSort(array,index+1,high);
        }
    }

    private static int partition(Comparable[] array, int low, int high) {
        // 指定左指针 i 和右指针 j
        int i = low;
        int j = high;
        // 将第一个数作为基准数，挖坑
        Comparable x = array[low];
        // 使用循环实现分区操作
        while (i < j){ // 指针位置不能超
            // 1.从右边向左移动j，找到第一个小于基准值的值 araay[j]
            while (greater(array[j],x) && i < j){ // 循环找，大于的话 指针 j--
                j--;
            }
            // 2.将右侧找到的小于基准值的值加入到左边的坑位置，左指针加1 i++
            if(i < j){
                array[i] = array[j];
                i++;
            }
            // 3.从左向右移动i，找到第一个大于基准值的值 array[i]
            while (less(array[i],x) && i < j){ // 循环找 小于的话指针 i++
                i++;
            }
            // 4.将左侧找到的大于等于基准值的值加入到右边的坑中，右指针向左移动，j--
            if(i < j){
                array[j] = array[i];
                j--;
            }
        }
        // 使用基准值填坑
        array[i] = x; // array[j] = x;
        // 返回基准值的位置索引
        return i; // return j;
    }


    // -----------------------------------------



    private static Comparable[] assist;

    /**
     * 对数组 array中的元素进行排序
     * @param array
     */
    public static void mergeSort(Comparable[] array){
        // 1.初始化辅助数组 assist
        assist = new Comparable[array.length];
        // 2.定义一个lo变量，和 hi 变量，分别记录数组中最小的索引和最大的索引
        int lo = 0;
        int hi = array.length-1;
        // 3. 调用sort 重载方法对数组array中的从索引lo到索引hi的元素进行排序
        mergeSort(array,lo,hi);
    }

    /**
     *  对数组 array 中从lo到hi的元素进行排序
     * @param array
     * @param lo
     * @param hi
     */
    private static void mergeSort(Comparable[] array,int lo, int hi){
        // 安全性校验
        if(hi <= lo){
            return;
        }
        // 对lo到hi之间的数进行分为两个组
        int mid = lo + (hi-lo)/2;
        // 分别对每一组数据进行排序
        mergeSort(array,lo,mid);
        mergeSort(array,mid+1,hi);
        // 再把两个组中的数据进行归并
        merge(array,lo,mid,hi);
    }

    /**
     * 对数组中从lo到mid为一组，从mid+1到hi为一组，对这两组数据进行归并
     * @param array
     * @param lo
     * @param mid
     * @param hi
     */
    private static void merge(Comparable[] array,int lo,int mid,int hi){
        // 定义三个指针
        int i = lo;
        int p1 = lo;
        int p2 = mid+1;
        // 遍历，移动 p1 和 p2 指针，比较索引处的值，找出小的那个，放到辅助数组的对应索引处
        while (p1 <= mid && p2 <= hi){
            // 比较对应索引处的值
            if(less(array[p1],array[p2])){
                assist[i++] = array[p1++];
            }else {
                assist[i++] = array[p2++];
            }
        }
        // 如果 p1 的指针没有走完，那么顺序移动 p1 指针，把对应的元素放到辅助数组的对应索引处
        while (p1 <= mid){
            assist[i++] = array[p1++];
        }
        // 如果 p2 的指针没有走完，那么顺序移动 p2 指针，把对应的元素放到辅助数组的对应索引处
        while (p2 <= hi){
            assist[i++] = array[p2++];
        }
        // 把辅助数组中的元素拷贝到原素组中 lo 到 hi 索引内
        for (int j = lo; j <= hi; j++) {
            array[j] = assist[j];
        }
    }


    /**
     *  比较v1 元素是否小于 v2 元素
     * @param v1
     * @param v2
     * @return
     */
    public static boolean less(Comparable v1,Comparable v2){
        return v1.compareTo(v2) < 0;
    }


    // -------------------------------------------------


    public static void shellSort(Comparable[] array){
        // 1.根据 array 的长度，确定增长量的初始值
        int h = 1;
        while (h < array.length){
            h = 2*h +1;
        }
        // 2.希尔排序
        while (h >= 1){
            // 排序
            // 2.1 找到待插入的元素
            for (int i = 0; i < array.length; i++) {
                // 2.2 把待插入元素插入到有序数列中
                for (int j = i;j>=h; j-=h) {
                    // 待插入元素是 array[j] 比较array[j]和array[j-h]
                    if(greater(array[j-h],array[j])){
                        // 交换元素
                        exch(array,j-h,j);
                    }else {
                        break;
                    }
                }
            }
            // 减小h的值
            h = h/2;
        }
    }







    public static void insertSort(int[] array){
        // 从第二个元素开始比较
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(array[j-1] > array[j]){ // 将要比较的元素加入到已经排序的组中，往前比较，如果前一个元素比其大则进行交换，否则退出
                    int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }else {
                    break;
                }
            }
        }
    }




    // 插入排序 -- 扑克牌
    public static void insertSort(Comparable[] array){
        // 默认第一个是有序的，从第二个开始进行比较
        for (int i = 1; i < array.length; i++) {
            // 对已排序的进行倒序遍历
            for (int j = i; j > 0; j--) {
                // 找到前一个元素比当前元素大则进行交换，否则退出
                if (greater(array[j-1],array[j])){
                    exch(array,j-1,j);
                }else {
                    break;
                }
            }
        }
    }



    // 选择排序
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            int index = i;
            for (int j = 0; j < array.length; j++) {
                if(array[index] > array[j]){
                    // 最小下标互换
                    index = j;
                }
            }
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }





    // 选择排序
    public static void selectSort(Comparable[] array){
        // 默认 下标 0 的元素最小，记住下标，依次循环比较大小，找出真正的最小元素下标，然后进行交换
        for (int i = 0; i <= array.length-2; i++) {
            int minIndex = i;
            // 从 i 的 后一个元素开始比较
            for (int j = i+1; j < array.length; j++) {
                if(greater(array[minIndex],array[j])){
                    // 将最小元素的下标进行更新
                    minIndex = j;
                }
            }
            exch(array,i,minIndex);
        }
    }







    // 冒泡排序
    public static void bubbleSort(int[] arr){
        for(int i = arr.length-1; i > 0; i--){
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void bubbleSort(Comparable[] array){
        // 外层循环，参与排序的元素个数为 array.length，这里是索引因此 i = array.length-1
        for (int i = array.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                // 比较大小
                if(greater(array[j],array[j+1])){
                    exch(array,j,j+1);
                }
            }
        }
    }

    /**
     * v1 比 v2 大则返回 true，否则返回 false
     * @param v1
     * @param v2
     * @return
     */
    private static boolean greater(Comparable v1,Comparable v2){
        return v1.compareTo(v2) > 0;
    }

    /**
     * 完成交换任务 数组的元素
     * @param array
     * @param i
     * @param j
     */
    private static void exch(Comparable[] array, int i, int j){
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
