package com.zhou.demo.leetcode;

/**
 * @ClassName MedianSortedArrays
 * @Author JackZhou
 * @Date 2021/1/19  12:39
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *  寻找两个正序数组的中位数   时间复杂度为 O(log (m+n))
 **/
public class MedianSortedArrays {

    public static void main(String[] args) {

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        // 中位数是1个还是2个
        int twoMedian = (length1 + length2)/2;
        int oneMedian  = 0;
        if((length1 + length2) % 2 == 1){
            oneMedian = twoMedian + 1;
        }

        // 其中一个等于0
        if(length1 == 0){
            if(oneMedian != 0){
                return nums2[oneMedian];
            }else{
                return nums2[twoMedian] + nums2[twoMedian + 1];
            }
        }

        if(length2 == 0){
            if(oneMedian != 0){
                return nums1[oneMedian];
            }else{
                return nums1[twoMedian] + nums1[twoMedian + 1];
            }
        }


        int i = 0; int j =0;
        int count = 2;
        boolean flag = true; // twoMedian 落到nums1 还是 num2
        while(count != twoMedian){
            if(nums1[i] <= nums2[j] && i < length1){
                flag = true;
                i++;
                count++;
            }else if( j < length2){
                flag = false;
                j++;
                count++;
            }
        }

        return 0;
    }
}
