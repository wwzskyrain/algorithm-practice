/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : TrimABinarySearchTree.java, v 0.1 2021年11月14日 8:09 下午 yueyi Exp $
 */
public class TrimABinarySearchTree {

    @LetCodeCommit(title = "669. Trim a Binary Search Tree",
            time = 100,
            space = 45,
            selfRemark = "二叉树的题目都很优雅，我们最喜欢。")
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val <= high) {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
        return trimBST(root.left, low, high);
    }

}