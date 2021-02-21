package com.zhou.demo.leetcode;

/**
 * @ClassName ClimbStairs
 * @Author JackZhou
 * @Date 2021/2/21  13:16
 **/
public class DynamicPlan {


    /**
     *   https://leetcode-cn.com/problems/climbing-stairs/
     *   假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     *   每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     **/
    //动态规划问题   可以用递归，但是会重复计算、所以使用循环、自底而上
    public int climbStairs(int n) {
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

    public static void main(String[] args) {

    }


    public static void  testOne(){
       int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        maxSubArray(nums);
    }

    /**
     * https://leetcode-cn.com/problems/maximum-subarray/   最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     **/
    public static int maxSubArray(int[] nums) {
        int pre = 0, max = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            max = Math.max(max, pre);
        }
        return max;
    }

}
