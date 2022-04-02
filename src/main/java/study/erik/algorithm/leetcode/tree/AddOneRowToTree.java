/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : AddOneRowToTree.java, v 0.1 2022年04月02日 9:57 上午 yueyi Exp $
 */
public class AddOneRowToTree {

    @LetCodeCommit(title = "Add One Row to Tree")
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (level == depth - 1) {
                while (size > 0) {
                    TreeNode node = queue.pollFirst();
                    // 安插左节点
                    TreeNode newLeftNode = new TreeNode(val);
                    if (node.left != null) {
                        newLeftNode.left = node.left;
                    }
                    node.left = newLeftNode;

                    // 安插右节点
                    TreeNode newRightNode = new TreeNode(val);
                    if (node.right != null) {
                        newRightNode.right = node.right;
                    }
                    node.right = newRightNode;
                    size--;
                }
            } else {
                while (size > 0) {
                    TreeNode node = queue.pollFirst();
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    size--;
                }
            }
            level++;
        }
        return root;
    }
}