package com.shadow.datastructure;

import java.util.Arrays;

public class ArrayList implements List {

    // 底层是一个数组，目前还没确定长度
    private Object[] elementData;

    // 不是数组的长度，二十元素的个数
    private int size;


    // 无参构造
    public ArrayList(){
        this(4);
        // 初始化数组大小
        // elementData = new Object[]{};
    }

    /**
     *  有参构造
     * @param initialCapacity 指定数组的初始长度
     */
    public ArrayList(int initialCapacity){
        // 给数组分配指定数量的空间
        elementData = new Object[initialCapacity];
        // 指定顺序表元素个数，默认是 0
        // size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        // 如果 i 小于 0 或这 i 大于等于 size 会溢出
        if(i < 0 || i >= size) {
            throw new MyIndexOutOfBoundsException("数组下标溢出：" + i);
        }
        return elementData[i];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        // 循环查看
        for (int i = 0; i < size; i++) {
            if(elementData[i] == o || elementData[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if(elementData[i] == o || elementData[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(int i, Object o) {
        if(i < 0 || i > size){
            throw new MyIndexOutOfBoundsException("数组下标异常：" + i);
        }
        // 数组满了，需要扩容
        if(size == elementData.length) {
            grow();
        }
        // i 后续的元素需要后移
        for (int j = size; j > i; j--) {
            elementData[j] = elementData[j-1];
        }
        // 给数组的 i 位置赋值
        elementData[i] = o;
        size++;
    }

    private void grow() {
        // 方式 1
           /* Object[] newArr = new Object[size + (size >> 1)];
            // 将旧数组值复制到新数组
            for (int i = 0; i < elementData.length; i++) {
                newArr[i] = elementData[i];
            }
            // 旧数组引用指向新数组引用
            elementData = newArr; */
        // 方式 2
        elementData = Arrays.copyOf(elementData, size + (size >> 1));
    }

    @Override
    public void add(Object o) {
       this.add(size, o);
       /* // 数组满了，需要扩容
        if(size == elementData.length) {
            grow();
        }
        // 把元素添加到最后一个位置
        elementData[size] = o;
        // 元素个数加 1
        size++;
        System.out.println("数组大小===" + elementData.length); */
    }


    @Override
    public boolean addBefore(Object o1, Object o2) {
        int i = getIndex(o1);
        // 加入到指定位置
        this.add(i,o2);
        return true;
    }

    private int getIndex(Object o1) {
        // 先找到 o1 位置，然后插入
        int i = this.indexOf(o1);
        if(i < 0){
            throw new MyIndexOutOfBoundsException("没有找到指定元素" + o1);
        }
        return i;
    }

    @Override
    public boolean addAfter(Object o1, Object o2) {
        int index = getIndex(o1);
        this.add(index + 1,o2);
        return true;
    }

    @Override
    public Object remove(int i) {
        // 移除位置 i 的元素，然后将后面的元素往前移动
        Object obj = elementData[i];
        for (int j = i; j < size; j++) {
            elementData[j] = elementData[j+1];
        }
        // size --
        size--;
        return obj;
    }

    @Override
    public boolean remove(Object o) {
        // 找到 o 对应的 index
        int index = -1;
        for (int i = 0; i < size; i++) {
            if(elementData[i] == o || elementData[i].equals(o)) {
                index = i;
                break;
            }
        }
        if(index == -1){
            return false;
        }
        this.remove(index);
        return true;
    }

    @Override
    public Object replace(int i, Object o) {
        // 找到 i 的元素进行替换
        if(i < 0 || i > size){
            throw new MyIndexOutOfBoundsException("数组下标异常：" + i);
        }
        Object temp = elementData[i];
        elementData[i] = o;
        return temp;
    }

    /**
     *  [1,2,3,4,5,6]
     * @return
     */
    @Override
    public String toString() {
        if(size == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < elementData.length; i++) {
            if(i == elementData.length-1){
                stringBuilder.append(elementData[i]);
            }else {
                stringBuilder.append(elementData[i] + ",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
