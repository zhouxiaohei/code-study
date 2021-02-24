package com.zhou.demo.leetcode.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LRUCacheDoubleLinkedDemo
 * @Author JackZhou
 * @Date 2021/2/23  20:40
 *
 *  // 双向链表实现
 **/
public class LRUCacheDoubleLinkedDemo {
    private int capacity;
    private Map<Integer, DoubleNodeDemo> map;
    private DoubleNodeDemo head;
    private DoubleNodeDemo tail;

    public static void main(String[] args) {
        testOne();
    }

    public static void testOne(){

        LRUCacheDoubleLinkedDemo lruCacheDemo = new LRUCacheDoubleLinkedDemo(2);
        lruCacheDemo.put(1,1);
        lruCacheDemo.put(2,2);
        System.out.println(lruCacheDemo.get(1));
        lruCacheDemo.put(3,3);
        System.out.println(lruCacheDemo.get(2));
        lruCacheDemo.put(4,4);
        System.out.println(lruCacheDemo.get(1));
        System.out.println(lruCacheDemo.get(3));
        System.out.println(lruCacheDemo.get(4));
    }

    public LRUCacheDoubleLinkedDemo(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new DoubleNodeDemo(-1, -1);
        head.flag = true;
        tail = new DoubleNodeDemo(-1, -1);
        tail.flag = true;
        head.next = tail;
        tail.prve = head;
    }

    public int get(int key) {
        DoubleNodeDemo node = map.get(key);
        if(node == null){
            return -1;
        }else{
            addLast(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DoubleNodeDemo node = map.get(key);
            node.value = value;
            addLast(node);
        }else{
            if (map.size() >= capacity) {
                DoubleNodeDemo removeNode = head.next;
                map.remove(removeNode.key);
                remove(removeNode);

            }
            DoubleNodeDemo node = new DoubleNodeDemo(key, value);
            map.put(key, node);
            add(node);
        }
    }

    public void print(){
        System.out.print("head <-->");
        DoubleNodeDemo current = head.next;
        while(!current.flag){
            System.out.print("<- [" + current.value  + "] ->");
            current = current.next;
        }
        System.out.println(" <-->end");
    }

    public void remove(DoubleNodeDemo node){
        node.prve.next = node.next;
        node.next.prve = node.prve;
    }

  //  head  <-> 1 <->  2 <->  3  <-> tail
    public void add(DoubleNodeDemo node){
        node.prve = tail.prve;
        tail.prve.next = node;
        tail.prve = node;
        node.next = tail;
    }

    // 将已存在的节点移到最后
    public void addLast(DoubleNodeDemo node){
        node.prve.next = node.next;
        node.next.prve = node.prve;
        add(node);
    }

    class DoubleNodeDemo{
        public int key;
        public int value;
        public boolean flag = false; // 设置头尾节点
        public DoubleNodeDemo next;
        public DoubleNodeDemo prve;

        public DoubleNodeDemo(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}


