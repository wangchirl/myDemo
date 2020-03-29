package com.shadow.dataStruct.tree;

/**
 * 二叉查找树 [链表结构]
 */
public class BinarySearchTree {


    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
//        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
//        for (int i = 1; i < arr.length; i++) {
//            binarySearchTree.put(i);
//        }
//        System.out.println(binarySearchTree.find(6));
//        System.out.println(binarySearchTree.parent);

        binarySearchTree.put(16);
        binarySearchTree.put(14);
        binarySearchTree.put(35);
        binarySearchTree.put(12);
        binarySearchTree.put(15);
        binarySearchTree.put(25);
        binarySearchTree.put(40);
        binarySearchTree.put(10);
        binarySearchTree.put(20);
        binarySearchTree.put(27);
        binarySearchTree.put(38);
        binarySearchTree.put(41);
        binarySearchTree.put(26);
        binarySearchTree.put(30);
        binarySearchTree.put(39);

        binarySearchTree.delete(38);

        System.out.println(binarySearchTree);

    }


    /**
     * 父节点
     */
    Node parent;


    /**
     * 节点删除
     * 1. 删除叶子节点
     * 2. 删除只有一个子节点的节点
     * 3. 删除有两个子节点的节点
     * @param value
     * @return
     */
    public Node delete(int value) {
        // 记录要删除的节点
        Node p = parent;
        // 记录要删除节点的父节点
        Node p_parent = null;
        // 先找到要删除的元素及其父元素
        while (p != null){
            if(p.value > value){ // 小于则在左子树中找
                p_parent = p;
                p = p.left;
            }else if(p.value < value){// 大于则在右子树中找
                p_parent = p;
                p = p.right;
            }else {// 找到了
                break;
            }
        }
        // 判断要删除的元素是否存在
        if(p == null){
            return null;
        }
        // 1.要删除的元素有两个子节点，最复杂的情况
        // 我们需要找到右子树中最小的元素替换掉要删除节点的位置
        if(p.left != null && p.right != null){
            // 右子树
            Node rTree = p.right;
            // rTree 的父节点
            Node rTree_p = p;
            // 找到右子树中最小的节点 - 此节点肯定在左子树中
            while (rTree.left != null){
                rTree_p = rTree;
                rTree = rTree.left;
            }
            // 用右子树中最小节点替换掉删除的节点位置
            p.value = rTree.value;
            // 对要删除元素和要删除元素父节点赋值，这里先不进行删除，后续一起处理
            p = rTree;
            p_parent = rTree_p;
        }
        // 2.要删除的节点是叶子节点，或者是只有一个叶子的节点
        Node child = null;
        if(p.right != null){
            child = p.right;
        }else if(p.left != null){
            child = p.left;
        }else { // 没有子节点
            child = null;
        }
        // 删除节点
        if(p_parent == null){ // 父节点为null，表示要删除根节点
            parent = child;
        }else if(p_parent.left == p){ // 父节点不为空且要删除的节点是左节点，将左节点后移到要删除元素的左节点
            p_parent.left = child;
        }else { // 否则是右节点
            p_parent.right = child;
        }
        return p;
    }


    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public Node find(int value) {
        // check
        if (parent == null) {
            return null;
        }
        Node root = parent;
        while (root != null) {
            if (root.value == value) { // 刚好是根节点
                return root;
            } else if (root.value > value) {// 左子树查找
                root = root.left;
            } else {// 右子树查找
                root = root.right;
            }
        }
        return null;
    }


    /**
     * 向二叉查找树中插入元素
     * 1. 树存在
     * 2. 树不存在
     * @param value
     * @return
     */
    public boolean put(int value) {
        // 如果树还没有，那么创建树，并赋值给parent
        if (parent == null) {
            parent = createNode(value);
            return true;
        }
        Node root = parent;
        while (root != null) {
            // 当前我们插入的数据比root的值小，那么存储在左子树上
            if (root.value > value) {
                // 要判断左子树是否存在元素
                if (root.left == null) {
                    root.left = createNode(value);
                    return true;
                }
                root = root.left;
            } else if (root.value < value) { // 要插入的数据大于 root 的值，那么存储在右子树上
                if (root.right == null) {
                    root.right = createNode(value);
                    return true;
                }
                root = root.right;
            }
        }
        return false;
    }


    /**
     * 构造一个没有左右子树的节点
     *
     * @param value
     * @return
     */
    private Node createNode(int value) {
        return new Node(value, null, null);
    }

    /**
     * 构造一个右左右子树的节点
     *
     * @param value
     * @param left
     * @param right
     * @return
     */
    private Node createNode(int value, Node left, Node right) {
        return new Node(value, left, right);
    }


    private static class Node {
        // 存储值
        private int value;
        // 左子节点指针
        private Node left;
        // 右子节点指针
        private Node right;

        // 构造方法
        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        // getter -- setter
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

}
