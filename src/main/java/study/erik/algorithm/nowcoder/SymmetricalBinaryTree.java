package study.erik.algorithm.nowcoder;

import study.erik.algorithm.leetcode.util.TreeNode;

/**
 * @author erik.wang
 * @date 2019/06/24
 **/
public class SymmetricalBinaryTree {

    boolean isSymmetrical(TreeNode pRoot) {

        if (pRoot == null) {
            return true;
        }

        return mirror(pRoot.left, pRoot.right);
    }

    boolean mirror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }

        return mirror(root1.left, root2.right) && mirror(root1.right, root2.left);

    }

}
