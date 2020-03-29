package com.shadow.Mianshi;

import com.shadow.datastructure.binary.BinaryTree;

import java.util.*;

public class NiuKeTest {

    public static void main(String[] args) {
        int[][] a = new int[10][10];
        a[1][1] = 10;
        System.out.println(find(10, a));


        System.out.println(replaceSpace(new StringBuffer("We Are  Happy")));
//        System.out.println(printListFromTailToHead());
        int[] w = new int[]{1,2,4,7,3,5,6,8};
        int[] q = new int[]{4,7,2,1,5,3,8,6};

        TreeNode treeNode = reConstructBinaryTree(w, q);
        System.out.println(treeNode);

        System.out.println(minNumberInRotateArray(new int[]{3,4,5,1,2}));

        LinkedList<SingleNode> linkedList = new LinkedList<>();
        SingleNode s1 = new SingleNode(1);
        SingleNode s2 = new SingleNode(2,s1);
        SingleNode s3 = new SingleNode(3,s2);
        SingleNode s4 = new SingleNode(4,s3);
        SingleNode s5 = new SingleNode(5,s4);
        System.out.println(convertSingleLinkedList(s5));
        System.out.println(findKthToTail(s5,1));
        int[] array = new int[]{1,2,3,4,5,6,7};
        System.out.println(array2tree(array));

        System.out.println(findnNear(7));
        System.out.println(find(22));

    }

    //--------------------------------------------------------
    // 一个只包含0和1的矩阵，把所有的1替换为它到离它最近的0的步数。



    //--------------------------------------------------------
    // 一个无限长数列，前面两位是0,1,2，后面每一位等于它的前面第二位和第三位数之和，给出一个数，找出该数列中离它最近的数。
    // 0 1 2 1 3 3 4 6 7 10 13 17 23 30
    public static int find(int k){
        int n = 0;
        while (true){
            int near = findnNear(n++);
            if(near >= k){
                break;
            }
        }
        int res1 = findnNear(n - 1);
        int res2 = findnNear(n - 2);
        return (res1-k)>(k-res2)?res2:res1;
    }

    public static int findnNear(int n){
        if(n == 0 || n == 1|| n==2){
            return n;
        }
        return findnNear(n-2) + findnNear(n-3);
    }


    //---------------------------------------------------------
    // 数组转树
    public static TreeNode array2tree(int[] array){
        // 特殊情况处理
        if(array == null){
            return null;
        }
        if(array.length == 1){
            return new TreeNode(array[0]);
        }
        // 转树  左子树下标 2*i 右子树下标 2*i+1,i从1开始
        int[] temp = new int[array.length+1];
        for (int i = 0; i < array.length; i++) {
            temp[i+1] = array[i];
        }

        TreeNode head = null;
        for (int i = 1; i < temp.length; i++) {
            TreeNode node = new TreeNode(temp[i]);
            if(2*i >= temp.length){
                node.left = null;
            }else {
                node.left = new TreeNode(temp[2 * i]);
            }
            if((2*i +1) >= temp.length){
                node.right = null;
            }else{
                node.right = new TreeNode(temp[2*i+1]);
            }
            System.out.println(node);
        }

        return head;
    }




    //---------------------------------------------------------
    // 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
    public ListNode merge(ListNode list1,ListNode list2) {
        // 判断算法存在null的情况
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        // merge
        ListNode m = null;
        // 单调递增，list1 小，则将list1 放在前，重新设置其next值
        if(list1.val < list2.val){
            m = list1;
            m.next = merge(list1.next,list2);
        }else {
            m = list2;
            m.next = merge(list1,list2.next);
        }
        return m;
    }


    //------------------------------------------------------
    // 输入一个链表，输出该链表中倒数第k个结点。
    public static SingleNode findKthToTail(SingleNode head,int k) {
        // 计算链表的长度
        SingleNode c = head;
        int len = 0;
        while (c != null){
            c = c.next;
            len++;
        }
        // 遍历链表
        c = head;
        for (int i = 0; i < len; i++) {
            if(i == len-k){
                break;
            }
            c = c.next;
        }
        return c;
    }



