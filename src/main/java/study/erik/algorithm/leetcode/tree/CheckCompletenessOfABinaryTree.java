/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : CheckCompletenessOfABinaryTree.java, v 0.1 2022年11月19日 19:50 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CheckCompletenessOfABinaryTree {

    @LetCodeCommit(title = "958. Check Completeness of a Binary Tree",
            diff = "m",
            selfRemark = "这是lee大神的算法，说实话，佩服，再次见识了")
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (q.peek() != null) {
            TreeNode poll = q.poll();
            q.add(poll.left);
            q.add(poll.right);
        }
        while (!q.isEmpty() && q.peek() == null) {
            q.poll();
        }
        return q.isEmpty();
    }

    @Parameter
    public TreeNode root;
    @Parameter(1)
    public boolean  expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {TreeNode.buildTree("[1,2,3,4,5,6]"), true},
                {TreeNode.buildTree("[1,2,3,4,5,null,7]"), false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, isCompleteTree(root));
    }
}