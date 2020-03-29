package com.shadow.dataStruct.thought;

/**
 * 回溯思想 - 八皇后问题
 * 1. 任意两个皇后不能在同一条直线上
 * 2. 任意两个皇后不能在对角线上
 */
public class EightQueue {

    public static void main(String[] args) {
        EightQueue eightQueue = new EightQueue();
        eightQueue.backMethod(8);
    }

    /**
     * 皇后数组
     * 下标表示 行数
     * 值表示  列数
     */
    private int[] queue;


    /**
     * 求解8 皇后问题
     *
     * @param n
     */
    public void backMethod(int n) {
        // 皇后数组初始化
        queue = new int[n];
        // 初始化皇后起点
        for (int i = 0; i < queue.length; i++) {
            queue[i] = -1;
        }
        // 从第一个皇后开始
        int k = 0;
        while (true){
            // 第k个皇后要移动一个
            queue[k] += 1;
            // 判断是否应该回到上一行搜索
            if(queue[k] >= n){
                // 皇后越界，此行没有位置可以放置皇后
                if(k > 0){
                    queue[k] = -1;
                    k--;
                    continue;//跳出下面的判断
                }else {
                    break;
                }
            }
            if(!isConflict(k)){
                k++;
                if(k >= n){
                    for (int i = 0; i < n; i++) {
                        System.out.print(queue[i] + "");
                    }
                    System.out.println();
                    k--;
                }
            }
        }

    }


    /**
     * 判断我们第k个皇后是否与之前之后的皇后冲突
     * @param k
     * @return
     */
    public boolean isConflict(int k){
        for (int i = k-1; i > -1 ; i--) {
            if(queue[k] == queue[i] || Math.abs(queue[k] - queue[i]) == Math.abs(k-i)){
                return true;
            }
        }
        return false;

    }

}
