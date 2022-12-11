/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumDifferenceBetweenNodeAndAncestor.java, v 0.1 2022年12月11日 16:04 yueyi Exp $
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

    @LetCodeCommit(title = "1026. Maximum Difference Between Node and Ancestor")
    public int maxAncestorDiff(TreeNode root) {
        int[] postOrder = postOrder(root);
        return postOrder[2];
    }

    public int[] postOrder(TreeNode root) {
        if (root.right == null && root.left == null) {
            return new int[] {root.val, root.val, 0};
        }
        int min = root.val;
        int max = root.val;
        int maxV = 0;
        if (root.left != null) {
            int[] left = postOrder(root.left);
            min = Math.min(min, left[0]);
            max = Math.max(max, left[1]);
            maxV = Math.max(maxV, left[2]);
        }
        if (root.right != null) {
            int[] right = postOrder(root.right);
            min = Math.min(min, right[0]);
            max = Math.max(max, right[1]);
            maxV = Math.max(maxV, right[2]);
        }
        int v = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
        maxV = Math.max(maxV, v);
        return new int[] {min, max, maxV};

    }

}