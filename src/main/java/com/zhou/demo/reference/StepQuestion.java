package com.zhou.demo.reference;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName StepQuestion
 * @Author JackZhou
 * @Date 2021/1/18  10:23
 **/

// 小青蛙跳台阶问题、一次可以调一个台阶或者两个  N个台阶有几种跳法
public class StepQuestion {

    private static int[] arr;

    private static AtomicInteger atomicWayOne = new AtomicInteger();
    private static AtomicInteger atomicWayTwo = new AtomicInteger();

    public static void main(String[] args) {
        // 基础版
        System.out.println(stepWays(20));
        System.out.println(atomicWayOne.get());
        // 优化版，  包含大量重复计算、优化已经计算过的直接用； 或者使用for循环优化也可
        System.out.println(stepWaysOptimize(20));
        System.out.println(atomicWayTwo.get());

        System.out.println(stepWaysUp(20));
    }

    public static int stepWays(int n){
        if(n == 1 || n == 2){
            return n;
        }
        atomicWayOne.incrementAndGet();
        return stepWays(n-1) + stepWays(n-2);
    }

    // 递归，但是记录   不重复计算、需要额外空间
    public static int stepWaysOptimize(int n){
        if(n == 1 || n == 2){
            return n;
        }
        arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        arr[n] = stepWaysOption(n);
        return arr[n];
    }

    public static int stepWaysOption(int n){
        if(arr[n] != 0){
            return arr[n];
        }
        atomicWayTwo.incrementAndGet();
        arr[n-1] = stepWaysOption(n-1);
        arr[n-2] = stepWaysOption(n-2);
        return arr[n-1] + arr[n-2];
    }


    //TODO 不使用额外空间的  自底而上
    public static int stepWaysUp(int n){
        if(n == 1 || n == 2){
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        for(int i = 3; i < n + 1; i++){
            int temp = f2;
            f2 = f1 + f2;
            f1 = temp;
        }
        return f2;
    }
}
