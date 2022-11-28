/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DistributeCoinsInBinaryTree.java, v 0.1 2022年11月26日 15:15 yueyi Exp $
 */
public class DistributeCoinsInBinaryTree {

    @LetCodeCommit(title = "979. Distribute Coins in Binary Tree",
            diff = "m",
            selfRemark = "树题，真的是有意思呢。而且这个题目，我独立一边通过。")
    public int distributeCoins(TreeNode root) {
        int[] res = postOrder(root);
        return res[2];
    }

    /**
     * @param root
     * @return 0=node 1=coin 2=move
     */
    public int[] postOrder(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new int[] {1, root.val, Math.abs(root.val - 1)};
        }
        int[] res = new int[] {1, root.val, 0};
        if (root.left != null) {
            int[] left = postOrder(root.left);
            res[0] += left[0];
            res[1] += left[1];
            res[2] += left[2];
        }
        if (root.right != null) {
            int[] right = postOrder(root.right);
            res[0] += right[0];
            res[1] += right[1];
            res[2] += right[2];
        }
        res[2] += Math.abs(res[1] - res[0]);
        return res;
    }
}