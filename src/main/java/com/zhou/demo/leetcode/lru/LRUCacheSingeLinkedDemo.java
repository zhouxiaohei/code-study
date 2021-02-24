package com.zhou.demo.leetcode.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LRUCacheDemo
 * @Author JackZhou
 * @Date 2021/2/22  12:53
 *
 * https://leetcode-cn.com/problems/lru-cache-lcci/
 *  面试题 16.25. LRU 缓存
 *
 *  TODO   Least Recently Used（LRU）淘汰最长时间未被使用的页面，以时间作为参考
 *   // 将最近访问过得移动到头部
 * Least Frequently Used(LFU)淘汰一定时期内被访问次数最少的页面，以次数作为参考
 * 先进先出算法（FIFO）
 **/
public class LRUCacheSingeLinkedDemo {

    public static void main(String[] args) {
        testOne();
        //lruCacheDemo.put(4,1);
        //System.out.println( lruCacheDemo.get(1));
    }

    public static void testOne(){
        LRUCacheSingeLinkedDemo lruCacheDemo = new LRUCacheSingeLinkedDemo(1);
        lruCacheDemo.put(2,1);
        System.out.println(lruCacheDemo.get(2));
        lruCacheDemo.put(3,2);
        System.out.println(lruCacheDemo.get(2));
        System.out.println(lruCacheDemo.get(3));
    }

    private final int maxSize;
    Map map;
    NodeDemo head = null;

    public LRUCacheSingeLinkedDemo(int capacity) {
        this.maxSize = capacity; // TODO 手误
        map = new HashMap(maxSize);
    }

    public int get(int key) {
        //  TODO bug1  LUR定义、淘汰最长时间未被使用的节点
        Object o = map.get(key);
        if(o == null){
            return -1;
        }else{
            head = head.addLast(key);
            return (int)o;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { //TODO bug3 实现细节 更新操作、更新顺序
            head = head.addLast(key);
        } else {
            if (map.size() >= maxSize) {
                // 使用单向链表头、移除最早的元素、形成简单的LRU
                int removeKey = head.value;  // TODO bug2 思维混乱、不该的操作
                head = head.next;   // 可能head为null
                map.remove(removeKey);
            }
            if (head == null) {
                head = new NodeDemo(key);
            } else {
                head.add(key);
            }
        }
        map.put(key, value);
    }

    class NodeDemo{
        int value;
        NodeDemo next;

        public NodeDemo(int value){
            this.value = value;
        }

        // 新增直接添加到节点最后
        public void add(int value){
            NodeDemo current = this;
            while(current.next != null){
                current = current.next;
            }
            current.next = new NodeDemo(value);
        }

        // 将最近访问的添加到最后
        public NodeDemo addLast(int value){
            // 长度为1，直接返回
            if(this.next == null){
                return this;
            }
            NodeDemo head = this;
            NodeDemo tail = this;
            if(value != tail.value){ // 如果访问节点不是头部节点、找到此节点
                // 方便继续遍历找尾部节点
                NodeDemo currentNode = tail.next;
                // 找到新头部节点
                while(tail.next.value != value){
                    currentNode = tail;
                    tail = tail.next;
                }
                tail.next = currentNode.next; // 将当前节点的下一个节点挂到前一个节点的下一个节点、有可能是空节点
            }else{
                head = this.next;  // 当前节点为头节点、头节点为当前节点一个节点
            }
            // 找到最尾部节点
            while(tail.next != null){
                tail = tail.next;
            }
            tail.next = new NodeDemo(value);// 将当前节点放到尾部
            return head;
        }

    }

}

