/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BinarySearchTreeToGreaterSumTree.java, v 0.1 2022年12月12日 20:52 yueyi Exp $
 */
public class BinarySearchTreeToGreaterSumTree {

    @LetCodeCommit(title = "1038. Binary Search Tree to Greater Sum Tree")
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        bstToGst(root.right);
        curSum += root.val;
        root.val = curSum;
        bstToGst(root.left);
        return root;
    }

    int curSum = 0;

}