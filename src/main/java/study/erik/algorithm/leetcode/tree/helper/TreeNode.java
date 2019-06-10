package study.erik.algorithm.leetcode.tree.helper;

/**
 * @author erik.wang
 * @date 2019/04/10
 **/

public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(Integer x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
