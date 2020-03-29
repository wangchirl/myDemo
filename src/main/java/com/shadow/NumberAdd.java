package com.shadow;




import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberAdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            int count = scanner.nextInt();
            scanner.nextLine();
            List<int[]> arr = new ArrayList<>();
            do {
                String num = scanner.nextLine();
                // 先不check了
                // 转化为数组
                int[] array = toArray(num);
                arr.add(array);
                count--;
            }while (count > 0);

            // 找出最大的长度
            int max = 0;
            for (int i = 0; i < arr.size(); i++) {
                if(arr.get(i).length > max){
                    max = arr.get(i).length;
                }
            }
            // 定义一个大于1的数组
            int[] result = new int[max+1];
            // 从后往前遍历数组相加
            for (int i = 0; i < arr.size(); i++) { // 每个数据的内容
                int[] ints = arr.get(i);
                if(i == 0){// 第一个元素直接赋值给数组
                    int len = ints.length;
                    int rlen = result.length;
                    for (int j = len - 1; j >= 0; j--) {
                        result[rlen-1] = ints[j];
                        rlen--;
                    }
                }else {// 那么就将result中的数据从后往前进行相加

                    int pre = 0;// 记录是否有进位
                    int index = ints.length;
                    for (int j = result.length-1; j >= 0; j--) {
                        int temp = result[j];
                        int temp2 = 0;
                        if(index <= 0){
                            temp2 = 0;
                        }else {
                            temp2 = ints[index-1];
                        }

                        int res = temp + temp2 + pre;
                        if(j == 0){
                            pre = res;
                        }else {
                            if(res/10 == 0){
                                pre = 0;
                            }else {
                                pre = res / 10;
                                res = res % 10;
                            }
                        }

                        if( j == 0){
                            result[j] = pre;
                        }else {
                            result[j] = res;
                        }
                            index--;
                    }
                }
            }
            StringBuilder resNum = new StringBuilder("");
            for (int i = 0; i < result.length; i++) {
                if(i == 0 && result[i] == 0){
                    continue;
                }
                resNum.append(result[i]);
            }
            System.out.println(resNum.toString());
            break;
        }
    }

    public static int[] toArray(String s){

        if(s.isEmpty()){
            return null;
        }
        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int c = Integer.parseInt(s.substring(i,i+1));
            res[i] = c;
        }
        return res;
    }
}
