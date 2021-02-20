package com.zhou.demo.leetcode;

import java.util.Arrays;

/**
 * @ClassName Test1
 * @Author JackZhou
 * @Date 2021/1/11  19:38
 * @Desc  反转数组、指定位置后面的反转    1,3,5,6,8,10,12  3  ->  8, 10, 12, 1, 3, 5, 6
 *    leetcode 541. 反转字符串 II
 **/
public class ReverseArr {

    static int count;

    public static void main(String[] args) {
        testChange();
    }

    public static void testChange(){

        int [] arr = new int[]{1,3,5,6,8,10,12};
        int offset = 3;
        changeArr(arr, 3);
        System.out.println(count);
    }

    public static void changeArr(int[] arr, int offset){

        if(arr == null || arr.length == 0){
            return;
        }

        int size = arr.length;
        offset = offset % size;
        if(offset <= 0){
            return;
        }
        reverse(arr,0, size -offset -1);
        reverse(arr, size-offset, size-1);
        reverse(arr, 0, size-1);
        System.out.println(Arrays.toString(arr));
    }

    // 倒叙排列   开始和结束index
    private static void reverse(int[] arr, int start, int end){
        for( ;start < end; start++,end--){
            count++;
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
        }
    }
}
