package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-12 23:09
 */
public class SymmetricTree {

    @LetCodeCommit(no = 101, title = "Symmetric Tree")
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null && left.val == right.val) {
            return helper(left.left, right.right) &&
                    helper(left.right, right.left);
        }
        return false;
    }

    @Test
    public void test_case_1() {
        TreeNode root = TreeNode.buildTree("1,2,2,3,4,4,3");
        Assert.assertTrue(isSymmetric(root));
    }

    @Test
    public void test_case_2() {
        TreeNode root = TreeNode.buildTree("1,2,2,null,3,null,3");
        Assert.assertFalse(isSymmetric(root));
    }


}
