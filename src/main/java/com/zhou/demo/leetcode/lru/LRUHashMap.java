package com.zhou.demo.leetcode.lru;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName LRUHashMap
 * @Author JackZhou
 * @Date 2020/9/21  10:40
 * @Desc 固定长度大小的map
 *    利用链表的头结点特性，重写链表受保护的方法 判断时候删除最长时间未被使用的节点 removeEldestEntry
 **/
public class LRUHashMap<K, V> extends LinkedHashMap<K, V> {

    public static void main(String[] args) {
        Map<Integer, String> map = Collections.synchronizedMap(new LRUHashMap(10, true));
        for(int i = 0; i<9; i++){
            map.put(i, i + "");
        }
        System.out.println(map.get(1));
        for(int i = 9; i<15; i++){
            map.put(i, i + "");
        }
        System.out.println(map.get(2));
        System.out.println(map.get(1));
        System.out.println(map.get(5));
        System.out.println(map.get(8));

    }


    private final int maxSize;

    public LRUHashMap(int maxSize, boolean accessOrder){
        super(maxSize, 0.75F, accessOrder);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }
}
