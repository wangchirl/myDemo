package com.shadow;

import java.util.Arrays;
import java.util.List;

class Node {
    public Integer value;
    public Node left;
    public Node right;

    public Node(Integer value) {
        this.value = value;
        this.right = null;
        this.left = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

public class Application {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(resolve(Arrays.asList(array)));
    }
    public static Node resolve(List<Integer> input) {
        int index = input.get(0);
        //根节点为第一个数
        Node root = new Node(index);
        // 左孩子
        if(index * 2 + 1 < input.size()){
            List<Integer> subList = input.subList(index * 2, input.size());
            System.out.println(subList);
            root.left = resolve(subList);
        }

        // 右孩子
        if(index * 2 + 2 < input.size()){
            List<Integer> subList = input.subList(index * 2 + 1, input.size());
            System.out.println(subList);
            root.right = resolve(subList);
        }
//        Thread.State
        return root;
    }
}