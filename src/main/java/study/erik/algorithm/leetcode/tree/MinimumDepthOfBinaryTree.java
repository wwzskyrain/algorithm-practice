package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-21 08:34
 */
public class MinimumDepthOfBinaryTree {

    @LetCodeCommit(title = "111. Minimum Depth of Binary Tree",
            selfRemark = "力扣每日一题")
    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        int l = minDepth(root.left);
        int r = minDepth(root.right);
        return (l < r ? l : r) + 1;
    }

    @Test
    public void test_solution_1() {

        TreeNode root = TreeNode.buildTree("3,9,20,null,null,15,7");
        Assert.assertEquals(2, minDepth(root));

    }

    @Test
    public void test_solution_2() {

        TreeNode root = TreeNode.buildTree("1,2");
        Assert.assertEquals(2, minDepth(root));

    }


}
