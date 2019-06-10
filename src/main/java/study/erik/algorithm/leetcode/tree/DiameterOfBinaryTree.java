package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.leetcode.tree.helper.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/06/10
 **/
public class DiameterOfBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * https://leetcode.com/problems/diameter-of-binary-tree/
     * 描述:求二叉树中，距离最长的两个节点之间的路径
     * 解答：定义'二叉树的直径'=贯穿root节点的左右子树中最深的两个节点之间的路径。
     * 原问题就是'所有子树的直径'的最大致。
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        List<Integer> diameter = new ArrayList<>();
        diameter.add(0);
        depthOfBinaryTree(root, diameter);
        return diameter.get(0);
    }

    /**
     * 注意路径和树深度的不同；路径是两节点之间的边的个数、树深度是节点的个数。
     * @param root
     * @param diameter
     * @return 树的深度
     */
    public int depthOfBinaryTree(TreeNode root, List<Integer> diameter) {

        if (root == null) {
            return 0;
        }

        int leftDepth = depthOfBinaryTree(root.left, diameter);
        int rightDepth = depthOfBinaryTree(root.right, diameter);
        int currentDiameter = diameter.get(0);
//      根据'树的直径'的定义，当前root子树的直径=leftDepth + rightDepth。
        diameter.set(0, Math.max(currentDiameter, leftDepth + rightDepth));
        return Math.max(leftDepth, rightDepth) + 1;

    }

}