    //-------------------------------------------------------
    // 反转单链表
    private static class SingleNode{
        private Object value;
        private SingleNode next;
        SingleNode(Object value){
            this.value = value;
            this.next = null;
        }
        SingleNode(Object value,SingleNode next){
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "SingleNode{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
    // 反正单链表
    public static SingleNode convertSingleLinkedList(SingleNode singleNode){
        // 当前指针节点
        SingleNode curr = singleNode;
        // 前指针节点
        SingleNode prev = null;
        // 每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while (curr != null){
            // 临时节点，暂存当前节点的下一个节点，用于后移
            SingleNode next = curr.next;
            // 将当前节点的下一个节点指向它前面的节点
            curr.next = prev;
            // 前指针后移
            prev = curr;
            // 当前指针后移
            curr = next;
        }
        return prev;
    }



    // --------------------------------------------------------
    // 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
    // n=1 1  n=2 2 n=3 3 n=4 5 n=5  8
    public static int jumpFloor(int target) {
        if(target == 0){
            return 0;
        }
        if(target == 1){
            return 1;
        }
        if(target == 2){
            return 2;
        }
        return jumpFloor(target-1) + jumpFloor(target-2);
    }


    //-----------------------------------------------------
    // 现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
    // 0 1 1 2 3 5 8...
    public static int fibonacci(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }





    // -------------------------------------------------
    // 非递减排序的数组的一个旋转，输出旋转数组的最小元素。
    public static int minNumberInRotateArray(int [] array) {
        // 数组为 0 时
        if(array == null ||array.length == 0){
            return 0;
        }
        // 数组只有一个元素时
        if(array.length == 1){
            return array[0];
        }
        // 默认第一个元素是最小的，类似选择排序，找到最小的元素下标
        int min = 0;
        for (int i = 1; i < array.length; i++) {
            //for (int j = i; j < array.length ; j++) {
                if(array[min] > array[i]){
                    min = i;
                }
            //}
        }
        return array[min];
    }




    // ----------------------------------------
    // 两个栈实现队列

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        // 取出栈1的元素放入到栈2
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        // 取出栈2 的栈顶元素
        int top = stack2.pop();
        // 将剩余的栈2的元素重新放入到栈1中去
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return top;
    }


    // ---------------------------------------------
    // 树的重新构建

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
            //数组长度为0的时候要处理
            if(pre.length == 0){
                return null;
            }

            int rootVal = pre[0];

            //数组长度仅为1的时候就要处理
            if(pre.length == 1){
                return new TreeNode(rootVal);
            }

            //我们先找到root所在的位置，确定好前序和中序中左子树和右子树序列的范围
            TreeNode root = new TreeNode(rootVal);
            int rootIndex = 0;
            for(int i=0;i<in.length;i++){
                if(rootVal == in[i]){
                    rootIndex = i;
                    break;
                }
            }

            //递归，假设root的左右子树都已经构建完毕，那么只要将左右子树安到root左右即可
            //这里注意Arrays.copyOfRange(int[],start,end)是[)的区间
            root.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,rootIndex+1),Arrays.copyOfRange(in,0,rootIndex));
            root.right = reConstructBinaryTree(Arrays.copyOfRange(pre,rootIndex+1,pre.length),Arrays.copyOfRange(in,rootIndex+1,in.length));


            return root;
        }

    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}


    // --------------------------------------------------
    // 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode temp = listNode;
        ArrayList<Integer> res = new ArrayList<>();
        while (temp != null) {
            res.add(temp.val);
            temp = temp.next;
        }
        ArrayList<Integer> res1 = new ArrayList<>();
        for (int i = res.size() - 1; i >= 0; i--) {
            res1.add(res.get(i));
        }
        return res1;
    }


    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        if (null != str) {
            String s = str.toString();
            s = s.replaceAll(" ", "%20");
            return s;
        }
        return null;
    }


    /**
     * 在一个二维数组中（每个一维数组的长度相同），
     * 每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param target
     * @param array
     * @return
     */
    public static boolean find(int target, int[][] array) {
        // 先遍历外层的值
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
