package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-27 16:45
 */
public class InvertBinaryTree {

    @LetCodeCommit(title = "226. Invert Binary Tree", time = 100, selfRemark = "一遍通过，没什么难的")
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


}
