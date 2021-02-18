package com.zhou.demo;

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
        Node node =  new Node();
        for(int i = 1; i < 10; i++){
            node.add(i);
        }
        System.out.println(node.toString());

        System.out.println(rever(node));
    }

    public static Node rever(Node node){
        if(node == null || node.getNext() == null){
            return node;
        }
        Node temp = node.getNext();
        Node rever = rever(temp);

        //rever.setNext(temp);

        temp.setNext(node);
        node.setNext(null);
        return rever;
    }
}

@Data
class Node {
    private Integer head;
    private Node next;

    public Node() {
    }

    public Node(Integer head) {
        this.head = head;
    }

    public void add(Integer value) {
        if (head == null) {
            this.head = value;
        } else {
            if (next == null) {
                next = new Node(value);
            } else {
                Node nextNode = next;
                while (nextNode.next != null) {
                    nextNode = nextNode.next;
                }
                nextNode.next = new Node(value);
            }

        }
    }
}