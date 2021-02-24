package com.zhou.demo.leetcode;

import lombok.Data;

/**
 * @ClassName reverseSingeLinked
 * @Author JackZhou
 * @Date 2021/1/17  19:46
 **/

// 反转单链表 1—>2->3->4  4->3-2-1
public class ReverseSingeLinked {

    public static void main(String[] args) {
        // 构建单链表
        Node node =  new Node(1);
        for(int i = 2; i < 6; i++){
            node.add(i);
        }
        System.out.println(node.toString());

        //递归和循环解决
        //System.out.println(reverRecursion(node));
        //System.out.println(rever(node));

        // leetcode  92
        testReverseBetween(node, 1, 5);

    }

    // 分治思想   反转N N-1 反转 1
    public static Node reverRecursion(Node node){
        if(node == null || node.getNext() == null){
            return node;
        }
        Node temp = node.getNext();
        Node rever = reverRecursion(temp);
        temp.setNext(node);
        node.setNext(null);
        return rever;
    }

    // 循环处理
    public static Node rever(Node node){
        if(node == null || node.getNext() == null){
            return node;
        }
        Node current = node;
        Node next = node.getNext();
        node.setNext(null); // 设置为null，不然循环调用栈溢出
        while(next != null){
            Node temp = next.getNext();
            next.setNext(current);
            current = next;
            next = temp;
        }
        return current;
    }


    public static void testReverseBetween(Node head, int left, int right){
        System.out.println(reverseBetween(head, left, right).toString());
    }

    // https://leetcode-cn.com/problems/reverse-linked-list-ii/
    // .92 反转链表 II     反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
    // 说明 1 ≤ m ≤ n ≤ 链表长度。
    //输入: 1->2->3->4->5->NULL, m = 2, n = 4
    // 输出: 1->4->3->2->5->NULL
    public static Node reverseBetween(Node head, int left, int right) {
        if(left == right){
            return  head;
        }
        // 找到开始的节点和开始的前一个节点
        //如果left节点为1、开始节点的前一个节点为null
        Node beginNodeBefore =  null;
        Node beginNode = head;
        int begin = 1;
        while(begin != left){
            beginNodeBefore = beginNode;
            beginNode = beginNode.getNext();
            begin ++;
        }

       // 暂存新的链   新的链的头尾节点   left 不等于right 设置头尾节点
        Node beginListNode = new Node(beginNode.getValue());
        Node next = beginNode.getNext();  // 前面已经判断开始和结束不相等 while必走
        Node endListNode = new Node(next.getValue());
        endListNode.setNext(beginListNode);
        begin++;
        next = next.getNext();
        while(begin != right){
            //依次将前一个节点设置为后一个节点的下一个节点
            Node temp = next;
            next = next.getNext();
            temp.setNext(endListNode);
            endListNode = temp;
            begin++;
        }

        // 将尾部节点和 之前的头部节点连接
        if(next != null){
            beginListNode.setNext(next);
        }

        // left节点为开始节点、right节点为最开始节点    反之需要连起来
        if(beginNodeBefore != null){
            beginNodeBefore.setNext(endListNode);
            return head;
        }
        return endListNode;
    }
}

@Data
class Node {
    private Integer value;
    private Node next;

    public Node() {
    }

    public Node(Integer value) {
        this.value = value;
    }

    // 新增直接添加到节点最后
    public void add(int value){
        Node current = this;
        while(current.next != null){
            current = current.next;
        }
        current.setNext(new Node(value));
    }
}

