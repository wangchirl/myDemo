package com.shadow.dataStruct.tree;

public class AvlTree<T extends Comparable> {


    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        tree.insert(10);
        tree.insert(8);
        tree.insert(3);
        tree.insert(12);
        tree.insert(9);
        tree.insert(4);
        tree.insert(5);
        tree.insert(7);
        tree.insert(1);
        tree.insert(11);
        tree.insert(17);
        System.out.println(tree);
    }

    // 定义平衡二叉（查找）树的根节点
    private AvlNode root;

    /**
     * 计算某一个节点的高度
     *
     * @param node
     * @return
     */
    private int height(AvlNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 计算AVL树的高度
     *
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * 计算两个高度的最大值
     * @param h1
     * @param h2
     * @return
     */
    private int getMax(int h1, int h2) {
        return h1 > h2 ? h1 : h2;
    }

    /**
     * 中序遍历AVL树
     * @param node
     */
    public void inOrder(AvlNode node){
        if(root == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.getData() + "-->");
        inOrder(node.rigth);
    }

    @Override
    public String toString() {
         inOrder(root);
         return "";
    }


    /**
     * LL 旋转
     * @param node 失衡节点
     * @return 左旋后根节点
     */
    public AvlNode ll(AvlNode node){
        // 定义一个临时变量保存我们失衡节点的左子树
        AvlNode node_left = node.left;
        // 将我们失衡节点左子树作为失衡节点的左子树
        node_left = node_left.rigth;
        // 将我们失衡节点作为调整后根节点的右子树
        node_left.rigth = node;
        // 重新计算失衡节点和旋转后根节点高度
        node.height = getMax(height(node.left),height(node.rigth)) + 1;
        node_left.height = getMax(height(node_left.left),height(node_left.rigth)) + 1;
        return node_left;
    }

    /**
     * RR 旋转
     * @param node 失衡节点
     * @return 右旋后根节点
     */
    public AvlNode rr(AvlNode node){
        // 定义临时变量
        AvlNode new_root = node.rigth;
        node.rigth = new_root.left;
        new_root.left = node;
        node.height = getMax(height(node.left),height(node.rigth)) + 1;
        new_root.height = getMax(height(new_root.left),height(new_root.rigth)) + 1;
        return new_root;
    }


    /**
     * LR 旋转
     * @param node 失衡节点
     * @return
     */
    public AvlNode lr(AvlNode node){
        // RR
        node.left = rr(node.left);
        // LL
        AvlNode avlNode = ll(node);
        return avlNode;
    }


    /**
     * RL 旋转
     * @param node 失衡节点
     * @return
     */
    public AvlNode rl(AvlNode node){
        // LL
        node.rigth = ll(node.rigth);
        // RR
        AvlNode avlNode = rr(node);
        return avlNode;
    }


    public void insert(T value){
        this.root = this.insert(this.root,value);
    }

    /**
     * 插入元素
     * @param node
     * @param data
     * @return
     */
    private AvlNode insert(AvlNode node, T data){
        // 节点为空
        if(node == null){
            node = new AvlNode(data);
        }else {
            // 比较
            int compared = data.compareTo(node.getData());
            if(compared > 0){ // 大于当前节点值，存储在右子树上，递归操作
                node.rigth = insert(node.rigth,data);
                // 判断是否失衡
                if(height(node.rigth) - height(node.left) == 2){
                    if(data.compareTo(node.rigth.getData()) > 0){
                        // RR
                        node = this.rr(node);
                    }else {
                        // RL
                        node = this.rl(node);
                    }
                }
            }else if(compared < 0){// 小于当前节点值，存在在左子树，递归操作
                node.left = insert(node.left,data);
                // 判断是否失衡
                if(height(node.left) - height(node.rigth) == 2){
                    if(data.compareTo(node.left.getData()) > 0){
                        // LR
                        node = this.lr(node);
                    }else {
                        // LL
                        node = this.ll(node);
                    }
                }
            }else {

            }
        }
        //计算节点的高度
        node.height = getMax(height(node.left),height(node.rigth)) + 1;
        return node;
    }



    public static class AvlNode<T extends Comparable> {
        // 存储数据
        private T data;
        // 左子节点
        private AvlNode<T> left;
        // 右子节点
        private AvlNode<T> rigth;
        // 节点高度
        private int height;

        public AvlNode(T data) {
            this(data, null, null);
        }

        public AvlNode(T data, AvlNode<T> left, AvlNode<T> rigth) {
            this(data, left, rigth, 0);
        }

        public AvlNode(T data, AvlNode<T> left, AvlNode<T> rigth, int height) {
            this.data = data;
            this.left = left;
            this.rigth = rigth;
            this.height = height;
        }
        // getter - setter

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public AvlNode<T> getLeft() {
            return left;
        }

        public void setLeft(AvlNode<T> left) {
            this.left = left;
        }

        public AvlNode<T> getRigth() {
            return rigth;
        }

        public void setRigth(AvlNode<T> rigth) {
            this.rigth = rigth;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
