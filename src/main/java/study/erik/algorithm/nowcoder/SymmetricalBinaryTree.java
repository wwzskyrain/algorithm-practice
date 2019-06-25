package study.erik.algorithm.nowcoder;

/**
 * @author erik.wang
 * @date 2019/06/24
 **/
public class SymmetricalBinaryTree {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

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
