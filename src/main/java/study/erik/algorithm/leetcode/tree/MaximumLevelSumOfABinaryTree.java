/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : MaximumLevelSumOfABinaryTree.java, v 0.1 2023年02月26日 19:56 yueyi Exp $
 */
public class MaximumLevelSumOfABinaryTree {

    @LetCodeCommit(title = "1161. Maximum Level Sum of a Binary Tree",
    selfRemark = "easy, no more word.")
    public int maxLevelSum(TreeNode root) {

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int l = 1;
        int maxL = l;
        int maxSum = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int size = q.size();
            int sum = 0;
            while (size > 0) {
                TreeNode poll = q.poll();
                size--;
                sum += poll.val;
                if (poll.left != null) {
                    q.add(poll.left);
                }
                if (poll.right != null) {
                    q.add(poll.right);
                }
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxL = l;
            }
            l++;
        }
        return maxL;
    }
}