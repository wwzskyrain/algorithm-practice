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
 * @version : CountCompleteTreeNodes.java, v 0.1 2021年06月22日 12:45 下午 yueyi Exp $
 */
public class CountCompleteTreeNodes {

    @LetCodeCommit(title = "Count Complete Tree Nodes")
    public int countNodes(TreeNode root) {
        return resolveWithBFS(root);
    }

    public int resolveWithBinarySearch(TreeNode root) {
        return 0;
    }

    public int resolveWithBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            count++;
            if (node.left == null) {
                return 2 * count - 1;
            }
            queue.addLast(node.left);

            if (node.right == null) {
                return 2 * count;
            }
            queue.addLast(node.right);
        }
        return 0;
    }

}