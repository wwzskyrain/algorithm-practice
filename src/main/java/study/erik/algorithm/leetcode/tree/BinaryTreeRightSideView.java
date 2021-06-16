/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : BinaryTreeRightSideView.java, v 0.1 2021年06月15日 8:04 上午 yueyi Exp $
 */
public class BinaryTreeRightSideView {

    @LetCodeCommit(title = "Binary Tree Right Side View",
            selfRemark = "二叉树的右视图")
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        TreeNode END_NODE = new TreeNode(0);
        TreeNode pre = END_NODE; //前一个有效的节点
        queue.addLast(root);
        queue.addLast(END_NODE);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            if (treeNode != END_NODE) {
                if (treeNode.left != null) {
                    queue.addLast(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.addLast(treeNode.right);
                }
                pre = treeNode;
            } else {
                result.add(pre.val);
                if (!queue.isEmpty()) {
                    queue.addLast(END_NODE);
                }
            }
        }
        return result;
    }

    @Test
    public void test_1() {
        TreeNode root = TreeNode.buildTree("1,2,3,null,5,null,4");
        List<Integer> result = rightSideView(root);
        System.out.println(result);
    }

    @Test
    public void test_2() {
        TreeNode root = TreeNode.buildTree("1,null,3");
        List<Integer> result = rightSideView(root);
        System.out.println(result);
    }

    @Test
    public void test_3() {
        TreeNode root = TreeNode.buildTree("");
        List<Integer> result = rightSideView(root);
        System.out.println(result);
    }

}