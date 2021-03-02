package com.zhou.demo.leetcode;

import lombok.Data;

/**
 * @ClassName LinkedAdd
 * @Description 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 来源：力扣（LeetCode） 2. 两数相加
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 *
 * @Author JackZhou
 * @Date 2021/2/20  21:30
 **/
public class LinkedAdd {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);//123
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(3);


        ListNode l2 = new ListNode(7); // 7654
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(5);
        l2.next.next.next = new ListNode(4);

        ListNode node = addTwoNumbers(l1, l2);
        System.out.println(node.toString());
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int mid = 0;
        ListNode left = l1;
        ListNode right = l2;
        ListNode result = null;
        ListNode currentNode = null;
        while (left != null || right != null) {
            int sum = (left == null ? 0 : left.val) + (right == null ? 0 : right.val) + mid;
            if (sum >= 10) {
                mid = 1;
                sum = sum - 10;
            }else{
                mid = 0;
            }

            if (result == null) {
                result = new ListNode(sum);
                currentNode = result;
            } else {
                ListNode node = new ListNode(sum);
                currentNode.next = node;
                currentNode = node;
            }
            if(left != null){
                left = left.next;
            }

            if(right != null){
                right = right.next;
            }
        }
        if(mid > 0){
            currentNode.next = new ListNode(mid);
        }

        return result;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }

        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}

@Data
 class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
