/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version ： BinarySearchTreeIterator.java, v 0.1 2021年12月16日 8:46 上午 yueyi Exp $
 * title = 173. Binary Search Tree Iterator
 */
public class BSTIterator {

    private Deque<TreeNode> stack = new LinkedList<>();
    private TreeNode        p;

    @LetCodeCommit(title = "173. Binary Search Tree Iterator",
            selfRemark = "在考查二叉树的递给非递归遍历(中序)")
    public BSTIterator(TreeNode root) {
        p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
    }

    public int next() {
        TreeNode pop = stack.pop();
        p = pop.right;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        return pop.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty() || p != null;
    }

}