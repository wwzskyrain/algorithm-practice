/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BinaryTreePruning.java, v 0.1 2022年05月19日 13:51 yueyi Exp $
 */
public class BinaryTreePruning {

    @LetCodeCommit(title = "814. Binary Tree Pruning",
            diff = "m",
            selfRemark = "随便写的，0ms")
    public TreeNode pruneTree(TreeNode root) {
        if (root.left != null) {
            TreeNode leftRes = pruneTree(root.left);
            if (leftRes == null) {
                root.left = null;
            }
        }
        if (root.right != null) {
            TreeNode rightRes = pruneTree(root.right);
            if (rightRes == null) {
                root.right = null;
            }
        }
        if (root.left == null && root.right == null) {
            return root.val == 0 ? null : root;
        } else {
            return root;
        }
    }
}