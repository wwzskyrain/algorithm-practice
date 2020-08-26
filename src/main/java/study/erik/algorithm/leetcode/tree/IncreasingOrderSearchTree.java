package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

import static study.erik.algorithm.util.QuestionType.Tree;


/**
 * @author erik.wang
 * @date 2020-08-12 23:52
 */
public class IncreasingOrderSearchTree {

    @LetCodeCommit(no = 897, title = "Increasing Order Search Tree", time = 100, space = 76,
            diff = "e",
            types = Tree,
            selfRemark = "easy级别的树题，对我来说不是很简单的，比如这个，把二叉搜索树给线索化，就让我调了半个小时之多",
            related = {"Print Binary Tree-踩比赞多",
                    "Recover a Tree From Preorder Traversal",
                    "Frog Position After T Seconds"})
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode p = new TreeNode(0);

        inOrder(root, new TreeNode[]{p});
        return p.right;
    }

    public void inOrder(TreeNode root, TreeNode[] pre) {
        if (root == null) {
            return;
        }
        inOrder(root.left, pre);
        pre[0].right = root;
        pre[0].left = null;
        pre[0] = pre[0].right;
        inOrder(root.right, pre);
    }

    @Test
    public void test_case_1() {
        TreeNode root = TreeNode.buildTree("5,3,6,2,4,null,8,1,null,null,null,7,9");
        TreeNode newRoot = increasingBST(root);
        System.out.println(1);
    }
}
