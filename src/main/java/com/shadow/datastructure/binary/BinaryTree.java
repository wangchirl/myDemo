package com.shadow.datastructure.binary;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree implements Tree{

    private Node root;

    public BinaryTree(Node root){
        this.root = root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        System.out.print("二叉树的节点数：");
        return this.size(root);
    }

    private int size(Node node){
        if(node == null){
            return 0;
        }else {
            // 左子树的结点数
            int left = this.size(node.leftChild);
            // 右子树结点数
            int right = this.size(node.rightChild);
            return left+right+1;
        }

    }

    // 比较左右子树的高度，取最大的 + 1 ，递归调用，结果就是树的高度
    public int hight(Node node){
        if(node == null){
            return 0;
        }
        // 获取左子树的高度
        int leftV = hight(node.leftChild);
        // 获取右子树的高度
        int rightV = hight(node.rightChild);
        return (leftV > rightV ? leftV : rightV)  + 1;
    }

    @Override
    public int getHeight() {
        System.out.print("二叉树的高度：");
         return this.getHeight(root);
    }

    private int getHeight(Node node){
        if(node == null){
            return 0;
        }else {
            // 获取左子树的高度
            int left = this.getHeight(node.leftChild);
            // 获取右子树的高度
            int right = this.getHeight(node.rightChild);
            // 返回左子树、右子树中最大的高度并加 1
            return left >= right ? left +1 : right +1;
        }
    }

    @Override
    public Node findKey(Object value) {

        return this.findKey(root,value);
    }

    private Node findKey(Node root, Object value) {
        // check
        if(root == null){
            return null;
        }
        if(root.value == value){
            return root;
        }
        // 左子树查找
        Node node1 = findKey(root.leftChild, value);
        if(node1 != null && node1.value == value){
            return node1;
        }
        // 右子树查找
        Node node2 = findKey(root.rightChild, value);
        if(node2 != null && node2.value == value){
            return node2;
        }
        return null;
    }

    @Override
    public void preOrderTraverse() {
        System.out.print("先序遍历：");
        this.preOrderTraverse(root);
        System.out.println();
    }

    private void preOrderTraverse(Node node){
        // 递归结束条件
        if(node == null){
            return;
        }
        // 1.输出根结点的值
        System.out.print(node.value + " ");
        // 2.对左子树进行先序遍历
        this.preOrderTraverse(node.leftChild);
        // 3.对右子树进行先序遍历
        preOrderTraverse(node.rightChild);
    }

    @Override
    public void inOrderTraverse() {
        System.out.print("中序遍历：");
        this.inOrderTraverse(root);
        System.out.println();
    }

    private void inOrderTraverse(Node node){
        if(node == null){
            return;
        }
        // 中序遍历左子树
        this.inOrderTraverse(node.leftChild);
        // 输出结点值
        System.out.print(node.value + " ");
        // 中序遍历右子树
        this.inOrderTraverse(node.rightChild);
    }

    @Override
    public void postOrderTraverse() {
        System.out.print("后根遍历：");
        this.postOrderTraverse(root);
        System.out.println();
    }

    @Override
    public void postOrderTraverse(Node node) {
        // 递归结束条件
        if(node == null){
           return;
        }
        // 后序遍历左子树
        this.postOrderTraverse(node.leftChild);
        // 后序遍历右子树
        this.postOrderTraverse(node.rightChild);
        // 输出结点值
        System.out.print(node.value + " ");
    }

    @Override
    public void inOrderByStack(){
        System.out.print("中序非递归遍历：");
        // 借助栈
        Deque<Node> stack = new LinkedList();
        // 当前节点 默认设置为根
        Node current = root;
        while (current != null || !stack.isEmpty()){
            // 这里是将根节点的左节点全部入栈
            while (current != null){
                stack.push(current); // 入栈
                current = current.leftChild;// 指向左节点
            }
            // 出栈操作
            if(!stack.isEmpty()){
                current = stack.pop();// 指向出栈的元素
                System.out.print(current.value + " ");
                // 处理节点的右节点
                current = current.rightChild;
            }
        }
        System.out.println();
    }

    @Override
    public void preOrderByStack() {
        System.out.print("前序非递归遍历：");
        // 借助栈
        Deque<Node> stack = new LinkedList<>();
        Node current = root; // 当前节点默认 根节点
        while (current != null || !stack.isEmpty()){
            // 当前节点不为空则找左节点，为空则弹出栈顶元素找元素右节点
            if(current != null){
                // 根入栈
                stack.push(current);
                // 直接输出值
                System.out.print(current.value + " ");
                // 赋值为左节点
                current = current.leftChild;
            }else {
                // 出栈
                current = stack.pop();
                // 出栈的找右节点
                current = current.rightChild;
            }
        }
        System.out.println();
    }

    @Override
    public void postOrderByStack() {
        System.out.print("后序非递归遍历: ");
        Node current = root;
        if (current != null) {
            Deque<Node> s1 = new LinkedList<>();//双栈法
            Deque<Node> s2 = new LinkedList<>();
            // 入栈 s1
            s1.push(current);
            while (!s1.isEmpty()) {
                // 根出栈
                current = s1.pop();
                // 放到 s2 栈中
                s2.push(current);
                // 处理左节点
                if (current.leftChild != null) {
                    s1.push(current.leftChild);
                }
                // 处理右节点
                if (current.rightChild != null) {
                    s1.push(current.rightChild);
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }


    /**
     * 1.使用队列特性，先进先出，根入队
     * 2.取出根，获取根的左元素入队，获取根的右元素入队
     * 3.每次循环取出根，依次进行入队
     */
    @Override
    public void levelOrderByQueue() {
        System.out.print("层级遍历：");
        if(root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>(); // 对列保存树节点
        queue.add(root); // 根结点
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.value + " ");
            if (temp.leftChild != null) { // 添加左子节点到对列
                queue.add(temp.leftChild);
            }
            if (temp.rightChild != null) {// 添加右子节点到对列
                queue.add(temp.rightChild);
            }
        }
    }

}
