package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-05 08:11
 */
public class MaximumSumBstInBinaryTreeTest {


    @LetCodeCommit(no = 1373, title = "Maximum Sum BST in Binary Tree", time = 29, space = 20, diff = "h",
            types = LetCodeCommit.Type.Tree,
            related = {"Binary Tree Cameras", "Minimum Cost to Merge Stones"},
            selfRemark = "树的题目，一般考思想，我也不在意时间复杂度。这里判断BST时要注意与左右子树的最小值和最大值比较，而不是和左右子树的根的值比较")
    public int maxSumBST(TreeNode root) {
        return Math.max(postIsBst(root)[0], 0);
    }

    /**
     * 以root为根节点的最大sum
     *
     * @param root
     * @return {
     * maxSum(当前root的子树中包含的最大的bst的sum),
     * sum(以当前root为根的bst的sum),
     * 1/0(当前root子树是否为bst),
     * min(当前bst树的最小值，如果当前子树不是bst就算了)
     * min(当前bst树的最大值，如果当前子树不是bst就算了)
     * }
     */
    public static int[] postIsBst(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new int[]{root.val, root.val, 1, root.val, root.val};
        }
        int[] left = null;
        int[] right = null;
        if (root.left != null) {
            left = postIsBst(root.left);
        }
        if (root.right != null) {
            right = postIsBst(root.right);
        }

        if (root.left != null && root.right != null) {
            if (left[2] == 1 && right[2] == 1
                    && left[4] < root.val && root.val < right[3]) {
                int max = Math.max(Math.max(left[0], right[0]), right[1] + left[1] + root.val);
                return new int[]{max, right[1] + left[1] + root.val, 1, left[3], right[4]};
            }
            return new int[]{Math.max(left[0], right[0]), 0, 0, 0, 0};
        }

        if (root.left != null) {
            if (left[2] == 1 && left[4] < root.val) {
                int max = Math.max(left[0], left[1] + root.val);
                return new int[]{max, left[1] + root.val, 1, left[3], root.val};
            }
            return new int[]{left[0], 0, 0, 0, 0};
        }

        if (root.right != null) {
            if (right[2] == 1 && root.val < right[3]) {
                int max = Math.max(right[0], right[1] + root.val);
                return new int[]{max, right[1] + root.val, 1, root.val, right[4]};
            }
            return new int[]{right[0], 0, 0, 0, 0};
        }
        throw new IllegalArgumentException();
    }

    @Test
    public void test_case_1() {
        TreeNode tree = TreeNode.buildTree("1,4,3,2,4,2,5,null,null,null,null,null,null,4,6");
        Assert.assertEquals(20, maxSumBST(tree));
    }

    @Test
    public void test_case_2() {
        TreeNode tree = TreeNode.buildTree("4,3,null,1,2");
        Assert.assertEquals(2, maxSumBST(tree));
    }

    @Test
    public void test_case_3() {
        TreeNode tree = TreeNode.buildTree("-4,-2,-5");
        Assert.assertEquals(0, maxSumBST(tree));
    }

    @Test
    public void test_case_4() {
        TreeNode tree = TreeNode.buildTree("2,1,3");
        Assert.assertEquals(6, maxSumBST(tree));
    }

    @Test
    public void test_case_5() {
        TreeNode tree = TreeNode.buildTree("5,4,8,3,null,6,3");
        Assert.assertEquals(7, maxSumBST(tree));
    }

    @Test
    public void test_case_6() {
        TreeNode tree = TreeNode.buildTree("4,8,null,6,1,9,null,-5,4,null,null,null,-3,null,10");
        Assert.assertEquals(14, maxSumBST(tree));
    }

    @Test
    public void test_case_7() {
        TreeNode tree = TreeNode.buildTree("1,null,10,-5,20");
        Assert.assertEquals(25, maxSumBST(tree));
    }


}
