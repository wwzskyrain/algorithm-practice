/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumBinaryTree.java, v 0.1 2022年04月07日 7:34 上午 yueyi Exp $
 */
public class MaximumBinaryTree {

    @LetCodeCommit(title = "654. Maximum Binary Tree",
    selfRemark = "文不加点，一遍通过，成绩还可以，85。"
            + "待优化点：快速找到最值，而不是O(n).")
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return doConstruct(nums, 0, nums.length - 1);
    }

    public TreeNode doConstruct(int[] nums, int l, int h) {
        if (l > h) {
            return null;
        }
        if (l == h) {
            return new TreeNode(nums[l]);
        }
        int maxIndex = l;
        int i = l;
        while (i <= h) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
            i++;
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = doConstruct(nums, l, maxIndex - 1);
        root.right = doConstruct(nums, maxIndex + 1, h);
        return root;
    }
}