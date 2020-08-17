package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-17 13:07
 */
public class BalancedBinaryTree {

    @LetCodeCommit(no = 110, title = "Balanced Binary Tree", time = 99, space = 86,
            selfRemark = "复用二叉树遍历时返回的状态")
    public boolean isBalanced(TreeNode root) {
        return postOrder(root) != -1;
    }

    /**
     * 这既是一个求树高的题目，又是一个判断是否是平衡树的遍历
     *
     * @param root
     * @return > -1 时，就是一个平衡树的树高；-1时，不是一个平衡树
     */
    public int postOrder(TreeNode root) {
        // 统一处理空指针
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int lH = postOrder(root.left);
        if (lH == -1) {
            return -1;
        }
        int rH = postOrder(root.right);
        if (rH == -1) {
            return -1;
        }

        //真正的遍历
        if (Math.abs(rH - lH) <= 1) {
            return lH > rH ? lH + 1 : rH + 1;
        }
        return -1;
    }


    @Test
    public void test_solution_1() {
        TreeNode root = TreeNode.buildTree("3,9,20,null,null,15,7");
        Assert.assertTrue(isBalanced(root));
    }

    @Test
    public void test_solution_2() {
        TreeNode root = TreeNode.buildTree("1,2,2,3,3,null,null,4,4");
        Assert.assertFalse(isBalanced(root));
    }

}
