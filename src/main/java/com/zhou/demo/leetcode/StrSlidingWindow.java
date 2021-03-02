package com.zhou.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StrSlidingWindow
 * @Author JackZhou
 * @Date 2021/2/26  21:42
 **/
public class StrSlidingWindow {

    public static void main(String[] args) {
        //testOne();
        StrSlidingWindow strSlidingWindow = new StrSlidingWindow();
        System.out.println(strSlidingWindow.convert("ABCDE", 5));


    }

    public static void testOne(){
        String str = "abba";
        StrSlidingWindow strSlidingWindow = new StrSlidingWindow();
        System.out.println(strSlidingWindow.lengthOfLongestSubstring(str));
    }

    //3. 无重复字符的最长子串
    //https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        char[]  charArr = s.toCharArray();
        Map<Character, Integer> map = new HashMap();
        int max = 0;
        int beginIndex = -1;
        for(int i = 0; i< charArr.length; i++){
            Integer index = map.get(charArr[i]);
            if(index != null){
                if(beginIndex < index){  //开始index只能往前
                    beginIndex = index;
                }
            }
            max = Math.max(max, i-beginIndex);
            map.put(charArr[i], i);
        }
        return max;
    }

    //https://leetcode-cn.com/problems/longest-palindromic-substring/
    // 5. 最长回文子串    回文子串的意思是  去掉首尾字母还是回文字符串
    // todo 使用动态规划
    public String longestPalindrome(String s) {
        char[] charArr = s.toCharArray();
        Map<Character, Integer> map = new HashMap();
        int beginIndex = 0;
        int endIndex = 0;
        int max = 0;
        for(int i=0; i< charArr.length; i++){
            Integer index = map.get(charArr[i]);
            if(index != null){
                int size = i - index ;
                if(size > max){
                    beginIndex = index;
                    endIndex = i;
                    max = size;
                }
            }else{
                map.put(charArr[i], i);
            }
        }
        if(beginIndex == endIndex){
            return charArr[0] + "";
        }else{
            return s.substring(beginIndex, endIndex + 1);
        }

    }

    //6. Z 字形变换
    //https://leetcode-cn.com/problems/zigzag-conversion/
    //TODO 性能很差需要优化   这个好优化规律都出来了
    public String convert(String s, int numRows) {
        if(s.length() == 1 || numRows <= 1){
            return s;
        }
        Map<Integer, StringBuilder> map = new HashMap(numRows);
        char[] charArr = s.toCharArray();
        int h = 0; //高
        boolean flag = false; // 加周期和减周期
        for(int i =0; i< charArr.length; i++ ){
            StringBuilder stringBuilder = map.get(h);
            if(stringBuilder == null){
                stringBuilder = new StringBuilder();
                map.put(h, stringBuilder);
            }
            stringBuilder.append( charArr[i]);

            if(h == (numRows-1) || h == 0 ){  // 先加后减
                flag = !flag;
            }
            if(flag){ //加周期  保持不变
                h++;
            }else{
                h--;
            }
        }
        String result = "";
        for(int i = 0; i< map.size(); i++){  // "AB"  3   长度不能用numRows
            result = result + map.get(i).toString();
        }

        return result;
    }

//    public String convert(String s, int numRows) {
//        if(s.length() == 1 || numRows <= 1){
//            return s;
//        }
//
//        char[] charArr = s.toCharArray();
//        Character[][] charTwoArr = new Character[numRows][charArr.length];
//        int w = 0; // 长
//        int h = 0; //高
//        boolean flag = false; // 加周期和减周期
//        for(int i =0; i< charArr.length; i++ ){
//            charTwoArr[h][w] = charArr[i];
//
//            if(h == (numRows-1) || h == 0 ){  // 先加后减
//                flag = !flag;
//            }
//            if(flag){ //加周期  保持不变
//                h++;
//            }else{
//                h--;
//                w++;
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i<charTwoArr.length; i++){
//            for(int j = 0; j< charTwoArr[0].length; j++){
//                if(charTwoArr[i][j] != null){
//                    sb.append(charTwoArr[i][j]);
//                }
//            }
//        }
//        return sb.toString();
//    }

    //https://leetcode-cn.com/problems/palindrome-number/submissions/
    // 简单 9. 回文数
    public boolean isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0) ){
            return false;
        }
        char[] charArr = (x + "").toCharArray();
        int left = 0;
        int right = charArr.length -1;
        while(left < right){
            if(!(charArr[left] == charArr[right])){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //https://leetcode-cn.com/problems/3sum/
    // 三数之和   -1 2  3 -3 5  0  1  2 5    三数之和等于零
    // 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
    //
    //注意：答案中不可以包含重复的三元组。  题目理解、元素可以重复；另外一种题  元素不能重复
    public List<List<Integer>> threeSum(int[] nums) {
        // 长度不够三位
        if(nums.length < 3){
            return null;
        }
        // 排序方便匹配
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList();
        int right = nums.length -1;
        int left = 0;
        while((right - left) >= 2){
            if(nums[left] > 0) break;// 如果当前数字大于0，则三数之和一定大于0，所以结束循环

            int num1 = nums[left]; //最小的数
            int num2 = nums[right];  // 最大的数

            int midSum = num1 + num2;
            if(midSum > 0){ //从左边开始找
                int midIndex = left + 1; // (right - left) > 3  不需要再判断越界
                int sum = (nums[midIndex] + midSum);
                while( sum != 0 ){ //加上下一个最小的数、也不能等于目标0
                    if(sum < 0){
                        midIndex++;
                        if(midIndex >= right){ //如果大于右边、说明找不到  抛弃最大的数
                            right--;
                            break;
                        }

                    }else{   // 大于0，凑不齐 抛弃最大的数
                         right--;
                         break;
                    }
                }
                //等于0;
                List<Integer> reslut = new ArrayList<>();
                reslut.add(left);
                reslut.add(right);
                reslut.add(midIndex);
                nums[midIndex] = Integer.MAX_VALUE;
            }else{



            }

        }
        return null;
    }

//    public int threeSumClosest(int[] nums, int target) {
//        Arrays.sort(nums);
//        int ans = nums[0] + nums[1] + nums[2];
//        for(int i=0;i<nums.length;i++) {
//            int start = i+1, end = nums.length - 1;
//            while(start < end) {
//                int sum = nums[start] + nums[end] + nums[i];
//                if(Math.abs(target - sum) < Math.abs(target - ans))
//                    ans = sum;
//                if(sum > target)
//                    end--;
//                else if(sum < target)
//                    start++;
//                else
//                    return ans;
//            }
//        }
//        return ans;
//    }

//https://leetcode-cn.com/problems/3sum-closest/solution/zui-jie-jin-de-san-shu-zhi-he-by-leetcode-solution/
    // 三数之和接近target
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

}
