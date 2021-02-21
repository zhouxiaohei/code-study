package com.zhou.demo.leetcode;

import java.util.Arrays;

/**
 * @ClassName ReverseStr
 * @Author JackZhou
 * @Date 2021/2/20  18:40
 * @Desc 344. 反转字符串   https://leetcode-cn.com/problems/reverse-string/
 **/
public class ReverseStr {

    public static void main(String[] args) {
        //testReverseString("abcefs");
        System.out.println(reverseStr("abcdefg", 2));
    }

    //给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
    //
    //如果剩余字符少于 k 个，则将剩余字符全部反转。
    //如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样
    //
    //来源：力扣（LeetCode）  541. 反转字符串 II
    //链接：https://leetcode-cn.com/problems/reverse-string-ii
    public static String reverseStr(String s, int k) {
        if (s == null) {
            return null;
        }

        char[] charArr = s.toCharArray();
        int size = charArr.length;

        int begin = 0;
        //两种情况剩余元素大于K或者小于K
        while (size >= (begin + k)) {  // 剩余元素大于K 、反转K
            reverseChar(charArr, begin, begin + k - 1);
            if ((begin + 2 * k) <= size) {
                begin = begin + 2 * k;  // 移动2k
            } else {
                return new String(charArr);  //不足2k 结束
            }
        }
        reverseChar(charArr, begin, size - 1); //剩余元素
        return new String(charArr);
    }


    public static void reverseChar(char[] s, int begin, int end) {
        if (s == null || s.length == 1) {
            return;
        }
        while (begin < end) {
            char temp = s[begin];
            s[begin] = s[end];
            s[end] = temp;
            begin++;
            end--;
        }
    }

    public static void testReverseString(String str) {
        char[] chars = str.toCharArray();
        reverseString(chars);
        System.out.println(Arrays.toString(chars));
    }
   // 反转字符串   https://leetcode-cn.com/problems/reverse-string/
    public static void reverseString(char[] s) {
        if (s == null || s.length == 1) {
            return;
        }
        int begin = 0;
        int end = s.length - 1;
        while (begin < end) {
            char temp = s[begin];
            s[begin] = s[end];
            s[end] = temp;
            begin++;
            end--;
        }
    }

}
