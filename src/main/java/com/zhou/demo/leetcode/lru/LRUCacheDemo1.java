package com.zhou.demo.leetcode.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LRUCacheDemo1
 * @Author JackZhou
 * @Date 2021/2/23  21:00
 *
 * https://leetcode-cn.com/problems/lru-cache-lcci/solution/linkedhashmap-shuang-lian-biao-hashmap-dan-lian-2/
 **/
public class LRUCacheDemo1 {
    private int capacity;
    private Map<Integer, ListNode> map; //key -> node.pre
    private ListNode head;  // dummy
    private ListNode tail;

    public LRUCacheDemo1(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // map中存放的是要找的节点的前驱
        ListNode pre = map.get(key);
        ListNode cur = pre.next;

        // 把当前节点删掉并移到尾部
        if (cur != tail) {
            pre.next = cur.next;
            // 更新它后面 node 的前驱
            map.put(cur.next.key, pre);
            map.put(cur.key, tail);
            moveToTail(cur);
        }
        return cur.val;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            map.get(key).next.val = value;
            return;
        }
        // 若不存在则 new 一个
        ListNode node = new ListNode(key, value);
        // 当前 node 的 pre 是 tail
        map.put(key, tail);
        moveToTail(node);

        if (map.size() > capacity) {
            map.remove(head.next.key);
            map.put(head.next.next.key, head);
            head.next = head.next.next;
        }
    }

    private void moveToTail(ListNode node) {
        node.next = null;
        tail.next = node;
        tail = tail.next;
    }

    // 定义单链表节点
    private class ListNode {
        int key, val;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }

}