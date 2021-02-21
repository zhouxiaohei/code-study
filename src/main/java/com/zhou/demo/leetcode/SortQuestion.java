package com.zhou.demo.leetcode;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName SortQuestion
 * @Author JackZhou
 * @Date 2021/2/21  16:01
 **/
public class SortQuestion {

    //在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
    // 215. 数组中的第K个最大元素

    // java排序  略
//    public int findKthLargest(int[] nums, int k) {
//        Arrays.sort(nums);
//        return nums[nums.length - k];
//    }

    //TODO  java优先级队列   小顶堆
    // 构建大顶堆  PriorityQueue small=new PriorityQueue<>(Collections.reverseOrder());
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 小顶堆   满了就弹出最小的元素
        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k){
                pq.poll(); // 维护堆的大小为 K
            }
        }
        return pq.peek();
    }

    //给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
    //347. 前 K 个高频元素 https://leetcode-cn.com/problems/top-k-frequent-elements/description/
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        // 构建小顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : frequencyForNum.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    //https://leetcode-cn.com/problems/sort-characters-by-frequency/description/
    //451 根据字符出现频率排序 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
    public String frequencySort(String s) {
        if(s == null || s.length() ==1){
            return  s;
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> frequencyForNum = new HashMap<>();
        for(char charSt : chars){
            frequencyForNum.put(charSt, frequencyForNum.get(charSt) == null ? 1 : frequencyForNum.get(charSt)+1);
        }
        //TODO  然后排序整个map
        return null;
    }

    // https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/gen-ju-shen-gao-zhong-jian-dui-lie-by-leetcode-sol/
    //TODO 待解决
    //Input:  [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
    // [4,4],[5,0],[5,2],[6,1],[7,0],[7,1]
    // [5,0],[7,0],[6,1],[7,1],[5,2],[4,4]  这种方式不对
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }

        // 根据身高从小到大排序、身高相等根据前面的人数从小到大排序
        Arrays.sort(people, (a1, a2) ->{
            if(a1[1] != a2[1]){
                return a1[0] - a2[0];
            }else{
                return a1[1] -a2[1];
            }
        });


        return null;
    }


}
