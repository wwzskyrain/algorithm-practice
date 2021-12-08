/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
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

/**
 * @author yueyi
 * @version : BinaryTreeTilt.java, v 0.1 2021年12月09日 1:40 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BinaryTreeTilt {

    @LetCodeCommit(title = "563. Binary Tree Tilt",
            selfRemark = "确实是一个被dis的题目，细节题目，难度不到m，所以就是e了")
    public int findTilt(TreeNode root) {
        sum(root);
        int[] ret = {0};
        doFindTilt(root, ret);
        return ret[0];
    }

    public void doFindTilt(TreeNode root, int[] ret) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left == null) {
            ret[0] += Math.abs(root.right.val);
        } else if (root.right == null) {
            ret[0] += Math.abs(root.left.val);
        } else {
            ret[0] += Math.abs(root.left.val - root.right.val);
        }
        doFindTilt(root.left, ret);
        doFindTilt(root.right, ret);
    }

    public int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        root.val += (leftSum + rightSum);
        return root.val;
    }

    @Parameter
    public TreeNode root;
    @Parameter(1)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {TreeNode.buildTree("[1,2,3]"), 1},
                {TreeNode.buildTree("[4,2,9,3,5,null,7]"), 15},
                {TreeNode.buildTree("[21,7,14,1,1,2,2,3,3]"), 9},
                {TreeNode.buildTree("[-8,3,0,-8,null,null,null,null,-1,null,8]"), 18},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findTilt(root));
    }
}