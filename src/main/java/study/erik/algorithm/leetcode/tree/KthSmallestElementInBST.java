package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-27 16:49
 */
public class KthSmallestElementInBST {

    @LetCodeCommit(title = "230. Kth Smallest Element in a BST",
            selfRemark = "中序遍历，调一调就可以了")
    public int kthSmallest(TreeNode root, int k) {

        int[] data = new int[2];
        data[0] = k;
        inOrder(root, data);
        return data[1];
    }

    private void inOrder(TreeNode root, int[] data) {
        if (root == null || data[0] == 0) {
            return;
        }

        inOrder(root.left, data);
        if (data[0] != 0) {
            data[1] = root.val;
            data[0]--;
        }
        inOrder(root.right, data);
    }

    @Test
    public void test_solution_1() {
        TreeNode root = TreeNode.buildTree("3,1,4,null,2");
        int k = 1;
        Assert.assertEquals(1, kthSmallest(root, k));
    }

    @Test
    public void test_solution_2() {
        TreeNode root = TreeNode.buildTree("5,3,6,2,4,null,null,1");
        int k = 3;
        Assert.assertEquals(3, kthSmallest(root, k));
    }

}
