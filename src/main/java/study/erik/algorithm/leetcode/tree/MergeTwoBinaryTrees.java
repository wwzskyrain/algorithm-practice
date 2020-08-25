package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-20 12:58
 */
public class MergeTwoBinaryTrees {


    @LetCodeCommit(title = "617. Merge Two Binary Trees", time = 9, space = 5,
            selfRemark = "看代码多么整洁：不用另起函数，不用层层if判断。而且还保持了t1和t2的结构")
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (t1 == null && t2 == null) {
            return null;
        }
        TreeNode left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        TreeNode right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
        TreeNode root = new TreeNode((t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val));
        root.left = left;
        root.right = right;
        return root;
    }

}
