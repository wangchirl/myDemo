package com.shadow.datastructure;

public interface List {
    // 返回线性表的大小，即数据元素的个数
    int size();

    // 返回线性表中序号为 i 的数据元素
    Object get(int i);

    // 如果线性表为空返回true，否则返回false
    boolean isEmpty();
    // 判断线性表算法包含数据元素 e
    boolean contains(Object e);
    // 返回数据元素 e 在线性表中的序号
    int indexOf(Object e);
    // 将元素 e 添加到线性表指定位置 i 处
    void add(int i, Object e);
    // 将元素 e 添加到线性表末尾
    void add(Object e);
    // 将元素 e 插入到元素 obj 之前
    boolean addBefore(Object obj, Object e);
    // 将元素 e 插入到元素 obj 之后
    boolean addAfter(Object obj, Object e);
    // 删除线性表中序号为 i 的元素
    Object remove(int i);
    // 删除线性表中第一个与 e 相同的元素
    boolean remove(Object e);
    // 替换掉线性表中序号为 i 的数据元素为 e ，返回被替换的元素
    Object replace(int i, Object e);

}
