package com.shadow.dataStruct;

import java.util.Arrays;

/**
 * 冒泡排序
 *  1：比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 *  2：对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后
 *    的元素应该会是最大的数；
 *  3：针对所有的元素重复以上的步骤，除了最后一个；
 *  4：重复步骤 1~3，直到排序完成。
 */
public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] arr = new int[]{2,3,5,1,4,6,9,8,7};
        bubbleSort.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        bubbleSort.create(9,arr);
    }
    public void bubbleSort(int[] arr){
        // check
        if(null == arr){
            return;
        }
        if(arr.length <= 1){
            return;
        }
        // 排序
        int len = arr.length;// 数组大小
        for (int i = 0; i < len; i++) {// 外层循环-排序轮数
            // 每次冒泡会找出最大的元素放在最后，每循环一轮，减少一次，最后一个元素无需排序
            // 因此内层循环条件 len - i -1
            for (int j = 0; j < len-i-1; j++) {
                // 比较并交换
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }


    class Node{
        int v;
        Node next;
        Node(int v){
            this(v,null);
        }
        Node(int v,Node next){
            this.v = v;
            this.next = next;
        }
    }
    // 链表 特别注意使用临时变量啊 ！！！
    public void create(int n,int[] arr){
        bubbleSort(arr);
        Node head = null;
        Node t = null;
        for (int i = 0; i < arr.length; i++) {
            if(head == null){
                head = new Node(arr[i]);
                t = head;
            }else {
                t.next = new Node(arr[i]);
                t = t.next;
            }

        }
        while (head != null){
            System.out.println(head.v + " ");
            head = head.next;
        }
    }
}
