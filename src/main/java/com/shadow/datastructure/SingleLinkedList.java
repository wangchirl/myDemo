package com.shadow.datastructure;


class Node{

    Object data; // 要存储的数据
    Node next; // 下一个节点

    public Node() {
    }

    public Node(Object data) {
        this.data = data;
    }

    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

public class SingleLinkedList implements List{

    private Node head = new Node(); // 头结点，不存储任何数据，为了统一处理元素的添加等操作

    private int size; // 一共有多少个数据

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        // 这里需要遍历查询元素
        Node p = head;
        for (int j = 0; j <= i; j++) {
            p = p.next;
        }
        return p.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object e) {
        // 遍历所有元素
        Node p = head;
        for (int i = 0; i < size; i++) {
            p = p.next;
            if(p.data == e || p.data.equals(e)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object e) {
        // 遍历结点元素
        Node p = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            p = p.next;
            if(p.data == e || p.data.equals(e)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void add(int i, Object e) {
        // 判断 i 的有效性
        if(i < 0 || i > size){
            throw new MyIndexOutOfBoundsException("数组下标异常：" + i);
        }
        // 找到前一个结点 i-1 的位置
        Node p = head;
        for (int j = 0; j < i; j++) {
            p = p.next;
        }
        // 新创建一个结点
        Node newNode = new Node(e);
        // 指定新结点的后继结点
        newNode.next = p.next;
        // 指定新结点的前驱结点
        p.next = newNode;
        // size++
        size++;
    }

    @Override
    public void add(Object e) {
        this.add(size,e);
    }

    @Override
    public boolean addBefore(Object obj, Object e) {
        // 找到 obj 所在的 index
        Node p = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            p = p.next;
            if(p.data == obj || p.data.equals(obj)){
                index = i;
                break;
            }
        }
        if(index == -1){
            throw new MyIndexOutOfBoundsException("不存在的值：" + obj);
        }
        this.add(index, e);
        return true;
    }

    @Override
    public boolean addAfter(Object obj, Object e) {
        // 找到 obj 所在的 index
        Node p = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            p = p.next;
            if(p.data == obj || p.data.equals(obj)){
                index = i;
                break;
            }
        }
        if(index == -1){
            throw new MyIndexOutOfBoundsException("不存在的元素：" + obj);
        }
        this.add(index+1,e);
        return true;
    }

    @Override
    public Object remove(int i) {
        // 判断 i 的有效性
        if(i < 0 || i > size){
            throw new MyIndexOutOfBoundsException("数组下标异常：" +i);
        }
        //  找到 i 的前一个结点
        Node p = head;
        for (int j = 0; j < i; j++) {
            p = p.next;
        }
        // 找到 i 的后继结点
        Node obj = p.next;
        // 将 i 的前驱结点指向后继结点
        p.next = obj.next;
        // 删除 i 结点对 后继结点的引用
        obj.next = null;
        // size--
        size--;
        return obj.data;
    }

    @Override
    public boolean remove(Object e) {
     /*   // 找到所在的 index
        Node p = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            p = p.next;
            if(p.data == e || p.data.equals(e)){
                index = i;
                break;
            }
        }
        if(index == -1){
            return false;
        }
        this.remove(index);
        return true; */

        // 先确定前驱结点和要删除的结点 单链表最后结点的next 一定是null
        Node p = head;
        Node n = head.getNext(); //下一个结点
        boolean flag = false;
        while (n != null) {
            // 判断是否找到
            if(e.equals(n.getData())){
                flag = true;
                break;
            }
            // 如果没找到，指针后移
            p = n;
            n = n.getNext();
        }
        if(flag){
            p.setNext(n.getNext());
            n.setNext(null);
            n = null;
        }
        return flag;
    }

    @Override
    public Object replace(int i, Object e) {
        // 判断 i 的有效性
        if(i < 0 || i > size){
            throw new MyIndexOutOfBoundsException("数组下标异常：" + i);
        }
        // 找到对应的 node 重新设置 node 的data
        Node p = head;
        for (int j = 0; j <= i; j++) {
            p = p.next;
        }
        // 找出旧值，替换新值
        Object origin = p.data;
        p.data = e;
        return origin;
    }


    @Override
    public String toString() {
        if(size == 0){
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        Node p = head.next;
        for (int i = 0; i < size; i++) {
            if(i == size-1) {
                stringBuilder.append(p.data);
            } else {
                stringBuilder.append(p.data + ",");
            }
            p = p.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
