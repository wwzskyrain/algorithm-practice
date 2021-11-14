/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : FindBottomLeftTreeValue.java, v 0.1 2021年11月14日 7:43 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindBottomLeftTreeValue {

    @LetCodeCommit(title = "513. Find Bottom Left Tree Value",
            selfRemark = "很简单的一个层次遍历，搞定。但是代码繁杂。特别在找每一行的第一个元素时。"
                    + "既然是左边第一个，其实就是右边最后一个，所以只要反过来，就可以让代码精简")
    public int findBottomLeftValue(TreeNode root) {

        //return solutionWithFindFirstLeft(root);
        return solutionWithFindLastRight(root);
    }

    public int solutionWithFindFirstLeft(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode leftMostNode = null;
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int queueLength = queue.size();
            TreeNode tempLeftMostNode = null;
            while (queueLength > 0) {
                queueLength--;
                TreeNode node = queue.removeFirst();
                if (tempLeftMostNode == null) {
                    tempLeftMostNode = node;
                    leftMostNode = tempLeftMostNode;
                }
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return leftMostNode.val;
    }

    public int solutionWithFindLastRight(TreeNode root) {

        Deque<TreeNode> queue = new LinkedList<>();

        queue.addLast(root);
        while (!queue.isEmpty()) {
            root = queue.removeFirst();
            if (root.right != null) {
                queue.addLast(root.right);
            }
            if (root.left != null) {
                queue.addLast(root.left);
            }
        }
        return root.val;
    }
}