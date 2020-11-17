package com.zhou.demo.lintcode;

/**
 * @ClassName Test
 * @Author JackZhou
 * @Date 2020/11/13  17:13
 * TODO 595 最长序列
 * 给一棵二叉树，找到最长连续路径的长度。
 * 这条路径是指 任何的节点序列中的起始节点到树中的任一节点都必须遵循 父-子 联系。最长的连续路径必须是从父亲节点到孩子节点（不能逆序）。
 * https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence/description
 **/
public class Test {
    private int longest;

    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
        longest = 0;
        helper(root);
        return longest;
    }

    //从跟节点出发，都有两个方向，从左走 从右走
    //一直递归到最底层、分解为分治算法  再合并并解
    private int helper(TreeNode root) {
        // 叶子节点
        if (root == null) {
            return 0;
        }

        int left = helper(root.left);
        int right = helper(root.right);

        // 每一次递归都要重新置 1, 至少包含当前结点
        int subtreeLongest = 1;
        // 能和左儿子的连续序列连上，长度从 1 更新为 left+1
        if (root.left != null && root.val + 1 == root.left.val) {
            subtreeLongest = Math.max(subtreeLongest, left + 1);
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            subtreeLongest = Math.max(subtreeLongest, right + 1);
        }

        if (subtreeLongest > longest) {
            longest = subtreeLongest;
        }
        return subtreeLongest;
    }

}


class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
