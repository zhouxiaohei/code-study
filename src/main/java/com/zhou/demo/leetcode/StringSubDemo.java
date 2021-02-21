package com.zhou.demo.leetcode;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName StringSubDemo
 * @Author JackZhou
 * @Date 2021/2/20  22:12
 * @Desc  相关 https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
 **/
public class StringSubDemo {

    public static void main(String[] args) {

        lengthOfLongestSubstring("abcadabc");
    }

    //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
    // * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
    // 滑动窗口   abcadabc
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0){
            return 0;
        }
        // map存放出现过得字符
        HashMap<Character, Integer> map = new HashMap();
        int max = 0;
        int begin = 0; //开始位置
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){  //如果出现重复、从重复后面开始
                begin = Math.max(begin, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);  // 覆盖重复下标
            max = Math.max(max, i-begin+1);
        }
        return max;

    }

    //最小覆盖子串 https://leetcode-cn.com/problems/minimum-window-substring/
    // 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    //TODO 思路相同  need hashMap  存在的hashmap  、实现待考究
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<Character, Integer>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (char c :  t.toCharArray()){
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        // 记录最小覆盖字串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 判断取出的字符是否在字串中
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c,0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断是否需要收缩（已经找到合适的覆盖串）
            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char c1 = s.charAt(left);
                left++;
                if (need.containsKey(c1)) {
                    if (window.get(c1).equals(need.get(c1))) {
                        valid--;
                    }
                    window.put(c1, window.getOrDefault(c1, 0) - 1);
                }

            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    //https://leetcode-cn.com/problems/minimum-size-subarray-sum/
    //长度最小的子数组
    // 输入：target = 7, nums = [2,3,1,2,4,3]
    //输出：2
    //解释：子数组 [4,3] 是该条件下的长度最小的子数组。
    //如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= target) { //缩小窗口、挤出水分
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

     //通过删除字母匹配到字典里最长单词  524
    // https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/description/
     public String findLongestWord(String s, List<String> d) {
         String longestWord = "";
         for (String target : d) {
             int l1 = longestWord.length(), l2 = target.length();
             if (l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0)) {
                 continue;
             }
             if (isSubstr(s, target)) {
                 longestWord = target;
             }
         }
         return longestWord;
     }

    private boolean isSubstr(String s, String target) {
        int i = 0, j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == target.length();
    }
}
