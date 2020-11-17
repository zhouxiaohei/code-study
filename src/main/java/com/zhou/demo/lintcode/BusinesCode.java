package com.zhou.demo.lintcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @ClassName BusinesCode
 * @Author JackZhou
 * @Date 2020/11/11  14:26
 **/
public class BusinesCode {

    //TODO leetcode 第八题 旋转字符串
    public static void testChangeStr(){
        String str = "abcdefg";
        int offset = 3;
        changeStrArr(str.toCharArray(), 3);
    }

    ////给定一个字符串（以字符数组的形式给出）和一个偏移量，根据偏移量原地旋转字符串(从左向右旋转)。
    public static void changeStrArr(char[] str, int offset){

        if(str == null || str.length == 0){
            return;
        }

        int size = str.length;
        offset = offset % size;
        if(offset <= 0){
            return;
        }
        reverse(str,0, size -offset -1);
        reverse(str, size-offset, size-1);
        reverse(str, 0, size-1);
        System.out.println(Arrays.toString(str));
    }

    // 倒叙排列字符串   开始和结束index
    private static void reverse(char[] str, int start, int end){
        for( ;start < end; start++,end--){
            char tmp = str[start];
            str[start] = str[end];
            str[end] = tmp;
        }
    }


    //TODO leetcode 第82题
    public static void testprintlnOnly(){
        int[] ints = {1, 1, 2, 3, 3, 4, 4, 5, 2};
        System.out.println(printlnOnly(ints));
    }

    //给出 2 * n + 1个数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。
    //使用^ 的用法
    public static int printlnOnly(int[] A){
        int only = A[0];
        for(int i = 1; i < A.length; i++){
            only = only ^ A[i];//出现两次的数异或后为0，只剩出现一次的数。
        }
        return only;
    }


    //TODO 第1题，不用+  求和   ^  &  左移等
    public static int aplusb(int a, int b){
        if(b == 0) {
            return a;
        }else if(a == 0){
            return b;
        }
        // 负数用1补位最大值为-1
        System.out.println("a:" + a + "; a:" + Integer.toBinaryString(a));
        System.out.println("b:" + b + "; b:" + Integer.toBinaryString(b));
        // 0和1得1  不然得0;
        int c = a ^ b;  //保留剩下的1，用于后面继续计算
        // 1和1得1  不然得0   左移一位相当于乘以2
        b = (a & b) << 1;  //1和1相同的进位
        return aplusb(c,b);
    }

    //https://zhuanlan.zhihu.com/p/121064958
    //TODO 两数之和 第55题  使用hash
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if(map.get(numbers[i]) != null){
                int[] result = {map.get(numbers[i]), i};
                return result;
            }
            map.put(target- numbers[i], i);
        }
        return null;
    }


    public static void main(String[] args) {
        //testChangeStr();
        //testprintlnOnly();
       // System.out.println( aplusb(-5, 3));

    }
}

//TODO 两数之和 第55题  效率低一点
class Solution {
    /*
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    class Pair {
        Integer value;
        Integer index;

        Pair(Integer value, Integer index) {
            this.value = value;
            this.index = index;
        }

        Integer getValue() {
            return this.value;
        }
    }

    class ValueComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        //用一个pair数组记录每个numbers[i]的值和它的位置i，防止排序后不知道该元素的位置
        Pair[] number = new Pair[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            number[i] = new Pair(numbers[i], i);
        }
        //排序后使用双指针
        Arrays.sort(number, new ValueComparator());
        int L = 0, R = numbers.length - 1;
        while (L < R) {
            if (number[L].getValue() + number[R].getValue() == target) {
                int t1 = number[L].index;
                int t2 = number[R].index;
                int[] result = {Math.min(t1, t2), Math.max(t1, t2)};
                return result;
            }
            if (number[L].getValue() + number[R].getValue() < target) {
                L++;
            } else {
                R--;
            }
        }
        int[] res = {};
        return res;
    }
}