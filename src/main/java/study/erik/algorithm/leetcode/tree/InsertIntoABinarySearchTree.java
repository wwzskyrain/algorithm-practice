/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : InsertIntoABinarySearchTree.java, v 0.1 2022年04月23日 1:22 下午 yueyi Exp $
 */
public class InsertIntoABinarySearchTree {

    @LetCodeCommit(title = "701. Insert into a Binary Search Tree",
            diff = "m",
            selfRemark = "一个经典的bst插入算法")
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val, null, null);
        }
        doInsert(root, val);
        return root;
    }

    public void doInsert(TreeNode root, int val) {
        if (root.val > val) {
            if (root.left == null) {
                root.left = new TreeNode(val, null, null);
            } else {
                doInsert(root.left, val);
            }
            return;
        }
        if (root.right == null) {
            root.right = new TreeNode(val, null, null);
        } else {
            doInsert(root.right, val);
        }
    }

}