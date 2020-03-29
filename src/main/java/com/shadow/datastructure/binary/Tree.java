package com.shadow.datastructure.binary;


/**
 * 二叉树的接口
 * 可以有不同的实现类，每个类可以使用不同的存储结构，比如顺序结构，链式结构
 */
public interface Tree {
    /**
     * 是否空数
     * @return
     */
    boolean isEmpty();

    /**
     * 数结点数量
     * @return
     */
    int size();

    /**
     * 获取二叉树的高度
     * @return
     */
    int getHeight();

    /**
     * 获取指定值的结点
     * @param value
     * @return
     */
    Node findKey(Object value);

    /**
     * 前序递归遍历
     */
    void preOrderTraverse();

    /**
     * 中序递归遍历
     */
    void inOrderTraverse();

    /**
     * 后序递归遍历
     */
    void postOrderTraverse();

    /**
     * 后序递归遍历操作
     * @param node 树根结点
     */
    void postOrderTraverse(Node node);

    /**
     * 中序遍历 非递归操作
     * 1）对于任意结点 current，若该结点不为空则将该结点压栈，并将左子树结点置为current，重复此操作，直到结点current为空
     * 2）若左子树为空，栈顶结点出栈，访问结点后将该节点的右子树置为current
     * 3）重复1,2步骤，直到current为空且栈内结点为空
     */
    void inOrderByStack();

    /**
     * 前序遍历非递归操作
     * 1）对于任意结点current，若该结点不为空则访问该节点后再将结点压栈，并将左子树结点置为current，重复此操作，直到current为空
     * 2）若左子树为空，栈顶结点出栈，将该结点的右子树置为current
     * 3）重复1,2步骤，直到current为空且栈内结点为空
     */
    void preOrderByStack();

    /**
     * 后序遍历非递归操作
     * 1）对于任意结点current，若该结点不为空则访问该节点后再将结点压栈，并将左子树结点置为current，重复此操作，直到current为空
     * 2）若左子树为空，取栈顶结点的右子树，如果右子树为空或右子树刚访问过，则访问该结点，并将preNode置为该
     * 3）重复1,2步骤，直到current为空且栈内结点为空
     */
    void postOrderByStack();

    /**
     * 按照层次遍历二叉树
     */
    void levelOrderByQueue();
}
