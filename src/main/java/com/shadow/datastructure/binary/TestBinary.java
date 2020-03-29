package com.shadow.datastructure.binary;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestBinary {
    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        simpleDateFormatThreadLocal.set(new SimpleDateFormat("yyyy-mm-dd"));
        Node node5 = new Node(5,null,null);

        Node node4 = new Node(4,null,node5);

        Node node7 = new Node(7,null,null);

        Node node6 = new Node(6,null,node7);

        Node node3 = new Node(3,null,null);

        Node node2 = new Node(2,node3,node6);

        Node node1 = new Node(1,node4,node2);

        BinaryTree binaryTree = new BinaryTree(node1);

        System.out.println(binaryTree.isEmpty());

        // 先序遍历递归 1 4 5 2 3 6 7
        binaryTree.preOrderTraverse();
        // 中序遍历递归 4 5 1 3 2 6 7
        binaryTree.inOrderTraverse();
        // 后序遍历递归 5 4 3 7 6 2 1
        binaryTree.postOrderTraverse();


        // 中序非递归
        binaryTree.inOrderByStack();
        System.out.println();

        // 先序非递归
        binaryTree.preOrderByStack();

        // 后根非递归
        binaryTree.postOrderByStack();

        // 层次遍历
        binaryTree.levelOrderByQueue();

        // 二叉树中查找某个值

        // 二叉树高度
        System.out.println();
        System.out.println(binaryTree.getHeight());
        System.out.println(binaryTree.hight(node1));

        // 二叉树结点数
        System.out.println("===================" + binaryTree.size());

        System.out.println(binaryTree.findKey(7));

//        arrayToTree(Arrays.asList(1,2,3,4,5,6,7));

    }


    public static void arrayToTree(List<Integer> arr){
        // 元素转换成Node入栈
        List<Node> list = new ArrayList<>();
        for (int i = arr.size()-1; i >= 0; i--) {
            list.add(new Node(arr.get(i)));
        }
        Node root = null;
        Node left = null;
        Node right = null;
        for (int i = 0; i < list.size(); i = 2*i){

        }

        boolean flag = true;
        if(flag = false){
            System.out.println(flag);
        }
    }





}
